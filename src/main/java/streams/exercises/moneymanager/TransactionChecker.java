package streams.exercises.moneymanager;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;
import streams.exercises.moneymanager.domain.UnfinishedExerciseException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class TransactionChecker {
    private Stream<TransactionFeedEntry> transactionFeed;

    private TransactionChecker(Stream<TransactionFeedEntry> transactionFeed) {
        this.transactionFeed = transactionFeed;
    }

    public static TransactionChecker from(Stream<TransactionFeedEntry> transactionFeed) {
        return new TransactionChecker(transactionFeed);
    }

    public boolean hasInvalidTransactions() {
        throw new UnfinishedExerciseException();
    }
}