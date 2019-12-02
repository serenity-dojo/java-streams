package streams.exercises.moneymanager.solutions;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.Arrays;
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

    private final Predicate<TransactionFeedEntry> HAS_ZERO_VALUE = (entry -> entry.getDeposit() == 0 && entry.getWithdrawal() == 0);
    private final Predicate<TransactionFeedEntry> HAS_NEGATIVE_VALUES= (entry -> entry.getDeposit() < 0 || entry.getWithdrawal() < 0);
    private final Predicate<TransactionFeedEntry> HAS_MISSING_COUNTERPARTY= (entry -> entry.getCounterparty() == null || entry.getCounterparty().trim().isEmpty());

    private final List<Predicate<TransactionFeedEntry>> RULES = asList(
            HAS_ZERO_VALUE, HAS_NEGATIVE_VALUES, HAS_MISSING_COUNTERPARTY
    );

    public boolean hasInvalidTransactions() {
        return transactionFeed.anyMatch(this::violatesConstraints);
    }

    private boolean violatesConstraints(TransactionFeedEntry entry) {
        return RULES.stream().anyMatch(
                rule -> rule.test(entry)
        );
    }

}
