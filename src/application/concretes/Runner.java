package application.concretes;

import business.abstracts.ProductService;
import business.concretes.BookManager;
import business.concretes.NotebookManager;
import business.concretes.OtherProductsManager;
import core.validations.IntInput;

public class Runner {
    public static void main(String[] args) {

        start();
    }

    public static void start() {
        IntInput intInput = new IntInput();
        System.out.println("\t\t" + "+" + "-".repeat(10) + " BOOK STORE " + "-".repeat(10) + "+");
        int select = -1;
        while (select != 0) {
            System.out.println("\t\t" + "+" + "-".repeat(6) + " Product Management " + "-".repeat(6) + "+");
            System.out.println("\t\t" + "|\t< 1 > Books" + " ".repeat(18) + "|");
            System.out.println("\t\t" + "|\t< 2 > Notebooks" + " ".repeat(14) + "|");
            System.out.println("\t\t" + "|\t< 3 > Other Products" + " ".repeat(9) + "|");
            System.out.println("\t\t" + "|\t< 0 > Exit" + " ".repeat(19) + "|");
            System.out.println("\t\t" + "+" + "-".repeat(32) + "+");
            System.out.print("\t\tSelect : ");
            String arrow = " ".repeat(17) + "|\n" + " ".repeat(17) + "|\n" + " ".repeat(17) + "↓";
            select = intInput.scan();
            ProductService service;
            switch (select) {
                case 1:
                    System.out.print(arrow);
                    service = new BookManager();
                    service.processMenu();
                    break;
                case 2:
                    System.out.print(arrow);
                    service = new NotebookManager();
                    service.processMenu();
                    break;
                case 3:
                    System.out.print(arrow);
                    service = new OtherProductsManager();
                    service.processMenu();
                    break;
                case 0:
                    System.out.println("\t\tSee You");
                    break;
                default:
                    System.out.println("\t\tInvalid Selection. Try Again");
                    break;
            }
        }
    }

}

