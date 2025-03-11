package org.example.service;

import org.example.Dto.ProductDto;
import org.example.config.HibernateConfig;
import org.example.converter.ProductConverter;
import org.example.dao.ProductDAO;
import org.example.dao.ProductDAOImpl;
import org.example.entity.Product;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final SessionFactory sessionFactory = HibernateConfig.geSessionFactory();

    private final ProductDAO productDAO = new ProductDAOImpl(sessionFactory);

    @Override
    public Product createProduct(ProductDto productDto) {
        Product product = ProductConverter.converDtoToEntity(productDto);
        productDAO.save(product);
        return product;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productDAO.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = ProductConverter.converEntityToDto(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public Product findOne(Long id) {
        return productDAO.findById(id);

    }



}
