package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Entitlement;

import java.util.List;

@Component
public class EntitlementQueries implements GraphQLQueryResolver {

    private JdbcTemplate jdbc;

    private static final BeanPropertyRowMapper ENT_MAPPER = new BeanPropertyRowMapper(Entitlement.class);

    @Autowired
    public EntitlementQueries(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Entitlement entitlement(String name) {
        return (Entitlement) jdbc.queryForObject("select * from entitlement where name = ?", new String[]{name}, ENT_MAPPER);
    }

    public List<Entitlement> entitlements() {
        return jdbc.query("select * from entitlement", ENT_MAPPER);
    }
}
