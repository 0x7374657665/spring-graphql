package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;

import java.util.List;

import static us.stevenrussell.spgql.repositories.TypeMappers.APPLICATION_MAPPER;

@Repository
public class ApplicationRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Application getApplicationByName(String name) {
        return (Application) jdbc.queryForObject("select * from application where name = ?", APPLICATION_MAPPER, name);
    }

    public List<Application> getAllApplications() {
        return jdbc.query("select * from application", APPLICATION_MAPPER);
    }
    public Application getApplicationForEntitlement(Entitlement entitlement) {
        String query = "select a.* from application a, entitlement e where a.id = e.parent_application_id and e.id = ?";
        Application app = (Application) jdbc.queryForObject(query, APPLICATION_MAPPER, entitlement.getId());
        return app;
    }
}
