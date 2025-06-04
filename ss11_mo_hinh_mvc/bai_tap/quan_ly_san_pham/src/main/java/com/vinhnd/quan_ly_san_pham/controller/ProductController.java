package com.vinhnd.quan_ly_san_pham.controller;

import com.vinhnd.quan_ly_san_pham.entity.Product;
import com.vinhnd.quan_ly_san_pham.service.ProductService;
import com.vinhnd.quan_ly_san_pham.service.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/product")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                req.getRequestDispatcher("/view/form.jsp").forward(req, resp);
                break;
            case "edit":
                Integer id = Integer.valueOf(req.getParameter("id"));
                Product product = productService.findById(id);
                if (product == null) {
                    resp.sendRedirect("/product?action=add");
                } else {
                    req.setAttribute("product", product);
                    req.getRequestDispatcher("/view/form.jsp").forward(req, resp);
                }

            default:
                List<Product> products = productService.findAll();
                req.setAttribute("products", products);
                req.getRequestDispatcher("/view/list.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                add(req, resp);
                break;
            case "edit":
                edit(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
            case "detail":
                detail(req, resp);
                break;
            default:
                String searchName = req.getParameter("searchName");
                System.out.println(searchName);
                if(searchName != null){
                       List<Product> products = productService.findByName(searchName);
                       req.setAttribute("products", products);
                       req.getRequestDispatcher("/view/list.jsp").forward(req,resp);
                }else{
                    resp.sendRedirect("/product");
                }

        }
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void detail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Product product = productService.findById(id);
        if (product == null) {
            resp.sendRedirect("/product");
        } else {
            req.setAttribute("product", product);
            req.getRequestDispatcher("/view/detail.jsp").forward(req, resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Product product = productService.findById(id);
        productService.delete(product);
        resp.sendRedirect("/product");
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        Product product = productService.findById(id);
        if (product != null) {
            product.setName(req.getParameter("name"));
            product.setPrice(Float.parseFloat(req.getParameter("price")));
            product.setDescription(req.getParameter("description"));
            product.setStatus(Boolean.parseBoolean(req.getParameter("status")));

            productService.update(product);
            resp.sendRedirect("/product");
        } else {
            resp.sendRedirect("/product?action=add");
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        float price = Float.parseFloat(req.getParameter("price"));
        String description = req.getParameter("description");
        boolean status = Boolean.parseBoolean(req.getParameter("status"));

        Product product = new Product(name, price, description, status);
        productService.add(product);
        resp.sendRedirect("/product");
    }


}
