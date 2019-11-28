package streams.exercises.moneymanager.solutions;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

public class MinMaxTransactions {
    private Stream<TransactionFeedEntry> transactionFeed;

    private MinMaxTransactions(Stream<TransactionFeedEntry> transactionFeed) {
        this.transactionFeed = transactionFeed;
    }

    public static MinMaxTransactions from(Stream<TransactionFeedEntry> transactionFeed) {
        return new MinMaxTransactions(transactionFeed);
    }

    public OptionalDouble getSmallestDeposit() {
        return transactionFeed.filter(TransactionFeedEntry::isDeposit)
                              .mapToDouble(TransactionFeedEntry::getDeposit).min();
    }

    public OptionalDouble getLargestDeposit() {
        return transactionFeed.filter(TransactionFeedEntry::isDeposit)
                .mapToDouble(TransactionFeedEntry::getDeposit).max();
    }

    public Optional<Double> getLargestTransaction() {
        return transactionFeed.map(entry -> (entry.isDeposit() ? entry.getDeposit() : entry.getWithdrawal() * -1))
                              .max(Comparator.comparingDouble(Math::abs));
    }

    public OptionalDouble getSmallestWithdrawal() {
        return transactionFeed.filter(TransactionFeedEntry::isWithdrawl)
                .mapToDouble(TransactionFeedEntry::getWithdrawal).min();
    }

    public OptionalDouble getLargestWithdrawal() {
        return transactionFeed.filter(TransactionFeedEntry::isWithdrawl)
                .mapToDouble(TransactionFeedEntry::getWithdrawal).max();
    }
}
