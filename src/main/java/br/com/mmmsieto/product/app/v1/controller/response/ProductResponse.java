package br.com.mmmsieto.product.app.v1.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String company;

}
