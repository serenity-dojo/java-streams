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

@DisplayName("Calculate spending per counterparty")
class Module4Specs {

    List<TransactionFeedEntry> NO_TRANSACTIONS = new ArrayList<>();

    @DisplayName("Task 4.1: Find the list of counterparties")
    @Nested
    class FindAllTheCounterparties {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            assertThat(SpendingPerCounterparty.from(NO_TRANSACTIONS.stream()).getCounterparties()).isEmpty();
        }

        @Test
        @DisplayName("When there is one counterparty")
        void forTransactionsFromOneCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 15.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getCounterparties()).containsExactly("Coffee and Co");
        }

        @Test
        @DisplayName("When there are several counterparties")
        void forTransactionsFromSeveralCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getCounterparties())
                    .containsExactly("Coffee and Co", "Laundry Inc", "TFL");
        }
    }

    @DisplayName("Task 4.2: Find the transactions for a given counterparty")
    @Nested
    class FindTransactionsForACounterparty {
        @Test
        @DisplayName("When there are no matching transactions")
        void forTransactionsFromNoMatchingCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getPurchasesFrom("No Such Counterparty")).isEmpty();
        }


        @Test
        @DisplayName("When there are deposits and withdrawals")
        void whenThereAreBothDepositsAndWithdrawals() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Salary", "Employers Inc", 0, 1000, 1990),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getCounterparties())
                    .containsExactly("Coffee and Co", "Laundry Inc", "TFL");
        }

        @Test
        @DisplayName("When there are several matching transactions")
        void forTransactionsFromSeveralCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getPurchasesFrom("Coffee and Co"))
                    .hasSize(2)
                    .allMatch(entry -> entry.getCounterparty().equals("Coffee and Co"));
        }
    }

    @DisplayName("Task 4.3: Find the total transactions for a given counterparty")
    @Nested
    class FindTotalTransactionsForACounterparty {
        @Test
        @DisplayName("Total should be zero when there are no matching transactions")
        void forTransactionsFromNoMatchingCounterparty() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getTotalSpendingFor("No Such Counterparty")).isEqualTo(0);
        }

        @Test
        @DisplayName("Total should include all the transactions for a given counterparty")
        void forTransactionsFromoMatchingCounterparties() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getTotalSpendingFor("Coffee and Co")).isEqualTo(20.0);
        }

        @Test
        @DisplayName("Total should include both withdrawals and deposits")
        void forTransactionsFromoMatchingCounterpartiesWithWithdrawalsAndDeposits() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Oyster Card", "TFL", 25.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 10.0, 0, 10.0),
                            trx("Coffee", "Coffee and Co", 0, 5.0, 10.0),
                            trx("Dry Cleaning", "Laundry Inc", 5.0, 0, 10.0)
                    );

            assertThat(SpendingPerCounterparty.from(transactionFeed).getTotalSpendingFor("Coffee and Co")).isEqualTo(15.0);
        }

    }
}