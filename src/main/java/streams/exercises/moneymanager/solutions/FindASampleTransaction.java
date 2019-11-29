package streams.exercises.moneymanager.solutions;

import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
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
        return transactionFeed
                .filter(entry -> entry.getDeposit() > amount || entry.getWithdrawal() > amount)
                .findFirst();
    }

    public Optional<TransactionFeedEntry> forACounterpartyThatExceed(String counterParty, double amount) {
        return transactionFeed
                .filter(entry -> entry.getCounterparty().equals(counterParty))
                .filter(entry -> entry.getDeposit() > amount || entry.getWithdrawal() > amount)
                .findFirst();
    }

}
