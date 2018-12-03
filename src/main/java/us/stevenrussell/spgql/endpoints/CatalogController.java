package us.stevenrussell.spgql.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("cat")
public class CatalogController {

    @Autowired
    private JdbcTemplate jdbc;

    @GetMapping("ents")
    @ResponseBody
    public List<String> getEntitlements() {
        return jdbc.queryForList("select name from entitlement",String.class);
    }
}
