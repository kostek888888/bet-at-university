package bet_at_university.database.repository;

import bet_at_university.database.model.Bet;
import org.springframework.data.repository.CrudRepository;

public interface BetRepository extends CrudRepository<Bet, Long> {

}
