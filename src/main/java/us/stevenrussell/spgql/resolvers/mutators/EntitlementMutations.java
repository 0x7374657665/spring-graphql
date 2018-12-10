package us.stevenrussell.spgql.resolvers.mutators;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.EntitlementRepository;
import us.stevenrussell.spgql.types.Entitlement;
import us.stevenrussell.spgql.types.EntitlementDisplayInput;

@Component
public class EntitlementMutations implements GraphQLMutationResolver {

    private EntitlementRepository entRepo;

    @Autowired
    public EntitlementMutations(EntitlementRepository entRepo) {
        this.entRepo = entRepo;
    }

    public Entitlement createEntitlement(EntitlementDisplayInput newEntitlementData) {
        Entitlement created = entRepo.createEntitlement(newEntitlementData);
        return created;
    }

    public Entitlement updateEntitlement(EntitlementDisplayInput entitlementUpdates) {
        Entitlement updated = entRepo.updateEntitlement(entitlementUpdates);
        return  updated;
    }

    public Entitlement deleteEntitlement(String parentAppName, String entitlementName) {
        Entitlement deleted = entRepo.softDelete(parentAppName,entitlementName);
        return deleted;
    }
}
