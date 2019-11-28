package streams.exercises.moneymanager;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;
import streams.exercises.moneymanager.domain.UnfinishedExerciseException;

import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class MinMaxTransactions {
    public static MinMaxTransactions from(Stream<TransactionFeedEntry> transactionFeed) {
        return new MinMaxTransactions();
    }

    public OptionalDouble getSmallestDeposit() {
        throw new UnfinishedExerciseException();
    }

    public OptionalDouble getLargestDeposit() {
        throw new UnfinishedExerciseException();
    }

    public Optional<Double> getLargestTransaction() {
        throw new UnfinishedExerciseException();
    }

    public OptionalDouble getSmallestWithdrawal() {
        throw new UnfinishedExerciseException();
    }

    public OptionalDouble getLargestWithdrawal() {
        throw new UnfinishedExerciseException();
    }
}
