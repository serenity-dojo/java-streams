package streams.exercises.moneymanager.solutions;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.stream.Stream;

public class CountTransactions {
    private Stream<TransactionFeedEntry> transactionFeed;

    private CountTransactions(Stream<TransactionFeedEntry> transactionFeed) {
        this.transactionFeed = transactionFeed;
    }

    public static CountTransactions from(Stream<TransactionFeedEntry> transactionFeed) {
        return new CountTransactions(transactionFeed);
    }

    public long getNumberOfTransactions() {
        return transactionFeed.count();
    }

    public double getTransactionVolume() {
        return transactionFeed.mapToDouble(this::volumeOf).sum();
    }

    public double getTotalTransactions() {
        return transactionFeed.mapToDouble(this::totalOf).sum();

    }

    private double volumeOf(TransactionFeedEntry transaction) {
        return transaction.getDeposit() + transaction.getWithdrawal();
    }

    private double totalOf(TransactionFeedEntry transaction) {
        return transaction.getDeposit() - transaction.getWithdrawal();
    }
}
