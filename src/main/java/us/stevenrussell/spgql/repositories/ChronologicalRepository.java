package us.stevenrussell.spgql.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Chronological;
import us.stevenrussell.spgql.types.Entitlement;
import us.stevenrussell.spgql.types.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static us.stevenrussell.spgql.repositories.TypeMappers.*;

@Repository
public class ChronologicalRepository {

    private JdbcTemplate jdbc;

    public ChronologicalRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Chronological> getChronologicalItemsUpdatedSince(LocalDate since) {
        List<Application> apps = jdbc.query("select * from application a where a.updated >= ?", APPLICATION_MAPPER, since);
        List<Entitlement> ents = jdbc.query("select * from entitlement e where e.updated >= ?", ENTITLEMENT_MAPPER, since);
        List<Role> roles = jdbc.query("select * from role r where r.updated >= ?", ROLE_MAPPER, since);

        List<Chronological> items = new ArrayList<>();
        items.addAll(apps);
        items.addAll(ents);
        items.addAll(roles);

        return items;
    }
}
