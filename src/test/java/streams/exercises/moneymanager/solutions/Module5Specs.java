package streams.exercises.moneymanager.solutions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static streams.exercises.moneymanager.domain.TransactionFeedEntry.trx;

@DisplayName("Checking for invalid transactions")
class Module5Specs {

    List<TransactionFeedEntry> NO_TRANSACTIONS = new ArrayList<>();

    @DisplayName("Task 5.1: Check for the presence transactions with zero value")
    @Nested
    class FindInvalidTransactionsWithZeroValues {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            assertThat(TransactionChecker.from(NO_TRANSACTIONS.stream()).hasInvalidTransactions()).isFalse();
        }

        @Test
        @DisplayName("When all the transactions are correct")
        void forTransactionsFromOneCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 50.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 40.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 30.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isFalse();
        }

        @Test
        @DisplayName("When there is a transaction with a value of zero")
        void forTransactionsFromSeveralCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isTrue();

        }
    }

    @DisplayName("Task 5.2: Check for the presence transactions with negative value")
    @Nested
    class FindInvalidTransactionsWithNegativeValues {

        @Test
        @DisplayName("When a deposit is negative")
        void whenADepositIsNegative() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Dry Cleaning", "Laundry Inc", 0, -5, 10.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isTrue();
        }

        @Test
        @DisplayName("When a withdrawal is negative")
        void whenAWithdrawlIsNegative() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Dry Cleaning", "Laundry Inc", -5.0, 0, 10.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isTrue();
        }
    }


    @DisplayName("Task 5.3: Check for the presence transactions with no counterparty")
    @Nested
    class FindInvalidTransactionsWithEmptyCounterparties {

        @Test
        @DisplayName("When a counterparty is empty")
        void whenADepositIsNegative() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Dry Cleaning", "", 0, 5, 10.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isTrue();
        }

        @Test
        @DisplayName("When a counterparty is blank")
        void whenACounterpartyIsBlank() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Dry Cleaning", "   ", 0, 5, 10.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isTrue();
        }
        @Test
        @DisplayName("When a counterparty is null")
        void whenACounterpartyIsNull() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Dry Cleaning", null, 0, 5, 10.0)
                    );

            assertThat(TransactionChecker.from(transactionFeed).hasInvalidTransactions()).isTrue();
        }
    }
}