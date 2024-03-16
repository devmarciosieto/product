package br.com.mmmsieto.product.domain.service;

import br.com.mmmsieto.product.domain.entity.SupplierEntity;
import br.com.mmmsieto.product.infrastructure.client.SupplierClient;
import br.com.mmmsieto.product.infrastructure.service.RedisService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SupplierService {

    private final SupplierClient supplierClient;
    private final RedisService redisService;

    public SupplierService(SupplierClient supplierClient,
                           RedisService redisService) {
        this.supplierClient = supplierClient;
        this.redisService = redisService;
    }

    @CircuitBreaker(name = "suppliers", fallbackMethod = "findByIdFallback")
    public List<SupplierEntity> getSupplier(String id) {
        var suppliers = supplierClient.getSuppliers(id);
        redisService.save(suppliers, id);
        return suppliers;
    }

    private List<SupplierEntity>  findByIdFallback(String id, Throwable throwable) {
        log.warn("[WARN] fallback with id {}", id);
        return List.of();
    }

}
