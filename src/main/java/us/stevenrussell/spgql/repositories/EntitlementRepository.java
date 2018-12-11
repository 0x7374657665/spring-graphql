package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.CreateEntitlementInput;
import us.stevenrussell.spgql.types.Entitlement;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static us.stevenrussell.spgql.repositories.TypeMappers.ENTITLEMENT_MAPPER;

@Repository
public class EntitlementRepository {

    private JdbcTemplate jdbc;
    private ApplicationRepository appRepo;
    private AuthorizationService authSvc;

    @Autowired
    public EntitlementRepository(JdbcTemplate jdbc, ApplicationRepository appRepo, AuthorizationService authSvc) {
        this.jdbc = jdbc;
        this.appRepo = appRepo;
        this.authSvc = authSvc;
    }

    public Map<Long, List<Entitlement>> getEntitlementsForApplicationIds(Set<Long> applicationIds) {

        List<String> roles = authSvc.getRoles();
        boolean isAdmin = roles.contains("ROLE_ADMIN");

        String query = new StringBuilder()
                .append("select * from entitlement e where e.parent_application_id  in ( ")
                .append(String.join(", ", Collections.nCopies(applicationIds.size(), "?")))
                .append(" )")
                .toString();

        List<Entitlement> entitlements = jdbc.query(query, ENTITLEMENT_MAPPER, applicationIds.toArray());

        Map<Long, List<Entitlement>> entsByAppId = entitlements
                .stream()
                .filter(ent -> !ent.isRestricted() || isAdmin)
                .collect(
                        Collectors.groupingBy(
                                ent -> ent.getParentApplicationId()
                        )
                );

        return entsByAppId;
    }

    public List<Entitlement> getAllEntitlements() {
        return jdbc.query("select * from entitlement e", ENTITLEMENT_MAPPER);
    }

    private Entitlement getEntitlement(String parentAppName, String entName) {
        String query = "select * from application a, entitlement e where e.parent_application_id = a.id and a.name = ? and e.name = ?";
        Entitlement entitlement = (Entitlement) jdbc.queryForObject(query, ENTITLEMENT_MAPPER, parentAppName, entName);
        return entitlement;
    }

    public List<Entitlement> getEntitlementsByName(String entitlementName) {
        return jdbc.query("select * from entitlement e where e.name = ?", ENTITLEMENT_MAPPER, entitlementName);
    }


    public Entitlement createEntitlement(CreateEntitlementInput newEntitlementData) {
        Application parentApp = appRepo.getApplicationByName(newEntitlementData.getParentAppName());
        String query = "insert into entitlement (id, name, display_name, description, restricted, parent_application_id, is_deleted, created, updated) values (seq_id.nextval,?,?,?,?,?,false,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());";
        int updatedRows = jdbc.update(query,
                newEntitlementData.getName(),
                newEntitlementData.getDescription(),
                newEntitlementData.getDescription(),
                newEntitlementData.isRestricted(),
                parentApp.getId()
        );

        Entitlement newEnt = null;
        if (updatedRows == 1) {
            newEnt = getEntitlement(parentApp.getName(), newEntitlementData.getName());
        }

        return newEnt;
    }
}
