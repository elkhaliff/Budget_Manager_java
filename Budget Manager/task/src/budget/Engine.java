package budget;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Engine {
    private Double balance;
    private final Map<String, Double> purchases;


    public Engine(Double balance) {
        this.balance = balance;
        purchases = new HashMap<>();
    }

    public void println(String string) { System.out.println(string); }

    public String getString(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string);
        return scanner.nextLine();
    }

    public int getInt(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string);
        return scanner.nextInt();
    }

    public double getDouble(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string);
        return scanner.nextDouble();
    }

    public void run() {
        final String MENU =
                "Choose your action:\n" +
                "1) Add income\n" +
                "2) Add purchase\n" +
                "3) Show list of purchases\n" +
                "4) Balance\n" +
                "0) Exit";

        final int ADD_INCOME = 1;
        final int ADD_PURCHASE = 2;
        final int SHOW_LIST = 3;
        final int BALANCE = 4;
        final int EXIT = 0;

        int currAction;
        do {
            currAction = getInt(MENU);

            if (currAction == ADD_INCOME) {
                setBalance();
            } else if (currAction == ADD_PURCHASE) {
                addPurchase();
            } else if (currAction == SHOW_LIST) {
                showList();
            } else if (currAction == BALANCE) {
                getBalance();
            } else if (currAction == EXIT) {
                println("\nBye!");
                System.exit(0);
            } else {
                throw new IllegalStateException("Unexpected action: " + currAction);
            }
        } while (true);
    }

    private void showList() {
        println("");
        if (purchases.isEmpty())
            println("The purchase list is empty");
        else
            purchases.forEach((purchases, price) ->
                    println(String.format("%s %s", purchases, price.toString())));
        println("");
    }

    private void setBalance() {
        println("");
        balance += getDouble("Enter income:");
        println("Income was added!\n");
    }

    private void addPurchase() {
        println("");
        String purchase = getString("Enter purchase name:");
        Double price = getDouble("Enter its price:");
        spendMoney(price);
        purchases.put(purchase, price);
        println("Purchase was added!\n");
    }

    public void spendMoney(Double price) {
        balance -= price;
        if (balance < 0) balance = 0D;
    }

    private void getBalance() {
        println(String.format("\nBalance: $%s\n", balance.toString()));
    }
}
