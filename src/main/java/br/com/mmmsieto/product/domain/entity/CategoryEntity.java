package br.com.mmmsieto.product.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
public class CategoryEntity {

    private ObjectId id;

    private String name;

    private String description;

    private List<ProductEntity> products;

}
