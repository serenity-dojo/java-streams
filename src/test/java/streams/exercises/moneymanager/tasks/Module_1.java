package streams.exercises.moneymanager.tasks;

import streams.exercises.moneymanager.CountTransactions;
import streams.exercises.moneymanager.domain.TransactionFeedEntry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static streams.exercises.moneymanager.domain.TransactionFeedEntry.trx;
import static org.assertj.core.api.Java6Assertions.assertThat;

@DisplayName("Module 1: Calculate total values of transactions in a feed")
class Module_1 {

    List<TransactionFeedEntry> NO_TRANSACTIONS = new ArrayList<>();

    @DisplayName("Task 1.1: Find the total number of transactions")
    @Nested
    class FindTheTotalNumberOfTransactions {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            Stream<TransactionFeedEntry> transactionFeed = NO_TRANSACTIONS.stream();

            long numberOfTransactions = CountTransactions.from(transactionFeed).getNumberOfTransactions();
            assertThat(numberOfTransactions).isEqualTo(0);
        }

        @Test
        @DisplayName("When there is a single transaction")
        void forOneTransaction() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and Co", 10.0, 0, 10.0));

            long numberOfTransactions = CountTransactions.from(transactionFeed).getNumberOfTransactions();
            assertThat(numberOfTransactions).isEqualTo(1);
        }

        @Test
        @DisplayName("When there are several transactions")
        void forManyTransactions() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and Co", 10.0, 0, 100.0),
                            trx("Taxi","Taxis R Us", 15.0, 0, 85));

            long numberOfTransactions = CountTransactions.from(transactionFeed).getNumberOfTransactions();
            assertThat(numberOfTransactions).isEqualTo(2);
        }

    }

    @DisplayName("Task 1.2: Find the volume of all transactions")
    @Nested
    class FindTheVolumeOfTransactions {

        @Test
        @DisplayName("When there are lots of debits")
        void forDebits() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and co", 10.0, 0, 990.0),
                             trx("Gas", "Gas Company", 100, 0, 890),
                             trx("Taxi", "Taxis R Us", 15.0, 0, 875));

            assertThat(CountTransactions.from(transactionFeed).getTransactionVolume()).isEqualTo(125);
        }


        @Test
        @DisplayName("When there are debits and credits")
        void forDebitsAndCredits() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee","Coffee and co", 10.0, 0, 990),
                            trx("Salary", "Employers Inc", 0, 1000, 1990),
                            trx("Taxi", "Taxis R Us",15.0, 0, 1975));

            assertThat(CountTransactions.from(transactionFeed).getTransactionVolume()).isEqualTo(1025);
        }
    }

    @DisplayName("Task 1.3: Find the sum of all transactions")
    @Nested
    class FindTheSumOfAllTransactions {

        @Test
        @DisplayName("When there are lots of debits")
        void forDebits() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee","Coffee and co",  10.0, 0, 990.0),
                            trx("Gas", "Gas Company",100, 0, 890),
                            trx("Taxi", "Taxis R Us",15.0, 0, 875));

            assertThat(CountTransactions.from(transactionFeed).getTotalTransactions()).isEqualTo(-125);
        }


        @Test
        @DisplayName("When there are debits and credits")
        void forDebitsAndCredits() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and co", 10.0, 0, 990),
                            trx("Salary","Employers Inc",  0, 1000, 1990),
                            trx("Taxi", "Taxis R Us",15.0, 0, 1975));

            assertThat(CountTransactions.from(transactionFeed).getTotalTransactions()).isEqualTo(975);
        }
    }
}
