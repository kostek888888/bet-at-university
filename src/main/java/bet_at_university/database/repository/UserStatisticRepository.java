package bet_at_university.database.repository;

import bet_at_university.database.model.UserStatistics;
import org.springframework.data.repository.CrudRepository;

public interface UserStatisticRepository extends CrudRepository<UserStatistics, Long> {
}
