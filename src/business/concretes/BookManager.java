package business.concretes;

import business.abstracts.ProductService;
import core.validations.IntInput;
import dataaccess.concretes.BookDataAccess;
import entities.concretes.Book;
import java.util.LinkedList;
import java.util.Scanner;

public class BookManager implements ProductService {
    private LinkedList<Book> bookList = new LinkedList<>();
    BookDataAccess bda;
    IntInput intInput = new IntInput();

    public BookManager() {
        bda = new BookDataAccess(bookList);
        bda.read();
    }

    @Override
    public void processMenu() {
        int choice = -1;
        while (choice != 0) {
            System.out.println();
            System.out.println("\t\t" + "+" + "-".repeat(7) + " Book  Operations " + "-".repeat(7) + "+");
            System.out.println("\t\t" + "|\t" + "< 1 > List Books" + " ".repeat(13) + "|");
            System.out.println("\t\t" + "|\t" + "< 2 > Add Book" + " ".repeat(15) + "|");
            System.out.println("\t\t" + "|\t" + "< 3 > Delete Book" + " ".repeat(12) + "|");
            System.out.println("\t\t" + "|\t" + "< 4 > Filter by 'Publisher'  " + "|");
            System.out.println("\t\t" + "|\t" + "< 0 > Save & Exit" + " ".repeat(12) + "|");
            System.out.println("\t\t" + "+" + "-".repeat(32) + "+");

            System.out.print("\t\tSelect : ");
            choice = intInput.scan();

            switch (choice) {
                case 0:
                    bda.write();
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
                    System.out.print("\t\tPublisher : ");
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
        System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n", "ID", "Book Name", "Author", "Publisher", "Price", "Stock");
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        for (Book book : this.bookList) {
            System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n",
                    book.getId(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPrice(), book.getStock());

        }
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
    }

    @Override
    public void addProduct() {
        Scanner inp = new Scanner(System.in);
        System.out.printf("\t\t%-10s : ", "Book Name");
        String bookName = inp.nextLine().replaceAll(" ", "_");
        System.out.printf("\t\t%-10s : ", "Author");
        String authorName = inp.nextLine().replaceAll(" ", "_");
        System.out.printf("\t\t%-10s : ", "Publisher");
        String pub = inp.nextLine().replaceAll(" ", "_");
        System.out.printf("\t\t%-10s : ", "Price");
        int unitPrice = intInput.scan();
        System.out.printf("\t\t%-10s : ", "Stock");
        int stock = intInput.scan();
        Book newbook = new Book(this.bookList.peekLast() == null ? 1 : this.bookList.peekLast().getId() + 1, bookName, authorName, pub, unitPrice, stock);
        if (bookList.contains(newbook)) {
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n", "ID", "Book Name", "Author", "Publisher", "Price", "Stock");
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            Book found = bookList.get(bookList.indexOf(newbook));
            System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n",
                    found.getId(), found.getName(), found.getAuthor(), found.getPublisher(), found.getPrice(), found.getStock());
            System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
            System.out.println("\t\tThis book already exists. What action do you want to do?");
            System.out.println("\t\t< 1 > Update this book data\n\t\t< 2 > Increase amount of stock  by "+stock+"\n\t\t< 0 > Nothing");
            System.out.print("\t\tSelect : ");
            int sameOp = intInput.scan();
            switch (sameOp){
                case 1: found.setStock(stock);found.setPrice(unitPrice); System.out.println("\t\tBook data successfully updated");break;
                case 2: found.setStock(found.getStock() + stock); System.out.println("\t\tStock added. New stock : "+found.getStock());break;
                case 0: System.out.println("\t\tNo action");break;
                default:System.out.println("\t\tInvalid Selection");
            }
        }else {
            this.bookList.add(newbook);
            listProduct();
            System.out.println("\t\tNew Book Added");
        }

    }

    @Override
    public void removeProduct() {
        boolean isExist = true;
        System.out.print("\t\tBook ID : ");
        int id = intInput.scan();
        for (Book book : this.bookList) {
            if (book.getId() == id) {
                isExist = true;
                this.bookList.remove(book);
                System.out.println("\t\tBook Deleted");
                break;
            } else {
                isExist = false;
            }
        }
        if (!isExist) {
            System.out.println("\t\tBook not Found");
        }
    }

    @Override
    public void filterProduct(String filter) {
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n", "ID", "Book Name", "Author", "Publisher", "Price", "Stock");
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
        for (Book book : this.bookList) {
            if (book.getPublisher().equalsIgnoreCase(filter)) {
                System.out.printf("\t\t|\t%-3s | %-20s | %-20s | %-15s | %-11s | %-7s |\n",
                        book.getId(), book.getName(), book.getAuthor(), book.getPublisher(), book.getPrice(), book.getStock());
            }
        }
        System.out.println("\t\t" + "+" + "-".repeat(95) + "+");
    }

}
