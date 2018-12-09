package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;

import java.util.List;

@Repository
public class ApplicationRepository {

    private JdbcTemplate jdbc;

    private static final BeanPropertyRowMapper APPLICATION_MAPPER = new BeanPropertyRowMapper(Application.class);

    @Autowired
    public ApplicationRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Application getApplicationForEntitlement(Entitlement entitlement) {
        String query = "select * from application a where a.id = ?";
        Application app = (Application) jdbc.queryForObject(query, APPLICATION_MAPPER, entitlement.getParentApplicationId());
        return app;
    }

    public Application getApplicationByName(String name) {
        return (Application) jdbc.queryForObject("select * from application where name = ?", new String[]{name}, APPLICATION_MAPPER);
    }

    public List<Application> getAllApplications() {
        return jdbc.query("select * from application", APPLICATION_MAPPER);
    }
}
