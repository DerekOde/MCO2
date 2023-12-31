import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactions;


    public TransactionManager() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void addTransaction(String item, double price,  LocalDateTime time) {
        Transaction transaction = new Transaction(item, price, time);
        this.transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void displayTransactionSummary() {
        System.out.println("Transaction Summary");
        System.out.println("===================");

        for (Transaction transaction : this.transactions) {
            System.out.println(transaction.getItem() + " - " + transaction.getPrice() + " - " + transaction.getTime());
        }

        System.out.println();
    }
}