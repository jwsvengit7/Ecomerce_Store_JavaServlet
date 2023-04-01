package com.example.my_store.queries;

import lombok.*;

@Getter
@Setter
public class Query {
    private  final String queryInserted = "INSERT INTO signup (username,email,password)VALUES(?,?,?)";
    private  final String productAdded = "INSERT INTO product (product_name,price,qty,category,img)VALUES(?,?,?,?,?)";
    private  final String query= "SELECT * FROM  signup WHERE email=? AND password=?";
    private  final String UPDATE= "UPDATE FROM  signup WHERE email=? AND password=?";
    private  final String DELETE= "DELETE FROM  signup WHERE email=? ";
    private  final String productAll = "SELECT * FROM product ORDER BY ID";
    private  final String cart = "SELECT * FROM cart WHERE user_id=?";
    private  final String cartInsert = "INSERT INTO cart (product_id,user_id)VALUES(?,?)";


}
