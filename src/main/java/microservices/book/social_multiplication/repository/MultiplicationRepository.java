package microservices.book.social_multiplication.repository;

import microservices.book.social_multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface allows us to save and retrive Multiplications
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
