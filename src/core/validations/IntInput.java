package core.validations;

import java.util.Scanner;

public class IntInput {

    public int scan() {
        int result = Integer.MIN_VALUE;
        Scanner scanner = new Scanner(System.in);

        do {
            while (!scanner.hasNextInt()) {
                System.out.print("\t\tThat's not a number!\n\t\tTry Again : ");
                scanner.next();
            }
            result = scanner.nextInt();
        } while (result == Integer.MIN_VALUE);
        return result;
    }
}
