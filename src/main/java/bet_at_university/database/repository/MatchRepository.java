package bet_at_university.database.repository;

import bet_at_university.database.model.Match;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
}
