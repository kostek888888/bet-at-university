package bet_at_university.database.repository;

import bet_at_university.database.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
}
