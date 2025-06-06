package com.vinhnd.quan_ly_san_pham.controller;

import com.vinhnd.quan_ly_san_pham.dto.ProductDto;
import com.vinhnd.quan_ly_san_pham.entity.Category;
import com.vinhnd.quan_ly_san_pham.entity.Product;
import com.vinhnd.quan_ly_san_pham.service.CategoryService;
import com.vinhnd.quan_ly_san_pham.service.ICategoryService;
import com.vinhnd.quan_ly_san_pham.service.ProductService;
import com.vinhnd.quan_ly_san_pham.service.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/product")
public class ProductController extends HttpServlet {
    private IProductService productService = new ProductService();
    private ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
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
            case "search":
                search(req,resp);
                break;
            default:
                paginate(req,resp);
        }

    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");

        if(searchName != null){
            List<Product> products;
            Integer categoryId = Integer.valueOf(req.getParameter("categoryId"));

            if(categoryId != 0){
                products = productService.findByNameAndCategoryId(searchName, categoryId);
            }else{
                products = productService.findByName(searchName);
            }

            req.setAttribute("searchName", searchName);
            req.setAttribute("categoryId", categoryId);
            req.setAttribute("products", products);
            req.getRequestDispatcher("/view/list.jsp").forward(req,resp);
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
                resp.sendRedirect("/product");
        }
    }

    private void paginate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int size = 5;

        int totalItems = productService.count();
        int totalPages = (int) Math.ceil((double) totalItems / size);
        String pageParam = req.getParameter("page");

        if(pageParam != null){
            try {
                if(Integer.parseInt(pageParam) > totalPages || Integer.parseInt(pageParam) < 1){
                    page = 1;
                }else{
                    page = Integer.parseInt(pageParam);
                }
            } catch (NumberFormatException e){
                page = 1;
            }
        }

        int offset = (page - 1) * size;

        List<Product> products = productService.findPaginated(offset, size);

        req.setAttribute("products", products);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPage", totalPages);

        req.getRequestDispatcher("/view/list.jsp").forward(req, resp);
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
        Integer id = Integer.valueOf(req.getParameter("deleteId"));
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
            product.setCategoryId(Integer.valueOf(req.getParameter("categoryId")));
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
        Integer categoryId = Integer.valueOf(req.getParameter("categoryId"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));

        Product product = new Product(name, price, status, categoryId);
        productService.add(product);
        resp.sendRedirect("/product");
    }


}
