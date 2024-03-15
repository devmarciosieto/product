package br.com.mmmsieto.product.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductEntity {

    private ObjectId id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private CategoryEntity category;

}
