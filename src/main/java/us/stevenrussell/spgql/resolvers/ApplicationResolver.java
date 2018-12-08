package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Role;

@Component
public class ApplicationResolver implements GraphQLResolver<Application> {

    private JdbcTemplate jdbc;

    private static final BeanPropertyRowMapper ROLE_MAPPER = new BeanPropertyRowMapper(Role.class);

    @Autowired
    public ApplicationResolver(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Role getProvisionerRole(Application application, DataFetchingEnvironment dfe) {

        DataFetchingEnvironment dfe1 = dfe;

        String query = new StringBuilder()
                .append(" select r.* from application a, role r ")
                .append(" where a.provisioner_role_id = r.id    ")
                .append(" and a.name = ?                        ")
                .toString();

        return (Role) jdbc.queryForObject(query, new String[]{application.getName()}, ROLE_MAPPER);
    }
}
