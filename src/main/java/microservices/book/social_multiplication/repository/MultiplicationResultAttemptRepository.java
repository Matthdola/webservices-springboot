package microservices.book.social_multiplication.repository;

import microservices.book.social_multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MultiplicationResultAttemptRepository extends CrudRepository<MultiplicationResultAttempt, Long> {

    /**
     *
     * @param userAlias
     * @return the latest 5 attempts for a given user, identified by their userAlias
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}
