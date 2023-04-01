package com.example.my_store.controller;

import com.example.my_store.dao.Userdao;
import com.example.my_store.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name ="AddTocart",value = "/AddTocart")
public class AddTocart extends HttpServlet {
    Userdao userdao;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");
        String user_id = request.getParameter("user_id");
        Cart cart = new Cart(user_id,id);
        try {
            userdao = new Userdao();
            boolean addCart = userdao.insertCart(cart);
            if (addCart){
                request.setAttribute("status","success");
                String isLogin="isLogin";
                response.sendRedirect("/User/index.jsp?session="+isLogin);

            }else{
                request.setAttribute("status","error");


            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
}
