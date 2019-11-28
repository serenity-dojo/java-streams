package streams.exercises.moneymanager;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;
import streams.exercises.moneymanager.domain.UnfinishedExerciseException;

import java.util.stream.Stream;

public class CountTransactions {
    public static CountTransactions from(Stream<TransactionFeedEntry> transactionFeed) {
        return new CountTransactions();
    }

    public long getNumberOfTransactions() {
        throw new UnfinishedExerciseException();
    }

    public double getTransactionVolume() {
        throw new UnfinishedExerciseException();
    }

    public double getTotalTransactions() {
        throw new UnfinishedExerciseException();
    }
}
