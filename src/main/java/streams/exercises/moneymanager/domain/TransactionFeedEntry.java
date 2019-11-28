package streams.exercises.moneymanager.domain;

import java.time.LocalDate;

public class TransactionFeedEntry {
    private final LocalDate localDate;
    private final String description;
    private final String counterparty;
    private final double withdrawal;
    private final double deposit;
    private final double balance;

    public TransactionFeedEntry(LocalDate localDate, String description, String counterparty, double withdrawal, double deposit, double balance) {
        this.localDate = localDate;
        this.description = description;
        this.counterparty = counterparty;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
        this.balance = balance;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getDescription() {
        return description;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public double getBalance() {
        return balance;
    }

    public double getDeposit() {
        return deposit;
    }

    public double getWithdrawal() {
        return withdrawal;
    }

    public boolean isDeposit() {
        return withdrawal == 0;
    }

    public boolean isWithdrawl() {
        return deposit == 0;
    }

    public static TransactionFeedEntry trx(String description, String counterparty, double withdrawal, double deposit, double balance) {
        return new TransactionFeedEntry(LocalDate.now(), description, counterparty, withdrawal, deposit, balance);
    }

    public static TransactionFeedEntry trx(LocalDate date, String description, String counterparty, double withdrawal, double deposit, double amount, double balance) {
        return new TransactionFeedEntry(date, description, counterparty, withdrawal, deposit, balance);
    }

}
