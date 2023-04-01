package com.example.my_store.dao;

import com.example.my_store.Db.Config;
import com.example.my_store.model.Product;
import com.example.my_store.queries.Query;

import java.sql.*;
import java.util.ArrayList;

import static com.example.my_store.Db.Config.getConnection;

public class Productdao {
    Config config;
    Connection connection = getConnection();
    public Productdao() throws SQLException, ClassNotFoundException {
        this.config = new Config();
    }
    static Query queries(){
        return new Query();
    }
    public static ResultSet getAllProduct() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        return  statement.executeQuery(queries().getProductAll());
    }

    public  int deleteProduct(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM product WHERE id=?");
        statement.setString(1,id);
        return statement.executeUpdate();
    }
    public static Productdao getObject() throws SQLException, ClassNotFoundException {
        return new Productdao();
    }
    public static ArrayList<Product> getAllProductName() throws SQLException, ClassNotFoundException {
        ResultSet allProduct = getObject().getAllProduct();
        ArrayList<Product> products = new ArrayList<>();
        while (allProduct.next()) {
            products.add(new Product(
                            allProduct.getInt("id"),
                            allProduct.getString("product_name"),
                            allProduct.getString("price"),
                            allProduct.getString("qty"),
                            allProduct.getString("category"),
                            allProduct.getString("img")
                    ));
        }
        return products;
    }
    public int productStore(Product product) {
        int status = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(queries().getProductAdded());
            statement.setString(1, product.getName());
            statement.setString(2, product.getPrice());
            statement.setString(3, product.getQty());
            statement.setString(4, product.getCategory());
            statement.setString(5, product.getImage());
            int insert = statement.executeUpdate();
            System.out.print(insert);

                status = insert;
            statement.close();

            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Productdao productdao = new Productdao();
        System.out.println(getAllProduct());
        System.out.println(getAllProductName());

    }



}
