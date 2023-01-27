package business.abstracts;

public interface ProductService {
    void processMenu();

    void listProduct();

    void addProduct();

    void removeProduct();

    void filterProduct(String filter);
}
