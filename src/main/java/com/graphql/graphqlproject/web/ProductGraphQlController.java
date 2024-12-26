package com.graphql.graphqlproject.web;

import com.graphql.graphqlproject.dto.ProductRequestDto;
import com.graphql.graphqlproject.entities.Category;
import com.graphql.graphqlproject.entities.Product;
import com.graphql.graphqlproject.entities.repositories.CategoryRepository;
import com.graphql.graphqlproject.entities.repositories.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ProductGraphQlController {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductGraphQlController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Product> productList(){
        return productRepository.findAll();
    }
    @QueryMapping
    public Product productById(@Argument String id ){
        return productRepository.findById(id).orElseThrow(()-> new NullPointerException(String.format("Product with id: %s not found",id)));
    }

    @MutationMapping
    public void deleteProductById(@Argument String id ){
        Product product= productRepository.findById(id).orElseThrow(()-> new NullPointerException(String.format("Product with id: %s not found",id)));
        productRepository.delete(product);
    }
    @MutationMapping
    public Product saveProduct(@Argument ProductRequestDto productRequestDto){
        System.out.println(productRequestDto.categoryId());
        Category category=categoryRepository.findById(productRequestDto.categoryId()).orElse(null);
        Product product=new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName(productRequestDto.name());
        product.setPrice(productRequestDto.price());
        product.setQuantity(productRequestDto.quantity());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @MutationMapping
    public Product updateProduct(@Argument String id,@Argument ProductRequestDto productRequestDto){
        System.out.println(productRequestDto.categoryId());
        Category category=categoryRepository.findById(productRequestDto.categoryId()).orElse(null);
        Product product=new Product();
        product.setId(id);
        product.setName(productRequestDto.name());
        product.setPrice(productRequestDto.price());
        product.setQuantity(productRequestDto.quantity());
        product.setCategory(category);
        return productRepository.save(product);
    }
}
