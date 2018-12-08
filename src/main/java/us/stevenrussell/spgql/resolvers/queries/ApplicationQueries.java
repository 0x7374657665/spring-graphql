package us.stevenrussell.spgql.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Application;

import java.util.List;

@Component
public class ApplicationQueries implements GraphQLQueryResolver {

    private JdbcTemplate jdbc;

    private static final BeanPropertyRowMapper APP_MAPPER = new BeanPropertyRowMapper(Application.class);

    @Autowired
    public ApplicationQueries(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Application application(String name) {
        return (Application) jdbc.queryForObject("select * from application where name = ?", new String[]{name},APP_MAPPER);
    }

    public List<Application> applications() {
        return jdbc.query("select * from application", APP_MAPPER);
    }

}
