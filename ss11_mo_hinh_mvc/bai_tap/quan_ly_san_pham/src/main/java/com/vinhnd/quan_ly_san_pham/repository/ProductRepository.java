package com.vinhnd.quan_ly_san_pham.repository;

import com.vinhnd.quan_ly_san_pham.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository{
    private static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1,"Oppo", 15000, "Đa dạng mẫu mã", true));
        products.add(new Product(2,"Xiaomi", 12000, "Giá rẻ", true));
        products.add(new Product(3,"Iphone", 34000, "Hiện đại", true));
        products.add(new Product(4,"Rogphone", 32000, "Cấu hình tốt", true));
        products.add(new Product(5,"Nokia", 2000, "Siêu bền", true));
        products.add(new Product(6,"Samsung", 2000, "Camera sắc nét", false));
    }


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void update(Product product) {
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).equals(product)){
                products.set(i, product);
                return;
            }
        }
    }

    @Override
    public void delete(Product product) {
        products.remove(product);
    }

    @Override
    public Product findById(Integer id) {
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> foundProduct = new ArrayList<>();
        for(Product product : products){
            if(product.getName().contains(name)){
                foundProduct.add(product);
            }
        }
        return foundProduct;
    }
}
