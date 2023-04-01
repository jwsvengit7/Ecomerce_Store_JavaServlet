package com.example.my_store.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private String name;
    private int id;
    private String price;
    private String qty;
    private String category;
    private String image;

    public Product(String name, String price, String qty, String category,String image) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.category = category;
        this.image = image;

    }
    public Product(int id,String name, String price, String qty, String category,String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.category = category;
        this.image = image;

    }

    public Product(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
