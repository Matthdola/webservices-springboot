package microservices.book.social_multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * This class represents a Multiplication (a * b) in our application
 */
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class Multiplication {
    // Both factors
    private final int factorA;
    private final int factorB;

    // The reseult of the operation A*B
    //private int result;

    public Multiplication() {
        this(0,0);
    }
}
