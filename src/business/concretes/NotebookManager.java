package business.concretes;

import business.abstracts.ProductService;
import core.validations.IntInput;
import dataaccess.concretes.NotebookDataAccess;
import entities.concretes.Notebook;
import java.util.LinkedList;
import java.util.Scanner;

public class NotebookManager implements ProductService {

    private LinkedList<Notebook> notebookList = new LinkedList<>();
    NotebookDataAccess nda;
    IntInput intInput = new IntInput();

    public NotebookManager() {
        nda = new NotebookDataAccess(notebookList);
        nda.read();
    }

    @Override
    public void processMenu() {

        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("\t\t" + "+" + "-".repeat(5) + " Notebook  Operations " + "-".repeat(5) + "+");
            System.out.println("\t\t" + "|\t" + "< 1 > List Notebook" + " ".repeat(10) + "|");
            System.out.println("\t\t" + "|\t" + "< 2 > Add Notebook" + " ".repeat(11) + "|");
            System.out.println("\t\t" + "|\t" + "< 3 > Delete Notebook" + " ".repeat(8) + "|");
            System.out.println("\t\t" + "|\t" + "< 4 > Filter by 'Brand'" + " ".repeat(6) + "|");
            System.out.println("\t\t" + "|\t" + "< 0 > Save & Exit" + " ".repeat(12) + "|");
            System.out.println("\t\t" + "+" + "-".repeat(32) + "+");

            System.out.print("\t\tSelect : ");
            choice = intInput.scan();

            switch (choice) {
                case 0:
                    nda.write();
                    System.out.println("\n\t\tRedirected to the Start Menu\n");
                    break;
                case 1:
                    listProduct();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    Scanner inp = new Scanner(System.in);
                    System.out.print("\t\tBrand : ");
                    String pub = inp.next();
                    filterProduct(pub);
                    break;
                default:
                    System.out.println("\t\tInvalid Selection");
                    break;
            }
        }
    }

    @Override
    public void listProduct() {
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n", "ID", "Notebook Type", "Brand", "Sheet", "Price", "Stock");
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        for (Notebook notebook : this.notebookList) {
            System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n",
                    notebook.getId(), notebook.getName(), notebook.getBrand(), notebook.getSheet(), notebook.getPrice(), notebook.getStock());

        }
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
    }

    @Override
    public void addProduct() {
        Scanner inp = new Scanner(System.in);
        System.out.printf("\t\t%-10s : ", "Type");
        String notebookName = inp.nextLine().replaceAll(" ", "_");
        System.out.printf("\t\t%-10s : ", "Brand");
        String brand = inp.nextLine().replaceAll(" ", "_");
        System.out.printf("\t\t%-10s : ", "Sheet");
        int sheet = intInput.scan();
        System.out.printf("\t\t%-10s : ", "Price");
        int price = intInput.scan();
        System.out.printf("\t\t%-10s : ", "Stock");
        int stock = intInput.scan();

        Notebook notebook = new Notebook(this.notebookList.peekLast() == null ? 1 : this.notebookList.peekLast().getId() + 1, notebookName, brand, sheet, price, stock);

        if (notebookList.contains(notebook)) {

            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n", "ID", "Notebook Type", "Sheet", "Sheet", "Price", "Stock");
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            Notebook found = notebookList.get(notebookList.indexOf(notebook));
            System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n",
                    found.getId(), found.getName(), found.getBrand(), found.getSheet(), found.getPrice(), found.getStock());
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            System.out.println("\t\tThis notebook already exists. What action do you want to do?");
            System.out.println("\t\t< 1 > Update this notebook data\n\t\t< 2 > Increase amount of stock  by " + stock + "\n\t\t< 0 > Nothing");

            System.out.print("\t\tSelect : ");
            int sameOp = intInput.scan();

            switch (sameOp) {
                case 1:
                    found.setStock(stock);
                    found.setPrice(price);
                    System.out.println("\t\tNotebook data successfully updated");
                    break;
                case 2:
                    found.setStock(found.getStock() + stock);
                    System.out.println("\t\tStock added. New stock : " + found.getStock());
                    break;
                case 0:
                    System.out.println("\t\tNo action");
                    break;
                default:
                    System.out.println("\t\tInvalid Selection");
            }
        } else {
            this.notebookList.add(notebook);
            listProduct();
            System.out.println("\t\tNew Notebook Added");
        }

    }

    @Override
    public void removeProduct() {

        boolean isExist = true;
        System.out.print("\t\tNotebook ID : ");
        int id = intInput.scan();
        for (Notebook notebook : this.notebookList) {
            if (notebook.getId() == id) {
                isExist = true;
                this.notebookList.remove(notebook);
                System.out.println("\t\tNotebook Deleted");
                break;
            } else {
                isExist = false;
            }
        }
        if (!isExist) {
            System.out.println("\t\tNotebook not Found");
        }
    }

    @Override
    public void filterProduct(String filter) {
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n", "ID", "Notebook Type", "Sheet", "Sheet", "Price", "Stock");
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        for (Notebook notebook : this.notebookList) {
            if (notebook.getBrand().equalsIgnoreCase(filter)) {
                System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n",
                        notebook.getId(), notebook.getName(), notebook.getBrand(), notebook.getSheet(), notebook.getPrice(), notebook.getStock());
            }
        }
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
    }
}
