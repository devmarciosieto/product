package br.com.mmmsieto.product.infrastructure.mapper;

import br.com.mmmsieto.product.app.v1.controller.request.ProductRequest;
import br.com.mmmsieto.product.app.v1.controller.response.ProductResponse;
import br.com.mmmsieto.product.domain.entity.ProductEntity;
import br.com.mmmsieto.product.infrastructure.entity.ProductModel;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductModel toProductModel(ProductEntity productEntity);

    ProductEntity toProductEntity(ProductModel productModel);

    ProductEntity toProductEntity(ProductRequest productRequest);

    @Mapping(target = "id", source = "id", qualifiedByName = "objectIdToString")
    ProductResponse toProductResponse(ProductEntity productEntity);

    @Mapping(target = "id", source = "productEntity.id", qualifiedByName = "objectIdToString")
    @Mapping(target = "company", source = "company")
    ProductResponse toProductResponseWithCompany(ProductEntity productEntity, String company);


    @org.mapstruct.Named("objectIdToString")
    default String objectIdToString(ObjectId objectId) {
        return objectId != null ? objectId.toString() : null;
    }

}
