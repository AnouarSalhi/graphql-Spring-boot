package com.graphql.graphqlproject.dto;

import lombok.Data;

public record ProductRequestDto (String id, String name, double price, int quantity, Long categoryId){

}
