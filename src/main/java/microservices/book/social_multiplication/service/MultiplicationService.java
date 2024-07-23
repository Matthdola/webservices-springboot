package microservices.book.social_multiplication.service;

import microservices.book.social_multiplication.domain.Multiplication;
import microservices.book.social_multiplication.domain.MultiplicationResultAttempt;

public interface MultiplicationService {

    /**
     * Create a random {@link Multiplication} object
     *
     * @return a Multiplication of randomly generated numbers factors
     */
    Multiplication createRandomMultiplication();

    /**
     *
     * @param resultAttempt
     * @return true if the attempt matches the result of the multiplication, false otherwise
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);
}
