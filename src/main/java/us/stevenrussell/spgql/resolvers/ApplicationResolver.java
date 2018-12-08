package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.RoleRepository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;
import us.stevenrussell.spgql.types.Role;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ApplicationResolver implements GraphQLResolver<Application> {

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private RoleRepository roleRepo;

    private static final BeanPropertyRowMapper ROLE_MAPPER = new BeanPropertyRowMapper(Role.class);
    private static final BeanPropertyRowMapper ENTITLEMENT_MAPPER = new BeanPropertyRowMapper(Entitlement.class);

    public CompletableFuture<Role> getProvisionerRole(Application application, DataFetchingEnvironment dfe) {
        DataLoader<Long, Role> provisionersForApplicationsDataLoader = ((GraphQLContext) dfe.getContext())
                .getDataLoaderRegistry()
                .get()
                .getDataLoader("provisionersForApplicationsDataLoader");

        CompletableFuture<Role> roleCF = provisionersForApplicationsDataLoader.load(application.getId());

        return roleCF;
    }

    public CompletableFuture<List<Entitlement>> getEntitlements(Application application, DataFetchingEnvironment dfe) {
        DataLoader<Long, List<Entitlement>> entitlementsByApplicationDataLoader = ((GraphQLContext) dfe.getContext())
                .getDataLoaderRegistry()
                .get()
                .getDataLoader("entitlementsByApplicationDataLoader");

        CompletableFuture<List<Entitlement>> entitlementsCF = entitlementsByApplicationDataLoader.load(application.getId());

        return entitlementsCF;
    }
}
