package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;

@Component
public class EntitlementResolver implements GraphQLResolver<Entitlement> {

    private JdbcTemplate jdbc;

    private static final BeanPropertyRowMapper APPLICATION_MAPPER = new BeanPropertyRowMapper(Application.class);

    public Application getParentApplication(Entitlement entitlement) {

        String query = "select * from application a where a.id = ?";
        Application app = (Application) jdbc.queryForObject(query, new Long[]{entitlement.getParentApplicationId()}, APPLICATION_MAPPER);
        return app;
    }
}
