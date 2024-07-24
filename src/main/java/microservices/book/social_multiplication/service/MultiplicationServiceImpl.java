package microservices.book.social_multiplication.service;

import jakarta.transaction.Transactional;
import microservices.book.social_multiplication.domain.Multiplication;
import microservices.book.social_multiplication.domain.MultiplicationResultAttempt;
import microservices.book.social_multiplication.domain.User;
import microservices.book.social_multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.social_multiplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private RandomGeneratorService randomGeneratorService;
    private MultiplicationResultAttemptRepository attemptRepository;
    private UserRepository userRepository;

    @Autowired
    public MultiplicationServiceImpl(RandomGeneratorService randomGeneratorService,
                                     MultiplicationResultAttemptRepository attemptRepository,
                                     UserRepository userRepository) {
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
        // Check if user already exists for that alias
        Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());
        // Avoid 'hack' attempts

        Assert.isTrue(!resultAttempt.isCorrect(), "You can't send an attempt marked as correct!!");

        // Checks if it's correct
        boolean correct =  resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                resultAttempt.getMultiplication().getFactorB();
        // Avoids 'hack' attempts


        // Create a copy, now setting the 'correct' field accordingly
        MultiplicationResultAttempt chechAttempt =
                new MultiplicationResultAttempt(
                        user.orElse(resultAttempt.getUser()),
                        resultAttempt.getMultiplication(),
                        resultAttempt.getResultAttempt(),
                        correct);

        // Store the attemps
        attemptRepository.save(chechAttempt);

        // Returns the result
        return correct;

    }

    @Override
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }


}
