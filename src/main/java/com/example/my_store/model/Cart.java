package com.example.my_store.model;
import lombok.*;
@Getter
@Setter
@ToString


public class Cart  {
    private int id;
    private String user_id;
    private String product_id;

    public Cart(int id, String user_id, String product_id) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
    }

    public Cart(String user_id, String product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }
    public Cart(String user_id) {
        this.user_id = user_id;

    }
}
