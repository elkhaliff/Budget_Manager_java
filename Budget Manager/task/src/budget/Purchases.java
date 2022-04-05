package budget;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Purchases {
    private Double balance;
    Double total;
    double amount;

    private final int ALL = 5;
    private final String[] types = {"", "Food", "Clothes", "Entertainment", "Other", "All"};

    private final Map<Integer, Map<String, Double>> purchases;

    public Purchases() {
        balance = 0D;
        purchases = new HashMap<>();

        for (int i = 1; i < ALL; i++) {
            purchases.put(i, new HashMap<>());
        }
    }

    public void println(String string) { System.out.println(string); }

    public String getString(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string);
        return scanner.nextLine();
    }

    public double getDouble(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string);
        return scanner.nextDouble();
    }

    private void spendMoney(Double price) {
        balance -= price;
        if (balance < 0) balance = 0D;
    }

    public void setBalance() {
        balance += getDouble("\nEnter income:");
        println("Income was added!");
    }

    public void getBalance() {
        println(String.format("\nBalance: $%s\n", balance.toString()));
    }

    public void addPurchase(int type) {
        String purchase = getString("\nEnter purchase name:");
        Double price = getDouble("Enter its price:");
        spendMoney(price);
        purchases.get(type).put(purchase, price);
        println("Purchase was added!");
    }

    public void showPurchase(int type) {
        total = 0D;

        println(String.format("\n%s:", types[type]));
        if (type < ALL) {
            total = showList(type, purchases.get(type));
        } else
            purchases.forEach((tp, purchase) -> { total += showList(tp, purchase);});
        println(String.format("Total sum: $%s", total.toString()));
    }

    private double showList(int type, Map purchase) {
        amount = 0D;
        if (purchase.isEmpty())
            println("The purchase list is empty");
        else
            purchase.forEach((purch, price) -> {
                println(String.format("%s %s", purch, price.toString()));
                amount += (double)price;
            });
        return amount;
    }

}
