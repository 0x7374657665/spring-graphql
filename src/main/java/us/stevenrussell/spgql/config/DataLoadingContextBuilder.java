package us.stevenrussell.spgql.config;

import graphql.servlet.GraphQLContext;
import graphql.servlet.GraphQLContextBuilder;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.EntitlementRepository;
import us.stevenrussell.spgql.repositories.RoleRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class DataLoadingContextBuilder implements GraphQLContextBuilder {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private EntitlementRepository entitlementRepo;

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        GraphQLContext context = new GraphQLContext(httpServletRequest, httpServletResponse);
        context.setDataLoaderRegistry(buildDataLoaderRegistry());
        return context;
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        GraphQLContext context = new GraphQLContext(session, handshakeRequest);
        context.setDataLoaderRegistry(buildDataLoaderRegistry());
        return context;

    }

    @Override
    public GraphQLContext build() {
        GraphQLContext context = new GraphQLContext();
        context.setDataLoaderRegistry(buildDataLoaderRegistry());
        return context;
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry dlRegistry = new DataLoaderRegistry();

        dlRegistry.register(
                "provisionersForApplicationsDataLoader",
                DataLoader.newDataLoader(
                        (List<Long> applicationIds) -> CompletableFuture.supplyAsync(
                                () -> roleRepo.getRolesForApplicationIds(applicationIds)
                        )
                )
        );

        return dlRegistry;
    }
}
