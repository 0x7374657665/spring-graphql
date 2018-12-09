package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
    private AuthorizationService authSvc;

    @Autowired
    public EntitlementRepository(JdbcTemplate jdbc, AuthorizationService authSvc) {
        this.jdbc = jdbc;
        this.authSvc = authSvc;
    }

    public Map<Long, List<Entitlement>> getEntitlementsForApplicationIds(Set<Long> applicationIds) {

        List<String> roles = authSvc.getRoles();

        String query = new StringBuilder()
                .append("select * from entitlement e where e.parent_application_id  in ( ")
                .append(String.join(", ", Collections.nCopies(applicationIds.size(), "?")))
                .append(" )")
                .toString();

        List<Entitlement> entitlements = jdbc.query(query, ENTITLEMENT_MAPPER, applicationIds.toArray());

        Map<Long, List<Entitlement>> entsByAppId = entitlements
                .stream()
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
}
