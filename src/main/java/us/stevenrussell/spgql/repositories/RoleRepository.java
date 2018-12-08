package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Role;

import java.util.Collections;
import java.util.List;

@Repository
public class RoleRepository {

    private JdbcTemplate jdbc;

    private static final BeanPropertyRowMapper ROLE_MAPPER = new BeanPropertyRowMapper(Role.class);


    @Autowired
    public RoleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Role> getRolesForApplicationIds(List<Long> applicationIds) {
        String query = new StringBuilder()
                .append("select * from application a, role r  ")
                .append(" where a.provisioner_role_id = r.id  ")
                .append("   and a.id in (                     ")
                .append(String.join(", ", Collections.nCopies(applicationIds.size(), "?")))
                .append(" )")
                .toString();

        List<Role> roles = jdbc.query(query, ROLE_MAPPER, applicationIds.toArray());

        return roles;
    }
}
