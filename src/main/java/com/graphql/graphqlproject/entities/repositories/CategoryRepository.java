package com.graphql.graphqlproject.entities.repositories;

import com.graphql.graphqlproject.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
