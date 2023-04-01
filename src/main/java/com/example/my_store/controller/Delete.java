package com.example.my_store.controller;

import com.example.my_store.dao.Productdao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet(name = "Delete",value = "/delete")
public class Delete extends HttpServlet {

    private Productdao productdao;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");


        try {
            productdao = new Productdao();
            int check=productdao.deleteProduct(id);
            if(check==1) {

                request.setAttribute("status", "success");
                response.sendRedirect("/Admin/view_details.jsp");

            }else {
                response.sendRedirect("/Admin");
            }
        }catch (IOException | SQLException | ClassNotFoundException e){
            response.sendRedirect("/404.jsp");
        }


    }
}
