package com.graphql.graphqlproject;

import com.graphql.graphqlproject.entities.Category;
import com.graphql.graphqlproject.entities.Product;
import com.graphql.graphqlproject.entities.repositories.CategoryRepository;
import com.graphql.graphqlproject.entities.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GraphqlprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlprojectApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository){
        return args -> {
            List.of("Computer","Printer","Smartphone").forEach(cat->{
                Category category=Category.builder().name(cat).build();
                categoryRepository.save(category);
            });
            Random random=new Random();
            categoryRepository.findAll().forEach(category -> {
                for (int i = 0; i < 10; i++) {
                    Product product=Product.builder()
                            .id(UUID.randomUUID().toString())
                            .name("Computer "+i)
                            .price(100+Math.random()*50000)
                            .quantity(random.nextInt(100))
                            .category(category)
                            .build();
                    productRepository.save(product);
                }

            });
        };
    }
}
