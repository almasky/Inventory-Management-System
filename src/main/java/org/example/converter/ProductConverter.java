package org.example.converter;

import org.example.Dto.ProductDto;
import org.example.entity.Category;
import org.example.entity.Product;

//konvertojme te dhenat e perdoruesit nga dto ne entitet

public class ProductConverter {

   public static Product converDtoToEntity(ProductDto productDto) {
       Product product = new Product();
       product.setQuantity(productDto.getQuantity());
       product.setPrice(productDto.getPrice());
       product.setName(productDto.getName());
       product.setDescription(productDto.getDescription());
       product.setCategory(new Category(productDto.getCategoryId()));
       return product;
   };

   public static ProductDto converEntityToDto(Product product) {
       ProductDto productDto = new ProductDto();
       return productDto;
   }


}
