package streams.exercises.moneymanager;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;
import streams.exercises.moneymanager.domain.UnfinishedExerciseException;

import java.util.Optional;
import java.util.stream.Stream;

public class FindASampleTransaction {
    private Stream<TransactionFeedEntry> transactionFeed;

    private FindASampleTransaction(Stream<TransactionFeedEntry> transactionFeed) {
        this.transactionFeed = transactionFeed;
    }

    public static FindASampleTransaction from(Stream<TransactionFeedEntry> transactionFeed) {
        return new FindASampleTransaction(transactionFeed);
    }

    public Optional<TransactionFeedEntry> thatExceed(double amount) {
        throw new UnfinishedExerciseException();
    }

    public Optional<TransactionFeedEntry> forACounterpartyThatExceed(String counterParty, double amount) {
        throw new UnfinishedExerciseException();
    }
}
