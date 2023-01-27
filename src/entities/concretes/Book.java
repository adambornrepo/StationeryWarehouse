package entities.concretes;

import entities.abstracts.Product;

public class Book extends Product {

    private String author, publisher;

    public Book(int id, String name, String author, String publisher, int price, int stock) {
        super(id, name, price, stock);
        this.author = author;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return super.getId() + " " + super.getName() + " " + this.getAuthor() + " " + this.getPublisher() + " " + super.getPrice() + " " + getStock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return  getName().equalsIgnoreCase(book.getName()) &&
                getAuthor().equalsIgnoreCase(book.getAuthor()) &&
                getPublisher().equalsIgnoreCase(book.getPublisher());
    }

}
