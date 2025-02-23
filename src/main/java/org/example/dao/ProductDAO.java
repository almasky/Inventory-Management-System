package org.example.dao;

import org.example.entity.Product;

import java.util.List;

public interface ProductDAO {

     void save(Product product);
     void update(Product product);
     void delete(Product product);

     Product getProduct(int id);
     List<Product> getProducts();


}
