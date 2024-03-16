package br.com.mmmsieto.product.app.v1.controller;

import br.com.mmmsieto.product.app.v1.controller.request.ProductRequest;
import br.com.mmmsieto.product.app.v1.controller.response.ProductResponse;
import br.com.mmmsieto.product.domain.service.ProductService;
import br.com.mmmsieto.product.infrastructure.mapper.ProductMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody ProductRequest product) {
        productService.save(productMapper.toProductEntity(product));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findById(@PathVariable("id") String id) {
        return productService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAll() {
        return productService.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

}
