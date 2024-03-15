package br.com.mmmsieto.product.infrastructure.mapper;

import br.com.mmmsieto.product.app.v1.controller.request.ProductRequest;
import br.com.mmmsieto.product.domain.entity.ProductEntity;
import br.com.mmmsieto.product.infrastructure.entity.ProductModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductModel toProductModel(ProductEntity productEntity);

    ProductEntity toProductEntity(ProductModel productModel);

    ProductEntity toProductEntity(ProductRequest productRequest);

}
