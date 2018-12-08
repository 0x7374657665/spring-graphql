package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.ApplicationRepository;
import us.stevenrussell.spgql.types.Application;
import us.stevenrussell.spgql.types.Entitlement;

@Component
public class EntitlementResolver implements GraphQLResolver<Entitlement> {

    private ApplicationRepository appRepo;

    @Autowired
    public EntitlementResolver(ApplicationRepository appRepo) {
        this.appRepo = appRepo;
    }


    public Application getParentApplication(Entitlement entitlement) {
        return appRepo.getApplicationForEntitlement(entitlement);
    }
}
