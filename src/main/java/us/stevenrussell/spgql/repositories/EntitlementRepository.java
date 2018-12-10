package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;
import us.stevenrussell.spgql.types.EntitlementDisplayInput;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static us.stevenrussell.spgql.repositories.TypeMappers.ENTITLEMENT_MAPPER;

@Repository
public class EntitlementRepository {

    private JdbcTemplate jdbc;
    private AuthorizationService authSvc;
    private ApplicationRepository appRepo;

    @Autowired
    public EntitlementRepository(JdbcTemplate jdbc, AuthorizationService authSvc, ApplicationRepository appRepo) {
        this.jdbc = jdbc;
        this.authSvc = authSvc;
        this.appRepo = appRepo;
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
        List<String> roles = authSvc.getRoles();
        boolean isAdmin = roles.contains("ROLE_ADMIN");

        return jdbc.query("select * from entitlement e" + (isAdmin ? "" : " where e.restricted=false"), ENTITLEMENT_MAPPER);
    }

    public Entitlement createEntitlement(EntitlementDisplayInput newEntitlementData) {
        Application parentApp = appRepo.getApplicationByName(newEntitlementData.getParentAppName());
        String query = "insert into entitlement (id, name, display_name, description, restricted, parent_application_id, is_deleted, created, updated) values (seq_id.nextval,?,?,?,false,?,false,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());";
        int updatedRows = jdbc.update(query,
                newEntitlementData.getName(),
                newEntitlementData.getDescription(),
                newEntitlementData.getDescription(),
                parentApp.getId()
        );

        Entitlement newEnt = null;
        if (updatedRows == 1) {
            newEnt = getEntitlement(parentApp.getName(), newEntitlementData.getName());
        }

        return newEnt;
    }

    private Entitlement getEntitlement(String parentAppName, String entName) {
        String query = "select * from application a, entitlement e where e.parent_application_id = a.id and a.name = ? and e.name = ?";
        Entitlement entitlement = (Entitlement) jdbc.queryForObject(query, ENTITLEMENT_MAPPER, parentAppName, entName);
        return entitlement;
    }

    public Entitlement updateEntitlement(EntitlementDisplayInput entitlementUpdates) {
        Application parentApp = appRepo.getApplicationByName(entitlementUpdates.getParentAppName());
        String query = "update entitlement set display_name=?, description=?, restricted=?, updated=CURRENT_TIMESTAMP() where name=? and parent_application_id=?;";
        int updatedRows = jdbc.update(query,
                entitlementUpdates.getDisplayName(),
                entitlementUpdates.getDescription(),
                entitlementUpdates.isRestricted(),
                entitlementUpdates.getName(),
                parentApp.getId()
        );

        Entitlement updatedEnt = null;
        if (updatedRows == 1) {
            updatedEnt = getEntitlement(parentApp.getName(), entitlementUpdates.getName());
        }

        return updatedEnt;
    }

    public Entitlement softDelete(String parentAppName, String entitlementName) {
        Entitlement toDelete = getEntitlement(parentAppName, entitlementName);
        toDelete.setDeleted(true);

        String query = "update entitlement set is_deleted=true, updated=CURRENT_TIMESTAMP() where id = ? ;";
        int changed = jdbc.update(query, toDelete.getId());

        return changed == 1 ? toDelete : null;
    }
}
