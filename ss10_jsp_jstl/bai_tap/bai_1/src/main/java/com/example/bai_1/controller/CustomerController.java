package com.example.bai_1.controller;

import com.example.bai_1.entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/customer")
public class CustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Vinh", "12-05-2003", "Quảng Bình"));
        customers.add(new Customer("Vinh2", "12-05-2003", "Quảng Bình"));
        customers.add(new Customer("Vinh3", "12-05-2003", "Quảng Bình"));
        customers.add(new Customer("Vinh3", "12-05-2003", "Quảng Bình"));

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/index.jsp").forward(req,resp);
    }
}
