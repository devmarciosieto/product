package br.com.mmmsieto.product.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Category {

    private UUID id;

    private String name;

    private String description;

    private List<Product> products;

}
