package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Entitlement;

import java.util.List;

import static us.stevenrussell.spgql.repositories.TypeMappers.ENTITLEMENT_MAPPER;

@Repository
public class EntitlementRepository {

    private JdbcTemplate jdbc;
    private ApplicationRepository appRepo;

    @Autowired
    public EntitlementRepository(JdbcTemplate jdbc, ApplicationRepository appRepo) {
        this.jdbc = jdbc;
        this.appRepo = appRepo;
    }

    public List<Entitlement> getEntitlementsForApp(String appName) {
        String query = "select e.* from entitlement e, application a where e.parent_application_id = a.id and a.name = ?";
        List<Entitlement> entitlements = jdbc.query(query, ENTITLEMENT_MAPPER, appName);
        return entitlements;
    }

    public List<Entitlement> getAllEntitlements() {
        return jdbc.query("select * from entitlement e", ENTITLEMENT_MAPPER);
    }

    private Entitlement getEntitlement(String parentAppName, String entName) {
        String query = "select * from application a, entitlement e where e.parent_application_id = a.id and a.name = ? and e.name = ?";
        Entitlement entitlement = (Entitlement) jdbc.queryForObject(query, ENTITLEMENT_MAPPER, parentAppName, entName);
        return entitlement;
    }

}
