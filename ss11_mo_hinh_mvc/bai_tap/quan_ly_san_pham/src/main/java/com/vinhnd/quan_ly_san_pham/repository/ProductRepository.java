package com.vinhnd.quan_ly_san_pham.repository;

import com.vinhnd.quan_ly_san_pham.dto.ProductDto;
import com.vinhnd.quan_ly_san_pham.entity.Product;
import com.vinhnd.quan_ly_san_pham.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
//    private static List<Product> products = new ArrayList<>();
//    static {
//        products.add(new Product(1,"Oppo", 15000, "Đa dạng mẫu mã", true));
//        products.add(new Product(2,"Xiaomi", 12000, "Giá rẻ", true));
//        products.add(new Product(3,"Iphone", 34000, "Hiện đại", true));
//        products.add(new Product(4,"Rogphone", 32000, "Cấu hình tốt", true));
//        products.add(new Product(5,"Nokia", 2000, "Siêu bền", true));
//        products.add(new Product(6,"Samsung", 2000, "Camera sắc nét", false));
//    }


    @Override
    public List<Product> findAll() {
        String sql = "select * from products";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean add(Product product) {
        String sql = "insert into products (name, price, status, category_id) values (?,?,?,?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setBoolean(3, product.isStatus());
            statement.setInt(4, product.getCategoryId());

            int effectedRow = statement.executeUpdate();
            return effectedRow == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(Product product) {
        String sql = "update products set name = ?, price = ?, status = ?, category_id = ? where id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setBoolean(3, product.isStatus());
            statement.setInt(4, product.getCategoryId());
            statement.setInt(5, product.getId());

            int effectedRow = statement.executeUpdate();
            return effectedRow == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        String sql = "delete from products where id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, product.getId());

            int effectedRow = statement.executeUpdate();
            return effectedRow == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Product findById(Integer id) {
        String sql = "select * from products where id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setCategoryId(resultSet.getInt("category_id"));

                return product;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        String sql = "select * from products where name like ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);

            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Product> findPaginated(int offset, int size) {
        String sql = "select * from products limit ?, ?";

        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, offset);
            statement.setInt(2, size);

            ResultSet resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int count() {
        String sql = "select count(*) from products";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            if(resultSet.next()){
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<ProductDto> findAllProductWithCategory() {
        List<ProductDto> productDtos = new ArrayList<>();
        String sql = "select p.*,c.id as category_id ,c.name as category_name " +
                "from products p left join categories c on p.category_id = c.id;";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                ProductDto productDto = new ProductDto();
                productDto.setProductId(resultSet.getInt("id"));
                productDto.setProductName(resultSet.getString("name"));
                productDto.setPrice(resultSet.getFloat("price"));
                productDto.setStatus(resultSet.getBoolean("status"));
                productDto.setCategoryId(resultSet.getInt("category_id"));
                productDto.setCategoryName(resultSet.getString("category_name"));

                productDtos.add(productDto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDtos;
    }

    @Override
    public List<ProductDto> findByNameOrCategoryName(String keyword) {
        List<ProductDto> productDtoList = new ArrayList<>();
        String sql = "select p.*, c.id as category_id, c.name as category_name from products p " +
                "left join categories c on p.category_id = c.id " +
                "where p.name like ? or c.name like ? ;";
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                ProductDto productDto = new ProductDto();
                productDto.setProductId(resultSet.getInt("id"));
                productDto.setProductName(resultSet.getString("name"));
                productDto.setPrice(resultSet.getFloat("price"));
                productDto.setStatus(resultSet.getBoolean("status"));
                productDto.setCategoryId(resultSet.getInt("category_id"));
                productDto.setCategoryName(resultSet.getString("category_name"));

                productDtoList.add(productDto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productDtoList;
    }

    @Override
    public List<Product> findByNameAndCategoryId(String name, Integer categoryId) {
        String sql = "select * from products where name like ? and category_id = ?";
        List<Product> products = new ArrayList<>();
        try(Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");
            statement.setInt(2, categoryId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getFloat("price"));
                product.setStatus(resultSet.getBoolean("status"));
                product.setCategoryId(resultSet.getInt("category_id"));

                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


}
