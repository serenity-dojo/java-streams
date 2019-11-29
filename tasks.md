# Tasks

The tasks in this project are grouped by modules, which start simple and get progressively more sophisticated.
By the end of the modules, you will have implemented a small project that uses a wide variety of Java Steam features to analyse, aggregate and report on transaction feeds.

The overall aim of the project is to import a bank feed and perform a number of enrichment and reporting operations. 
The format of the feed is a comma-separated list of values, in the following format:

```
| Date	        | Description	| Counterparty     | Deposit | Withdrawl | Balance |
| 24/10/2018	| GAS BILL MAY  | GAS COMPANY      | 0       | 50        | 1000    |
| 25/10/2018	| INV 000002    | CAFE VIENNA      | 0       | 5.25      | 994.75  |
| 27/10/2018	| NEWSPAPER     | CORNER PRESS LTD | 0       | 15.00     | 979.75  |
| 27/10/2018	| SALARY JUNE   | EMPLOYERS INC    | 2000.00 | 0         | 2979.75 |
```

## Module 1 - Simple Stream Aggregation
The first module starts off simple: you will perform a number of manipulations on a stream of transaction feeds in the form of Java objects.
For this module, you will be working with a stream of [TransactionFeedEntry](src/main/java/streams/exercises/moneymanager/domain/TransactionFeedEntry.java) objects.
The [CountTransactions](src/main/java/streams/exercises/moneymanager/CountTransactions.java) class contains a skeleton class for you to work with.
First, complete the `from()` method in this class so that it stores the feed it is passed for use later on. 
Next, implement each of the three methods (getNumberOfTransactions(), getTransactionVolume() and getTotalTransactions()). These methods should:
  - Count the total number of transations in the feed
  - Find the total volume of money involved in all the transactions, regardless of whether they are deposits or withdrawls.
  - Find the net amount transacted over the period - the difference between the deposits and withdrawls.

The unit tests you need to make pass are defined in [Module_1](src/test/java/streams/exercises/moneymanager/tasks/Module_1.java).


## Module 2 - Minimum and maximum values
In this module you will implement a new class, [MinMaxTransactions](src/main/java/streams/exercises/moneymanager/MinMaxTransactions.java),
which will find the biggest and smallest deposits and withdrawals, as well as the largest overall transaction. 
Note that these methods return an `OptionalDouble`, which should be empty if there are no transactions of the requested type.

The tasks in this module are:
    - Find the smallest deposit by implementing the `getSmallestDeposit()` method.
    - Find the largest deposit by implementing the `getLargestDeposit()` method.
    - Find the smallest withdrawal by implementing the `getSmallestWithdrawal()` method.
    - Find the largest withdrawal by implementing the `getLargestWithdrawal()` method.
    - Find the largest overall transaction by implementing the `getLargestTransaction()` method. Note that this method should return a positive number for deposits and a negative number for withdrawals.

The unit tests you need to make pass are defined in [Module_2](src/test/java/streams/exercises/moneymanager/tasks/Module_2.java).

## Module 3 - Finding transactions
In this module you will implement a new class, [FindASampleTransaction](src/main/java/streams/exercises/moneymanager/FindASampleTransaction.java), 
which will find an example of a transaction matching certain criteria. 

The tasks in this module are:
    - Find an example of a transaction that exceeds a certain amount (either a deposit or a withdrawal)
    - Find an example of a transaction for a given counterparty that exceeds a certain amount (either a deposit or a withdrawal)

The unit tests you need to make pass are defined in [Module_4](src/test/java/streams/exercises/moneymanager/tasks/Module_3.java).

## Module 4 - Grouping by counterparty
The transactions we receive have both a description and a counterparty. 
The counterparty is the parson or company who is paying or being paid. 
In this module, you will group transactions by counterparty, to find the list of counterparties in a set of transactions. 
You will implement a new class, [SpendingPerCounterparty](src/main/java/streams/exercises/moneymanager/SpendingPerCounterparty.java),
For the purpose of this exercises, we are interested only in spending, not withdrawals.

The tasks in this module are:
    - Find the list of distinct counterparties, in alphabetical order
    - Find all the transactions for a given counterparty

The unit tests you need to make pass are defined in [Module_4](src/test/java/streams/exercises/moneymanager/tasks/Module_4.java).


## Module 5 - Checking for invalid transactions
In this module we will check for invalid transactions. 
The counterparty is the parson or company who is paying or being paid. 
You will implement a new class to do this, [SpendingPerCounterparty](src/main/java/streams/exercises/moneymanager/TransactionChecker.java),
You will need to use the anyMatches() stream method.

The tasks in this module are:
    - Check for transactions with a deposit and withdrawal value of zero
    - Check for transactions with a negative deposit or withdrawal value
    - Check for transactions with a missing or empty counterparty

The unit tests you need to make pass are defined in [Module_5](src/test/java/streams/exercises/moneymanager/tasks/Module_5.java).
