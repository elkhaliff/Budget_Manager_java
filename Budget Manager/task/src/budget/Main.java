package budget;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> purchases = new ArrayList<>();
        double sum = 0;
        Pattern patternPurchases = Pattern.compile("([$])(\\d+.\\d+)\\b+");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            purchases.add(input);
            Matcher matcherPurchases = patternPurchases.matcher(input);
            if (matcherPurchases.find())
                sum += Double.parseDouble(matcherPurchases.group(2));
        }
        for (String str: purchases) {
            System.out.println(str);
        }
        System.out.printf("Total: $%s", sum);
    }
}