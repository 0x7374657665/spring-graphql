package us.stevenrussell.spgql.resolvers.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.EntitlementRepository;
import us.stevenrussell.spgql.types.CreateEntitlementInput;
import us.stevenrussell.spgql.types.Entitlement;

@Component
public class EntitlementMutations implements GraphQLMutationResolver {

    private EntitlementRepository entRepo;

    @Autowired
    public EntitlementMutations(EntitlementRepository entRepo) {
        this.entRepo = entRepo;
    }

    @Secured("ROLE_ADMIN")
    public Entitlement createEntitlement(CreateEntitlementInput newEntitlementData) {
        Entitlement created = entRepo.createEntitlement(newEntitlementData);
        return created;
    }

}
