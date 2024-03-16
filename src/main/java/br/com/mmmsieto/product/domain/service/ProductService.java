package br.com.mmmsieto.product.domain.service;

import br.com.mmmsieto.product.domain.entity.ProductEntity;
import br.com.mmmsieto.product.infrastructure.mapper.ProductMapper;
import br.com.mmmsieto.product.infrastructure.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void save(ProductEntity productEntity) {
        productRepository.save(productMapper.toProductModel(productEntity));
    }

    public ProductEntity findById(String id) {
        ObjectId objectId = new ObjectId(id);
        return productRepository.findById(objectId)
                .map(productMapper::toProductEntity)
                .orElseThrow(RuntimeException::new);
    }

    public List<ProductEntity> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::toProductEntity)
                .toList();
    }


}
