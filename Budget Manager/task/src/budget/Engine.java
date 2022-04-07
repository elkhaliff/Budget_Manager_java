package budget;

import java.io.IOException;
import java.util.Scanner;

public class Engine {

    final String SERIAL_FILE_NAME = "purchases.txt";

    private Purchases allPurchases;

    public Engine() {
        allPurchases = new Purchases();
    }

    public void println(String string) { System.out.println(string); }

    public int getInt(String string) {
        Scanner scanner = new Scanner(System.in);
        println(string);
        return scanner.nextInt();
    }

    public void run() {
        final String MENU_LVL1 =
                "\nChoose your action:\n" +
                        "1) Add income\n" +
                        "2) Add purchase\n" +
                        "3) Show list of purchases\n" +
                        "4) Balance\n" +
                        "5) Save\n" +
                        "6) Load\n" +
                        "7) Analyze (Sort)\n" +
                        "0) Exit";

        final String MENU_LVL2 =
                "\nChoose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) Back";

        final String MENU_LVL3 =
                "\nChoose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other\n" +
                        "5) All\n" +
                        "6) Back";

        final String MENU_LVL4 =
                "\nHow do you want to sort?\n" +
                        "1) Sort all purchases\n" +
                        "2) Sort by type\n" +
                        "3) Sort certain type\n" +
                        "4) Back";


        final String MENU_LVL5 =
                "\nChoose the type of purchase\n" +
                        "1) Food\n" +
                        "2) Clothes\n" +
                        "3) Entertainment\n" +
                        "4) Other";

        final int LVL_1 = 1;
        final int LVL_2 = 2;
        final int LVL_3 = 3;
        final int LVL_4 = 4;
        final int LVL_5 = 5;

        final int ADD_INCOME = 1;
        final int ADD_PURCHASE = 2;
        final int SHOW_LIST = 3;
        final int BALANCE = 4;
        final int SAVE = 5;
        final int LOAD = 6;
        final int SORT = 7;

        final int SORT_ALL = 1;
        final int SORT_BY_TYPE = 2;

        final int EXIT = 0;
        final int BACK2 = 5;
        final int BACK3 = 6;
        final int BACK4 = 4;

        int currLvl = LVL_1;
        int currAction;
        while (true) {
            String menu;
            if (currLvl == LVL_1) menu = MENU_LVL1;
            else if (currLvl == LVL_2) menu = MENU_LVL2;
            else if (currLvl == LVL_3) menu = MENU_LVL3;
            else if (currLvl == LVL_4) menu = MENU_LVL4;
            else menu = MENU_LVL5;

            currAction = getInt(menu);

            if (currLvl == LVL_1) {
                if (currAction == ADD_INCOME)  allPurchases.setBalance();
                else if (currAction == ADD_PURCHASE)  currLvl = LVL_2;
                else if (currAction == SHOW_LIST)  currLvl = LVL_3;
                else if (currAction == BALANCE)  allPurchases.getBalance();
                else if (currAction == SAVE)  {
                    try {
                        SerializationUtils.serialize(allPurchases, SERIAL_FILE_NAME);
                        println("\nPurchases were saved!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                else if (currAction == LOAD)  {
                    try {
                        allPurchases = (Purchases) SerializationUtils.deserialize(SERIAL_FILE_NAME);
                        println("\nPurchases were loaded!");
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                else if (currAction == SORT)  currLvl = LVL_4;
                else if (currAction == EXIT) {
                    println("\nBye!");
                    System.exit(0);
                }
            } else if (currLvl == LVL_2) {
                if (currAction < BACK2)  allPurchases.addPurchase(currAction);
                else if (currAction == BACK2) currLvl = LVL_1;
            } else if (currLvl == LVL_3) {
                if (currAction < BACK3)  allPurchases.showPurchase(currAction);
                else if (currAction == BACK3) currLvl = LVL_1;
            } else if (currLvl == LVL_4) {
                if (currAction < BACK4) {
                    if (currAction == SORT_ALL || currAction == SORT_BY_TYPE)
                        allPurchases.sortPurchaseAll(currAction == SORT_BY_TYPE);
                    else {
                        currLvl = LVL_5;
                    }
                }
                else if (currAction == BACK4) currLvl = LVL_1;
            } else if (currLvl == LVL_5) { // choose type
                allPurchases.sortPurchaseByCertainType(currAction);
                currLvl = LVL_4;
            } else {
                throw new IllegalStateException("Unexpected action: " + currAction);
            }
        }
    }
}