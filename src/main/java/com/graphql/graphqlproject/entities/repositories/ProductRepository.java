package com.graphql.graphqlproject.entities.repositories;

import com.graphql.graphqlproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
