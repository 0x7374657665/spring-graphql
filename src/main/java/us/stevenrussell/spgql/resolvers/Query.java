package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Application;

import java.util.List;
import java.util.Map;

@Component
public class Query implements GraphQLQueryResolver {

    private JdbcTemplate jdbc;

    @Autowired
    public Query(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Application application(String name) {
        Application app = new Application();
        app.setName("steve_app");
        app.setDisplayName("Steve's App");
        return app;
    }

    public List<Application> applications() {
        return jdbc.query("select * from application", new BeanPropertyRowMapper(Application.class));
    }

}
