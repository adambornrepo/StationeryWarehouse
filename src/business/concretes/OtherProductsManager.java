package business.concretes;

import business.abstracts.ProductService;
import core.validations.IntInput;
import dataaccess.concretes.OtherProductsDataAccess;
import entities.concretes.OtherProduct;
import java.util.LinkedList;
import java.util.Scanner;

public class OtherProductsManager implements ProductService {

    private LinkedList<OtherProduct> otherProductsList = new LinkedList<>();
    OtherProductsDataAccess opda;
    IntInput intInput = new IntInput();

    public OtherProductsManager() {
        opda = new OtherProductsDataAccess(otherProductsList);
        opda.read();
    }

    @Override
    public void processMenu() {

        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("\t\t" + "+" + "-".repeat(5) + " Products  Operations " + "-".repeat(5) + "+");
            System.out.println("\t\t" + "|\t" + "< 1 > List Products" + " ".repeat(10) + "|");
            System.out.println("\t\t" + "|\t" + "< 2 > Add Product" + " ".repeat(12) + "|");
            System.out.println("\t\t" + "|\t" + "< 3 > Delete Product" + " ".repeat(9) + "|");
            System.out.println("\t\t" + "|\t" + "< 4 > Filter by 'Type'  " + " ".repeat(5) + "|");
            System.out.println("\t\t" + "|\t" + "< 0 > Save & Exit" + " ".repeat(12) + "|");
            System.out.println("\t\t" + "+" + "-".repeat(32) + "+");

            System.out.print("\t\tSelect : ");
            choice = intInput.scan();

            switch (choice) {
                case 0:
                    opda.write();
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
                    System.out.print("\t\tProduct Type : ");
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
        System.out.printf("\t\t|\t%-3s | %-20s | %-9s | %-7s | %-40s |\n", "ID", "Product Type", "Price", "Stock", "Description");
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        for (OtherProduct product : this.otherProductsList) {
            System.out.printf("\t\t|\t%-3s | %-20s | %-9s | %-7s | %-40s |\n",
                    product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getDescription());

        }
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
    }

    @Override
    public void addProduct() {
        Scanner inp = new Scanner(System.in);
        System.out.printf("\t\t%-10s : ", "Type");
        String productName = inp.nextLine().replaceAll(" ", "_");
        System.out.printf("\t\t%-10s : ", "Price");
        int price = intInput.scan();
        System.out.printf("\t\t%-10s : ", "Stock");
        int stock = intInput.scan();
        System.out.printf("\t\t%-10s : ", "Description (max 40 char)");
        String description = inp.nextLine().replaceAll(" ", "_");//this.otherProductsList.get(otherProductsList.size() - 1).getId() + 1
        OtherProduct otherProduct = new OtherProduct(this.otherProductsList.peekLast() == null ? 1 : this.otherProductsList.peekLast().getId() + 1, productName, price, stock, description);

        if (otherProductsList.contains(otherProduct)) {
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            System.out.printf("\t\t|\t%-3s | %-20s | %-9s | %-7s | %-40s |\n", "ID", "Product Type", "Price", "Stock", "Description");
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            OtherProduct found = otherProductsList.get(otherProductsList.indexOf(otherProduct));
            System.out.printf("\t\t|\t%-3s | %-20s | %-9s | %-7s | %-40s |\n",
                    found.getId(), found.getName(), found.getPrice(), found.getStock(), found.getDescription());
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            System.out.println("\t\tThis product already exists. What action do you want to do?");
            System.out.println("\t\t< 1 > Update this product data\n\t\t< 2 > Increase amount of stock  by " + stock + "\n\t\t< 0 > Nothing");
            System.out.print("\t\tSelect : ");
            int sameOp = intInput.scan();
            switch (sameOp) {
                case 1:
                    found.setStock(stock);
                    found.setPrice(price);
                    System.out.println("\t\tProduct data successfully updated");
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
            this.otherProductsList.add(otherProduct);
            listProduct();
            System.out.println("\t\tNew Product Added");
        }
    }

    @Override
    public void removeProduct() {
        boolean isExist = true;
        System.out.print("\t\tProduct ID : ");
        int id = intInput.scan();
        for (OtherProduct product : this.otherProductsList) {
            if (product.getId() == id) {
                isExist = true;
                this.otherProductsList.remove(product);
                System.out.println("\t\tProduct Deleted");
                break;
            } else {
                isExist = false;
            }
        }
        if (!isExist) {
            System.out.println("\t\tProduct not Found");
        }
    }

    @Override
    public void filterProduct(String filter) {
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        System.out.printf("\t\t|\t%-3s | %-20s | %-9s | %-7s | %-40s |\n", "ID", "Product Type", "Price", "Stock", "Description");
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        for (OtherProduct product : this.otherProductsList) {
            if (product.getName().equalsIgnoreCase(filter)) {
                System.out.printf("\t\t|\t%-3s | %-20s | %-9s | %-7s | %-40s |\n",
                        product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getDescription());
            }
        }
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
    }
}
