package com.example.my_store.controller;

import com.example.my_store.dao.Productdao;
import com.example.my_store.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name ="Product",value = "/product")

public class ProductServlet extends HttpServlet {
    private Productdao productdao;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("product");
        String category = request.getParameter("category");
        String price = request.getParameter("amount");
        String qty = request.getParameter("qty");


        String Id;
        String image;
        try {
            productdao = new Productdao();
            Product product = new Product(name, price, qty, category, "");
            if (category.isEmpty()) {

            } else {

                if (category.equals("Foods") && name.equals("Soup")) {
                    product.setImage("soup.jpeg");
                } else if (category.equals("Accessories") && name.equals("Printer")) {
                    product.setImage("Printer.jpeg");
                } else if (category.equals("Kitchen Kit") && name.equals("ELECTRIC BREAD TOASTER")) {
                    product.setImage("Bread.jpeg");
                } else if (category.equals("Kitchen Kit") && name.equals("3 SETS OF NONSTICK POTS")) {
                    product.setImage("sh.jpeg");
                } else if (category.equals("Kitchen Kit") && name.equals("Stove")) {
                    product.setImage("st.jpeg");
                } else if (category.equals("Kitchen Kit") && name.equals("Pot")) {
                    product.setImage("things.jpeg");
                } else if (category.equals("Foods") && name.equals("Burger")) {
                    product.setImage("Germain Stew.jpeg");
                } else if (category.equals("Accessories") && name.equals("HP 19.5 VOLTS ADAPTER")) {
                    product.setImage("charget.PNG");
                }


                int productCreated = productdao.productStore(product);
                if (productCreated==1) {
                    request.setAttribute("status", "success");
                    String isUpload = "isUpload";
                    out.println("<script>alert('successful added')</script>");
                    response.sendRedirect("/Admin");
                } else {
                    out.println("errorMessage: Error creating account.");
                    request.getRequestDispatcher("/404.jsp").forward(request, response);
                }

            }
            } catch(SQLException | ClassNotFoundException ex){
                throw new RuntimeException(ex);
            }





    }


}
