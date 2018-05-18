package bet_at_university.database.repository;

import bet_at_university.database.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
