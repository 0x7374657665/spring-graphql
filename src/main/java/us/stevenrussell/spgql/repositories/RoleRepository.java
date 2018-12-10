package us.stevenrussell.spgql.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Role;

import static us.stevenrussell.spgql.repositories.TypeMappers.ROLE_MAPPER;

@Repository
public class RoleRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public RoleRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Role getProvisionerRoleForApp(String appName) {
        String query = new StringBuilder()
                .append("select r.* from application a, role r ")
                .append(" where a.provisioner_role_id = r.id   ")
                .append("   and a.name = ?                     ")
                .toString();

        Role role = (Role) jdbc.queryForObject(query, ROLE_MAPPER, appName);
        return role;
    }


}
