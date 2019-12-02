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
Next, implement each of the three methods (getNumberOfTransactions(), getTransactionVolume() and getTotalTransactions()). These methods should do the following:
    - Count the total number of transactions in the feed
    - Find the total volume of money involved in all the transactions:  This should include the total amount of money in or out, regardless of whether the transactions are deposits or withdrawals.
    - Find the net amount transacted over the period - the difference between the deposits and withdrawals.

The unit tests you need to make pass are defined in [Module_1](src/test/java/streams/exercises/moneymanager/tasks/Module_1.java).

### Hints
You can use the [count()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html#count--) method to count the number of objects in a stream.
 To find the sum of a list of numerical values, you can use the [sum()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html#sum--) method. But first, you will need to convert the stream of TransactionFeedEntry objects to a stream of doubles ([DoubleStream](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html)).

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

### Hints

You can find the minimum and maximum values in a stream using [min()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html#min--) and [max()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/DoubleStream.html#max--).

## Module 3 - Finding transactions
In this module you will implement a new class, [FindASampleTransaction](src/main/java/streams/exercises/moneymanager/FindASampleTransaction.java), 
which will find an example of a transaction matching certain criteria. 

The tasks in this module are:
    - Find an example of a transaction that exceeds a certain amount (either a deposit or a withdrawal)
    - Find an example of a transaction for a given counter-party that exceeds a certain amount (either a deposit or a withdrawal)

The unit tests you need to make pass are defined in [Module_3](src/test/java/streams/exercises/moneymanager/tasks/Module_3.java).

### Hints

You can find the first element in a stream by using the [findFirst()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#findFirst--) method. But first, you need to [filter](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#filter-java.util.function.Predicate-) the stream so that it only contains transactions that match the criteria you are interested in finding. You can see an example of the `filter()` method in action [here](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html).

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

### Hints

The [distinct()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#distinct--) method returns a stream with no duplicate entries. The [sorted()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#sorted--) method returns a stream with the elements in sorted order. 

The [sum()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/IntStream.html#sum--) method allows you to find the sum of a list of numbers. But first, you need to extract the numerical values you want to sum using the [map()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#map-java.util.function.Function-) method.

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

### Hints
The [anyMatch()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#anyMatch-java.util.function.Predicate-), [allMatch()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#allMatch-java.util.function.Predicate-) and [noneMatch()](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html#noneMatch-java.util.function.Predicate-) methods can be used to check whether elements in a stream match (or don't match) a given predicate. The trick with this exercise is to remember that a stream can only be consumed once, so you need to perform all of the checks on a given transaction at the same time. 

One way to do this is to use `anyMatch()` or `allMatch()` with a boolean method that checks the validity of a given transaction, e.g.
```java
    public boolean hasInvalidTransactions() {
        return transactionFeed.anyMatch(this::violatesConstraints);
    }
    
    private boolean violatesConstraints(TransactionFeedEntry entry) {...} 
```

