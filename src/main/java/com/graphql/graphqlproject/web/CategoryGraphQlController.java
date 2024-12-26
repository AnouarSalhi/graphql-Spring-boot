package com.graphql.graphqlproject.web;

import com.graphql.graphqlproject.entities.Category;
import com.graphql.graphqlproject.entities.repositories.CategoryRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryGraphQlController {
    public CategoryGraphQlController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private CategoryRepository categoryRepository;
    @QueryMapping
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }
    @QueryMapping
    public Category categoryById(@Argument Long id){
        return categoryRepository.findById(id).orElseThrow(()->new RuntimeException(String.format("Category with id: %s not found!",id)));
    }
}
