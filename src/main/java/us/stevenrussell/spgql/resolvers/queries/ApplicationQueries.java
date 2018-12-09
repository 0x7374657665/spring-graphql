package us.stevenrussell.spgql.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.ApplicationRepository;
import us.stevenrussell.spgql.types.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationQueries implements GraphQLQueryResolver {

    private ApplicationRepository appRepo;

    @Autowired
    public ApplicationQueries(ApplicationRepository appRepo) {
        this.appRepo = appRepo;
    }

    public Application application(String name) {
        return appRepo.getApplicationByName(name);

    }

    public List<Application> applications(DataFetchingEnvironment dfe) {
        return appRepo.getAllApplications();
    }

    public List<Application> applicationsUpdatedSince(LocalDate since) {
        return new ArrayList<>();
    }

}
