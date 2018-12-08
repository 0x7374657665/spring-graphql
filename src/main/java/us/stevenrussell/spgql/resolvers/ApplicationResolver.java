package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;
import us.stevenrussell.spgql.types.Role;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ApplicationResolver implements GraphQLResolver<Application> {

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
