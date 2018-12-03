package us.stevenrussell.spgql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DBConfig {

    @Bean
    public JdbcTemplate getJdbc(@Autowired DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
