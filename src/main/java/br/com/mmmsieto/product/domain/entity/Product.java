package br.com.mmmsieto.product.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Product {

    private UUID id;

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private Category category;

}
