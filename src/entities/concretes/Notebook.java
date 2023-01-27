package entities.concretes;

import entities.abstracts.Product;

import java.util.Objects;

public class Notebook extends Product {
    private String brand;
    private int sheet;

    public Notebook(int id, String name, String brand, int sheet, int price, int stock) {
        super(id, name, price, stock);
        this.brand = brand;
        this.sheet = sheet;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSheet() {
        return sheet;
    }

    public void setSheet(int sheet) {
        this.sheet = sheet;
    }

    @Override
    public String toString() {
        return super.getId() + " " + super.getName() + " " + this.getBrand() + " " + this.getSheet() + " " + super.getPrice() + " " + getStock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notebook notebook)) return false;
        return  Objects.equals(getName().toLowerCase(), notebook.getName().toLowerCase()) &&
                Objects.equals(getBrand().toLowerCase(), notebook.getBrand().toLowerCase()) &&
                Objects.equals(getSheet(), notebook.getSheet());
    }

}
