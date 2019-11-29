package streams.exercises.moneymanager.solutions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static streams.exercises.moneymanager.domain.TransactionFeedEntry.trx;

@DisplayName("Finding sample transactions")
class Module3Specs {

    List<TransactionFeedEntry> NO_TRANSACTIONS = new ArrayList<>();

    @DisplayName("Task 3.1: Find an example of a transaction above a certain amount")
    @Nested
    class FindATransactionThatExceedsACertainThreshold {

        @Test
        @DisplayName("When there are no transactions at all")
        void forNoTransactions() {
            assertThat(FindASampleTransaction.from(NO_TRANSACTIONS.stream()).thatExceed(10.0).isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is one matching deposit")
        void whereThereIsAMatchingDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Salary", "Employers Inc", 0, 1000, 1990),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(FindASampleTransaction.from(transactionFeed).thatExceed(100).get().getDeposit()).isEqualTo(1000);
        }


        @Test
        @DisplayName("When there is one matching withdrawal")
        void whereThereIsAMatchingWithdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 15.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 5.0, 0, 10.0)
                    );

            assertThat(FindASampleTransaction.from(transactionFeed).thatExceed(12).get().getWithdrawal()).isEqualTo(15);
        }

        @Test
        @DisplayName("When there is one matching withdrawal")
        void whereThereAreAMatchingWithdrawalsAndDeposits() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Salary", "Employers Inc", 0, 1000, 1990),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(FindASampleTransaction.from(transactionFeed).thatExceed(12).get().getDeposit()).isEqualTo(1000);
        }

    }

    @DisplayName("Task 3.2: Find an example of a transaction above a certain amount for a given counterparty")
    @Nested
    class FindATransactionThatExceedsACertainThresholdForAGivenCounterparty {

        @Test
        @DisplayName("When there are no transactions at all")
        void forNoTransactions() {
            assertThat(FindASampleTransaction.from(NO_TRANSACTIONS.stream())
                    .forACounterpartyThatExceed("Coffee and Co",10.0).isPresent())
                    .isFalse();
        }

        @Test
        @DisplayName("When there no one matching deposit")
        void whereThereIsNoMatchingDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Salary", "Employers Inc", 0, 1000, 1990),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(FindASampleTransaction.from(transactionFeed)
                    .forACounterpartyThatExceed("TFL",100).isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is one matching deposit")
        void whereThereIsAMatchingDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Salary", "Employers Inc", 0, 1000, 1990),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(FindASampleTransaction.from(transactionFeed)
                    .forACounterpartyThatExceed("TFL",20).get().getWithdrawal()).isEqualTo(25);
        }


        @Test
        @DisplayName("When there are multiple matching withdrawals")
        void whereThereIsAMatchingWithdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Oyster Card", "TFL", 5.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 15.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 5.0, 0, 10.0)
                    );

            assertThat(FindASampleTransaction.from(transactionFeed).forACounterpartyThatExceed("Coffee and Co",12).get().getWithdrawal()).isEqualTo(15);
        }
    }
}