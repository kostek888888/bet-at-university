package bet_at_university.database.repository;

import bet_at_university.database.model.Matches;
import org.springframework.data.repository.CrudRepository;

public interface MatchesRepository extends CrudRepository<Matches, Long> {
}
