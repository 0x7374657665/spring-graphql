package us.stevenrussell.spgql.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.ApplicationRepository;
import us.stevenrussell.spgql.types.Application;

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

    public List<Application> applications() {
        return appRepo.getAllApplications();
    }

}
