package us.stevenrussell.spgql.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    GraphQLSchema getSchema() {
        GraphQLSchema graphQLSchema = GraphQLSchema.newSchema().build();
        return graphQLSchema;
    }
}
