package br.com.mmmsieto.product.domain.service;

import br.com.mmmsieto.product.app.v1.controller.response.ProductResponse;
import br.com.mmmsieto.product.domain.entity.ProductEntity;
import br.com.mmmsieto.product.infrastructure.entity.SupplierEntity;
import br.com.mmmsieto.product.infrastructure.mapper.ProductMapper;
import br.com.mmmsieto.product.infrastructure.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierService supplierService;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          SupplierService supplierService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierService = supplierService;
    }

    public void save(ProductEntity productEntity) {
        productRepository.save(productMapper.toProductModel(productEntity));
    }

    public ProductResponse findById(String id) {
        List<SupplierEntity> supplierEntityList = supplierService.getSupplier(id);

        String company =supplierEntityList.stream()
                .findFirst()
                .map(SupplierEntity::getCompany)
                .orElse(null);

        ObjectId objectId = new ObjectId(id);
        ProductEntity productEntity = productRepository.findById(objectId)
                .map(productMapper::toProductEntity)
                .orElseThrow(RuntimeException::new);
        return productMapper.toProductResponseWithCompany(productEntity, company);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductEntity)
                .toList();
    }

}
