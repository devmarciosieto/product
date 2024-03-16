package br.com.mmmsieto.product.domain.service;

import br.com.mmmsieto.product.app.v1.controller.response.ProductResponse;
import br.com.mmmsieto.product.domain.entity.ProductEntity;
import br.com.mmmsieto.product.infrastructure.client.SupplierClient;
import br.com.mmmsieto.product.infrastructure.mapper.ProductMapper;
import br.com.mmmsieto.product.infrastructure.repository.ProductRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SupplierClient supplierClient;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper, SupplierClient supplierClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.supplierClient = supplierClient;
    }

    public void save(ProductEntity productEntity) {
        productRepository.save(productMapper.toProductModel(productEntity));
    }

    @CircuitBreaker(name = "suppliers")
    public ProductResponse findById(String id) {

        String company = supplierClient.getSuppliers(id)
                .stream().findFirst().orElse(null)
                .getCompany();

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
