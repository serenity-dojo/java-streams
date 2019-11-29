package streams.exercises.moneymanager;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;
import streams.exercises.moneymanager.domain.UnfinishedExerciseException;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SpendingPerCounterparty {
    private Stream<TransactionFeedEntry> transactionFeed;

    private SpendingPerCounterparty(Stream<TransactionFeedEntry> transactionFeed) {
        this.transactionFeed = transactionFeed;
    }

    public static SpendingPerCounterparty from(Stream<TransactionFeedEntry> transactionFeed) {
        return new SpendingPerCounterparty(transactionFeed);
    }

    public List<String> getCounterparties() {
        throw new UnfinishedExerciseException();
    }

    public List<TransactionFeedEntry> getPurchasesFrom(String counterParty) {
        throw new UnfinishedExerciseException();
    }

    public double getTotalSpendingFor(String counterParty) {
        throw new UnfinishedExerciseException();
    }
}
