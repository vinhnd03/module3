package com.example.discount_caculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/display-discount")
public class DiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/index2.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double price = Double.valueOf(req.getParameter("price"));
        Double percent = Double.valueOf(req.getParameter("percent"));

        Double discountAmount = price * percent * 0.01;
        Double newPrice = price - discountAmount;

        req.setAttribute("discountAmount", discountAmount);
        req.setAttribute("newPrice", newPrice);

        req.getRequestDispatcher("/view/index2.jsp").forward(req,resp);
    }
}
