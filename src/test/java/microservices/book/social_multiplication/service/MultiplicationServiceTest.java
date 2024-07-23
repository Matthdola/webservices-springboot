package microservices.book.social_multiplication.service;


import microservices.book.social_multiplication.domain.Multiplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MultiplicationServiceTest {
    @MockBean
    private RandomGeneratorService randomGeneratorService;

    @Autowired
    private MultiplicationService multiplicationService;

    @Test
    public void createRandomMultiplicationTest(){
        // Given (out mocked Random Generator service will return first 50, then 30
        given(randomGeneratorService.generateRandomFactor()).
                willReturn(50, 30);

        // When
        Multiplication multiplication = multiplicationService.createRandomMultiplication();

        // Then
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorA()).isEqualTo(30);
        assertThat(multiplication.getFactorA()).isEqualTo(1500);
    }
}
