package com.example.bai_2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/calculator")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        float first = Float.parseFloat(req.getParameter("first"));
        float second = Float.parseFloat(req.getParameter("second"));
        float result = 0;
        String message = "";

        String operator = req.getParameter("operator");

        switch (operator){
            case "add" :
                result = first + second;
                break;
            case "sub":
                result = first - second;
                break;
            case "mul":
                result = first * second;
                break;
            case "div":
                if(second == 0){
                    message = "Không thể chia cho 0";
                }else{
                    result = first / second;
                }
                break;
        }
        req.setAttribute("message", message);
        req.setAttribute("result", result);

        req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
    }
}
