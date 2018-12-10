package us.stevenrussell.spgql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {

    public String helloWorld() {
        return "Hello, world!";
    }
}
