package budget;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Engine {
    private final int FOOD = 1;
    private final int CLOTHES = 2;
    private final int ENTERTAINMENT = 3;
    private final int OTHER = 4;
    private final int BACK2 = 5;
    private final int ALL = 5;
    private final int BACK3 = 6;

    private Double balance;
    private final Map<String, Double> purchasesFood;
    private final Map<String, Double> purchasesClothes;
    private final Map<String, Double> purchasesEntertainment;
    private final Map<String, Double> purchasesOther;


    public Engine(Double balance) {
        this.balance = balance;
        purchasesFood = new HashMap<>();
        purchasesClothes = new HashMap<>();
        purchasesEntertainment = new HashMap<>();
        purchasesOther = new HashMap<>();
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
        final String MENU_LVL1 =
                "Choose your action:\n" +
                        "1) Add income\n" +
                        "2) Add purchase\n" +
                        "3) Show list of purchases\n" +
                        "4) Balance\n" +
                        "0) Exit";

        final String MENU_LVL2 =
                "Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) Back\n";

        final String MENU_LVL3 =
                "Choose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) All\n" +
                        "6) Back\n";

        final int LVL_1 = 10;
        final int LVL_2 = 20;
        final int LVL_3 = 20;

        final int ADD_INCOME = 1;
        final int ADD_PURCHASE = 2;
        final int SHOW_LIST = 3;
        final int BALANCE = 4;
        final int EXIT = 0;

        int currLvl = LVL_1;
        int currAction;
        do {
            currAction = getInt((currLvl == LVL_1) ? MENU_LVL1 : MENU_LVL2);

            if (currLvl == LVL_1) {
                if (currAction == ADD_INCOME) {
                    setBalance();
                } else if (currAction == ADD_PURCHASE) {
                    addPurchase();
                } else if (currAction == SHOW_LIST) {
                    currLvl = LVL_3;
                } else if (currAction == BALANCE) {
                    getBalance();
                } else if (currAction == EXIT) {
                    println("\nBye!");
                    System.exit(0);
                }
            } else if (currLvl == LVL_2) {
                if (currAction == FOOD) {
                    addPurchase(FOOD);
                } else if (currAction == CLOTHES) {
                    addPurchase(CLOTHES);
                } else if (currAction == ENTERTAINMENT) {
                    addPurchase(ENTERTAINMENT);
                } else if (currAction == OTHER) {
                    addPurchase(OTHER);
                } else if (currAction == BACK2) {
                    currLvl = LVL_1;
                }
            } else if (currLvl == LVL_2) {
                if (currAction == FOOD) {
                    showPurchase(FOOD);
                } else if (currAction == CLOTHES) {
                    showPurchase(CLOTHES);
                } else if (currAction == ENTERTAINMENT) {
                    showPurchase(ENTERTAINMENT);
                } else if (currAction == OTHER) {
                    showPurchase(OTHER);
                } else if (currAction == ALL) {
                    showPurchase(ALL);
                } else if (currAction == BACK3) {
                    currLvl = LVL_1;
                }
            } else {
                throw new IllegalStateException("Unexpected action: " + currAction);
            }
        } while (true);
    }

    private void showList(int type) {
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

    private void addPurchase(int type) {
        println("");
        String purchase = getString("Enter purchase name:");
        Double price = getDouble("Enter its price:");
        spendMoney(price);
        if (type == FOOD)
            purchasesFood.put(purchase, price);
        else if (type == CLOTHES)
            purchasesClothes.put(purchase, price);
        else if (type == ENTERTAINMENT)
            purchasesEntertainment.put(purchase, price);
        else if (type == OTHER)
            purchasesOther.put(purchase, price);

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
