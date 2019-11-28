package streams.exercises.moneymanager.solutions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import streams.exercises.moneymanager.domain.TransactionFeedEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static streams.exercises.moneymanager.domain.TransactionFeedEntry.trx;

@DisplayName("Calculate minimum and maximum deposits and withdrawals in a feed")
class Module2Specs {

    @DisplayName("Task 1: Find the smallest deposit")
    @Nested
    class FindTheSmallestDeposit {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            List<TransactionFeedEntry> transactionFeedEntries = new ArrayList<>();
            Stream<TransactionFeedEntry> transactionFeed = transactionFeedEntries.stream();

            assertThat(MinMaxTransactions.from(transactionFeed).getSmallestDeposit().isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single withdrawal")
        void forOneWithdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and Co", 10.0, 0, 10.0));

            OptionalDouble smallestDeposit = MinMaxTransactions.from(transactionFeed).getSmallestDeposit();
            assertThat(smallestDeposit.isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single deposit")
        void forOneDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Salary May", "Coffee and Co", 0, 500, 500));

            OptionalDouble smallestDeposit = MinMaxTransactions.from(transactionFeed).getSmallestDeposit();
            assertThat(smallestDeposit.isPresent()).isTrue();
            assertThat(smallestDeposit.getAsDouble()).isEqualTo(500.0);
        }

        @Test
        @DisplayName("When there are many deposits")
        void forManyDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee Macbine", "Coffee and Co", 1000.0, 0, 10.0),
                            trx("Salary May", "Coffee and Co", 0, 500, 500),
                            trx("Salary June", "Coffee and Co", 0, 800, 500)
                    );

            OptionalDouble smallestDeposit = MinMaxTransactions.from(transactionFeed).getSmallestDeposit();
            assertThat(smallestDeposit.isPresent()).isTrue();
            assertThat(smallestDeposit.getAsDouble()).isEqualTo(500.0);
        }
    }

    @DisplayName("Task 2: Find the largest deposit")
    @Nested
    class FindTheLargesttDeposit {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            List<TransactionFeedEntry> transactionFeedEntries = new ArrayList<>();
            Stream<TransactionFeedEntry> transactionFeed = transactionFeedEntries.stream();

            assertThat(MinMaxTransactions.from(transactionFeed).getLargestDeposit().isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single withdrawal")
        void forOneWithdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and Co", 10.0, 0, 10.0));

            OptionalDouble smallestDeposit = MinMaxTransactions.from(transactionFeed).getLargestDeposit();
            assertThat(smallestDeposit.isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single deposit")
        void forOneDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Salary May", "Coffee and Co", 0, 500, 500));

            OptionalDouble smallestDeposit = MinMaxTransactions.from(transactionFeed).getLargestDeposit();
            assertThat(smallestDeposit.isPresent()).isTrue();
            assertThat(smallestDeposit.getAsDouble()).isEqualTo(500.0);
        }

        @Test
        @DisplayName("When there are many deposits")
        void forManyDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee Macbine", "Coffee and Co", 1000.0, 0, 10.0),
                            trx("Salary May", "Coffee and Co", 0, 500, 500),
                            trx("Salary June", "Coffee and Co", 0, 800, 500)
                    );

            OptionalDouble smallestDeposit = MinMaxTransactions.from(transactionFeed).getLargestDeposit();
            assertThat(smallestDeposit.isPresent()).isTrue();
            assertThat(smallestDeposit.getAsDouble()).isEqualTo(800.0);
        }
    }


    @DisplayName("Task 3: Find the smallest withdrawal")
    @Nested
    class FindTheSmallestWithdrawal {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            List<TransactionFeedEntry> transactionFeedEntries = new ArrayList<>();
            Stream<TransactionFeedEntry> transactionFeed = transactionFeedEntries.stream();

            assertThat(MinMaxTransactions.from(transactionFeed).getSmallestWithdrawal().isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single deposit")
        void forOneWithdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Salary May", "Coffee and Co", 0, 500, 500));

            OptionalDouble smallestWithdrawal= MinMaxTransactions.from(transactionFeed).getSmallestWithdrawal();
            assertThat(smallestWithdrawal.isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single withdrawal")
        void forOneDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and Co", 10.0, 0, 10.0));

            OptionalDouble smallestWithdrawal = MinMaxTransactions.from(transactionFeed).getSmallestWithdrawal();
            assertThat(smallestWithdrawal.isPresent()).isTrue();
            assertThat(smallestWithdrawal.getAsDouble()).isEqualTo(10.0);
        }

        @Test
        @DisplayName("When there are many withdrawals")
        void forManyDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 5.0, 0, 10.0),
                            trx("Newspapers", "Corner Shop", 10.0, 0, 10.0),
                            trx("Salary May", "Coffee and Co", 0, 500, 500),
                            trx("Salary June", "Coffee and Co", 0, 800, 500)
                    );

            OptionalDouble smallestWithdrawal = MinMaxTransactions.from(transactionFeed).getSmallestWithdrawal();
            assertThat(smallestWithdrawal.isPresent()).isTrue();
            assertThat(smallestWithdrawal.getAsDouble()).isEqualTo(5.0);
        }
    }

    @DisplayName("Task 4: Find the largest withdrawal")
    @Nested
    class FindTheLargestWithdrawal {

        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            List<TransactionFeedEntry> transactionFeedEntries = new ArrayList<>();
            Stream<TransactionFeedEntry> transactionFeed = transactionFeedEntries.stream();

            assertThat(MinMaxTransactions.from(transactionFeed).getLargestWithdrawal().isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single deposit")
        void forOneWithdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Salary May", "Coffee and Co", 0, 500, 500));

            OptionalDouble smallestWithdrawal= MinMaxTransactions.from(transactionFeed).getLargestWithdrawal();
            assertThat(smallestWithdrawal.isPresent()).isFalse();
        }

        @Test
        @DisplayName("When there is a single withdrawal")
        void forOneDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(trx("Coffee", "Coffee and Co", 10.0, 0, 10.0));

            OptionalDouble smallestWithdrawal = MinMaxTransactions.from(transactionFeed).getLargestWithdrawal();
            assertThat(smallestWithdrawal.isPresent()).isTrue();
            assertThat(smallestWithdrawal.getAsDouble()).isEqualTo(10.0);
        }

        @Test
        @DisplayName("When there are many withdrawals")
        void forManyDeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 5.0, 0, 10.0),
                            trx("Newspapers", "Corner Shop", 10.0, 0, 10.0),
                            trx("Salary May", "Coffee and Co", 0, 500, 500),
                            trx("Salary June", "Coffee and Co", 0, 800, 500)
                    );

            OptionalDouble smallestWithdrawal = MinMaxTransactions.from(transactionFeed).getLargestWithdrawal();
            assertThat(smallestWithdrawal.isPresent()).isTrue();
            assertThat(smallestWithdrawal.getAsDouble()).isEqualTo(10.0);
        }
    }

    @DisplayName("Task 5: Find the largest overall transaction")
    @Nested
    class FindTheLargestTransaction {
        @Test
        @DisplayName("When there are no transactions")
        void forNoTransactions() {
            List<TransactionFeedEntry> transactionFeedEntries = new ArrayList<>();
            Stream<TransactionFeedEntry> transactionFeed = transactionFeedEntries.stream();

            assertThat(MinMaxTransactions.from(transactionFeed).getLargestTransaction().isPresent()).isFalse();
        }

        @Test
        @DisplayName("When the largest transaction is a withdrawal we should get a negative value")
        void whenTheLargestIsAWidtdrawal() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee Machine", "Coffee and Co", 1200, 0, 10.0),
                            trx("Newspapers", "Corner Shop", 10.0, 0, 10.0),
                            trx("Salary May", "Coffee and Co", 0, 500, 500),
                            trx("Salary June", "Coffee and Co", 0, 800, 500)
                    );

            Optional<Double> smallestWithdrawal = MinMaxTransactions.from(transactionFeed).getLargestTransaction();
            assertThat(smallestWithdrawal.isPresent()).isTrue();
            assertThat(smallestWithdrawal.get()).isEqualTo(-1200.0);
        }

        @Test
        @DisplayName("When the largest transaction is a deposit we should get a positive value")
        void whenTheLargestIsADeposit() {
            Stream<TransactionFeedEntry> transactionFeed =
                    Stream.of(
                            trx("Coffee", "Coffee and Co", 5.0, 0, 10.0),
                            trx("Newspapers", "Corner Shop", 10.0, 0, 10.0),
                            trx("Salary May", "Coffee and Co", 0, 500, 500),
                            trx("Salary June", "Coffee and Co", 0, 800, 500)
                    );

            Optional<Double> smallestWithdrawal = MinMaxTransactions.from(transactionFeed).getLargestTransaction();
            assertThat(smallestWithdrawal.isPresent()).isTrue();
            assertThat(smallestWithdrawal.get()).isEqualTo(800.0);
        }
    }
}