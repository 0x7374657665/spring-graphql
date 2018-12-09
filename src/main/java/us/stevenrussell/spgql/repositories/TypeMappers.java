package us.stevenrussell.spgql.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;
import us.stevenrussell.spgql.types.Role;

public class TypeMappers {

    public static final BeanPropertyRowMapper APPLICATION_MAPPER = new BeanPropertyRowMapper(Application.class);
    public static final BeanPropertyRowMapper ENTITLEMENT_MAPPER = new BeanPropertyRowMapper(Entitlement.class);
    public static final BeanPropertyRowMapper ROLE_MAPPER = new BeanPropertyRowMapper(Role.class);
}
