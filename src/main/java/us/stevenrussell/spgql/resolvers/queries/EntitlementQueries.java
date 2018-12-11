package us.stevenrussell.spgql.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.EntitlementRepository;
import us.stevenrussell.spgql.types.Entitlement;

import java.util.List;

@Component
public class EntitlementQueries implements GraphQLQueryResolver {

    private final EntitlementRepository entRepo;

    @Autowired
    public EntitlementQueries(EntitlementRepository entRepo) {
        this.entRepo = entRepo;
    }

    public List<Entitlement> entitlements() {
        return entRepo.getAllEntitlements();
    }

    public List<Entitlement> entitlementsByName(String entitlementName) {
        return entRepo.getEntitlementsByName(entitlementName);
    }
}
