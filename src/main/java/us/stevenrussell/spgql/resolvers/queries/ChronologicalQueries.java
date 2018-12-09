package us.stevenrussell.spgql.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import us.stevenrussell.spgql.repositories.ChronologicalRepository;
import us.stevenrussell.spgql.types.Chronological;

import java.time.LocalDate;
import java.util.List;

@Component
public class ChronologicalQueries implements GraphQLQueryResolver {

    private ChronologicalRepository chronoRepo;

    public ChronologicalQueries(ChronologicalRepository chronoRepo) {this.chronoRepo = chronoRepo;}

    public List<Chronological> updatedSince(LocalDate since) {
        return chronoRepo.getChronologicalItemsUpdatedSince(since);
    }


}
