package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.types.Application;

@Component
public class Query implements GraphQLQueryResolver {

    public Application application(String name) {
        return new Application();
    }
}
