package org.example.service;

import org.example.Dto.ProductDto;
import org.example.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductDto productDto);

    Product findOne(Long id);

    List<ProductDto> getAllProducts();


}
