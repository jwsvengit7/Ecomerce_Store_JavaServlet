package com.example.my_store.dao;

import com.example.my_store.Db.Config;
import com.example.my_store.model.AppUser;
import com.example.my_store.model.Cart;
import com.example.my_store.model.Product;
import com.example.my_store.queries.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.my_store.Db.Config.getConnection;


public class Userdao {
    Config config;
    Connection connection = getConnection();

    public Userdao() throws SQLException, ClassNotFoundException {
        this.config = new Config();
    }

    public static Userdao getObject() throws SQLException, ClassNotFoundException {
        return new Userdao();
    }
    public ResultSet getAllUser() throws SQLException {
        String query = "SELECT * FROM signup";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
    public ResultSet getCarts() throws SQLException {
        String query = "SELECT * FROM cart";
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static ResultSet getCartByID(String id) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM cart WHERE user_id='"+id+"'");
    }
    public static List<Cart> getAllCartid(String id) throws SQLException, ClassNotFoundException {

        ResultSet cartS = getCartByID(id);
        List<Cart> list = new ArrayList<>();
        while (cartS.next()) {
            list.add(new Cart(
                    cartS.getInt("id"),
                    cartS.getString("user_id"),
                    cartS.getString("product_id")

            ));
        }
        return list;
    }



    public static ResultSet getProductByID(String id) throws SQLException, ClassNotFoundException {

        Statement statement = getConnection().createStatement();
        return statement.executeQuery("SELECT * FROM product WHERE id='"+id+"'");
    }
    public static List<Product> getAllProductid(String id) throws SQLException, ClassNotFoundException {

        ResultSet cartS = getProductByID(id);
        List<Product> list = new ArrayList<>();
        while (cartS.next()) {
            list.add(new Product(
                    cartS.getInt("id"),
                    cartS.getString("product_name"),
                    cartS.getString("price"),
                    cartS.getString("qty"),
                    cartS.getString("category"),
                    cartS.getString("img")
            ));
        }
        return list;
    }

    public static List<Cart> getAllCart() throws SQLException, ClassNotFoundException {

        ResultSet cartS = getObject().getCarts();
        List<Cart> list = new ArrayList<>();
        while (cartS.next()) {
            list.add(new Cart(
                    cartS.getInt("id"),
                    cartS.getString("user_id"),
                    cartS.getString("product_id")

            ));
        }
        return list;
    }
public static List<String> getAllUserEmail() throws SQLException, ClassNotFoundException {

    ResultSet allUser = getObject().getAllUser();
    List<String> emailList = new ArrayList<>();
    while (allUser.next()) {
        emailList.add(allUser.getString("email"));
    }
    return emailList;
}


    public static List<AppUser> getAllUsers() throws SQLException, ClassNotFoundException {

        ResultSet allUser = getObject().getAllUser();
        List<AppUser> list = new ArrayList<>();
        while (allUser.next()) {
            list.add(new AppUser(
                    allUser.getInt("id"),
                    allUser.getString("username"),
                    allUser.getString("email"),
                    allUser.getString("password")

            ));
        }
        return list;
    }


    public boolean insertUser(AppUser appUser) {
        boolean status = false;
        Query query=new Query();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query.getQueryInserted());
            preparedStatement.setString(1, appUser.getUsername());
            preparedStatement.setString(2, appUser.getEmail());
            preparedStatement.setString(3, appUser.getPassword());
            if (!getAllUserEmail().contains(appUser.getEmail())) {
                preparedStatement.executeUpdate();
                status = true;
                preparedStatement.close();
            }
            return status;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertCart(Cart cart) {
        boolean status = false;
        Query query=new Query();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query.getCartInsert());
            preparedStatement.setString(1, cart.getProduct_id());
            preparedStatement.setString(2, cart.getUser_id());
            if (preparedStatement.executeUpdate()==1) {

                status = true;
                preparedStatement.close();
            }
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean loginUser(String name, String password) throws SQLException, ClassNotFoundException {
        boolean status= false;
        Query query=new Query();

            PreparedStatement statement = connection.prepareStatement(query.getQuery());
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                status = true;
                statement.close();
            }

            return status;

    }


    public  String getUserId(String username, String password) throws SQLException {
        String user_Id = null;

        String query = "SELECT * FROM signup WHERE email = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            user_Id = resultSet.getString("id");
            String name = resultSet.getString("username");
            String email = resultSet.getString("email");
            String cpassword = resultSet.getString("password");
            System.out.println(
                    "---------------ID "+user_Id+"\n" +
                            "---------------Name "+name+"\n" +
                            "---------------Username "+name+"\n" +
                            "---------------Password "+cpassword);
            statement.close();
        }
        return user_Id;
    }

    public static AppUser getUser(String email) throws SQLException, ClassNotFoundException {
        AppUser user = new AppUser();

        String query = "SELECT * FROM signup WHERE email = ?";
        PreparedStatement statement = getConnection().prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            int user_Id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            String emailuser = resultSet.getString("email");
            String cpassword = resultSet.getString("password");

             user.setId(user_Id);
             user.setUsername(name);
             user.setEmail(emailuser);
             user.setPassword(cpassword);



            statement.close();
        }
        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Userdao userdao = new Userdao();
        Query query=new Query();
        System.out.println(query.getQuery());

        System.out.println(userdao.loginUser("chiorlujack@gmail.com","12345"));
        System.out.println(userdao.getUserId("chiorlujack@gmail.com","12345"));

        System.out.println(getAllCartid("1"));

        System.out.println(getAllProductid("2"));
    }


}
