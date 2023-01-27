package entities.concretes;


import entities.abstracts.Product;

import java.util.Objects;

public class OtherProduct extends Product {
    private String description;

    public OtherProduct(int id, String name, int price, int stock, String description) {
        super(id, name, price, stock);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.getId() + " " + super.getName() + " " + super.getPrice() + " " + getStock() + " " + this.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OtherProduct product)) return false;
        return  Objects.equals(getName().toLowerCase(), product.getName().toLowerCase()) &&
                Objects.equals(getDescription().toLowerCase(), product.getDescription().toLowerCase());
    }

}
