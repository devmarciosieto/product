package br.com.mmmsieto.product.app.v1.controller;

import br.com.mmmsieto.product.app.v1.controller.request.ProductRequest;
import br.com.mmmsieto.product.app.v1.controller.response.ProductResponse;
import br.com.mmmsieto.product.domain.service.ProductService;
import br.com.mmmsieto.product.infrastructure.client.SupplierClient;
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
    private final SupplierClient supplierClient;

    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             SupplierClient supplierClient) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.supplierClient = supplierClient;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@Valid @RequestBody ProductRequest product) {
        productService.save(productMapper.toProductEntity(product));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse findById(@PathVariable("id") String id) {

        String company = supplierClient.getSuppliers(id)
                .stream().findFirst().orElse(null)
                .getCompany();

        return productMapper.toProductResponseWithCompany(productService.findById(id), company);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> findAll() {
        return productService.findAll().stream()
                .map(productMapper::toProductResponse)
                .toList();
    }

}
