package br.com.mmmsieto.product.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Setter
@Getter
@Document(collection = "project")
public class ProductModel {

    @Id
    private ObjectId id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

}
