package budget;

import java.io.Serializable;
import java.util.*;

public class Purchases implements Serializable {
    private Double balance;
    Double total;
    boolean isEmpty;
    double amount;

    private final int ALL = 5;

    private final String[] types = {"", "Food", "Clothes", "Entertainment", "Other", "All"};

    private final Map<Integer, Map<String, Double>> purchases;

    public Purchases() {
        balance = 0D;
        purchases = new LinkedHashMap<>();

        for (int i = 1; i < ALL; i++) {
            purchases.put(i, new LinkedHashMap<>());
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
        println(String.format("\nBalance: $%.2f", balance));
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
        if (type == ALL) {
            purchases.forEach((tp, purchase) -> total += showMap(purchase));
        } else
            total = showMap(purchases.get(type));
        println(String.format("Total sum: $%.2f", total));
    }

    private double showMap(Map<String, Double> map) {
        amount = 0D;
        if (map.isEmpty())
            println("The purchase list is empty");
        else
            map.forEach((purchase, price) -> {
                println(String.format("%s $%.2f", purchase, price));
                amount += price;
            });
        return amount;
    }

    public void sortPurchaseAll(boolean isSortByType) {
        total = 0D;
        int type = ALL;
        Map<String, Double> sortedMap;

        if (!isSortByType && isEmpty(type))
            println("\nThe purchase list is empty!");
        else{
            if (isSortByType) {
                println("\nTypes:");
                sortedMap = getSummaryMapByType(type);
            } else {
                println(String.format("\n%s:", types[type]));
                sortedMap = getSortedMapByType(type);
            }
            total = showMap(sortedMap);
            println(String.format("Total sum: $%.2f", total));
        }
    }

    private boolean isEmpty(int type) {
        isEmpty = false;

        if (type == ALL) {
            purchases.forEach((tp, purchase) -> isEmpty = isEmpty || purchase.isEmpty());
        } else
            isEmpty = purchases.get(type).isEmpty();

        return isEmpty;
    }

    public void sortPurchaseByCertainType(int type) {
        total = 0D;
        Map<String, Double> sortedMap;

        if (isEmpty(type))
            println("\nThe purchase list is empty!");
        else {
            println("\n%s:, types[type]");
            sortedMap = getSortedMapByType(type);

            total = showMap(sortedMap);
            println(String.format("Total sum: $%.2f", total));
        }
    }

    private Map<String, Double> getSortedMapByType(int type) {
        Map<String, Double> sortedMap = new TreeMap<>();
        if (type == ALL) {
            purchases.forEach((tp, purchase) -> sortedMap.putAll(purchase));
        } else
            sortedMap.putAll(purchases.get(type));
        return sortByValues(sortedMap);
    }

    private Map<String, Double> getSummaryMapByType(int type) {
        Map<String, Double> sortedMap = new TreeMap<>();
        if (type == ALL) {
            purchases.forEach((tp, purchase) -> sortedMap.putAll(getSummaByType(tp, purchase)));
        } else
            sortedMap.putAll(getSummaByType(type, purchases.get(type)));
        return sortByValues(sortedMap);
    }

    private Map<String, Double> getSummaByType(int type, Map<String, Double> map) {
        Map<String, Double> retMap = new HashMap<>();
        amount = 0D;
        if (!map.isEmpty())
            map.forEach((purchase, price) -> amount += price);
        retMap.put(types[type], amount);
        return retMap;
    }

    private  <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator = (k1, k2) -> {
            int compare =
                    map.get(k1).compareTo(map.get(k2));
            if (compare == 0)
                return 1;
            else
                return -compare;
        };
        Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }
}