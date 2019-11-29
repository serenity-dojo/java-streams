package streams.exercises.moneymanager.solutions;

import org.assertj.core.groups.Tuple;
import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class SpendingPerCounterparty {
    private Stream<TransactionFeedEntry> transactionFeed;

    private SpendingPerCounterparty(Stream<TransactionFeedEntry> transactionFeed) {
        this.transactionFeed = transactionFeed;
    }

    public static SpendingPerCounterparty from(Stream<TransactionFeedEntry> transactionFeed) {
        return new SpendingPerCounterparty(transactionFeed);
    }

    public List<String> getCounterparties() {
        return transactionFeed
                .filter(TransactionFeedEntry::isWithdrawl)
                .map(TransactionFeedEntry::getCounterparty)
                .distinct()
                .sorted()
                .collect(toList());
    }

    public List<TransactionFeedEntry> getPurchasesFrom(String counterParty) {
        return transactionFeed
                .filter(entry -> entry.getCounterparty().equals(counterParty))
                .collect(toList());
    }

    public double getTotalSpendingFor(String counterParty) {
        return transactionFeed
                .filter(entry -> entry.getCounterparty().equals(counterParty))
                .mapToDouble(entry -> entry.getWithdrawal() - entry.getDeposit() )
                .sum();
    }
}
