package org.example.dao;

import org.example.entity.Product;

import java.util.List;

public interface ProductDAO {

     void save(Product product);


     void delete(Long id);


     List<Product> findAll();


     Product findById(Long id);


}
