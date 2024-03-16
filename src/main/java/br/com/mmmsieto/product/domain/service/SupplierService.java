package br.com.mmmsieto.product.domain.service;

import br.com.mmmsieto.product.infrastructure.client.SupplierClient;
import br.com.mmmsieto.product.infrastructure.entity.SupplierEntity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SupplierService {

    private final SupplierClient supplierClient;

    public SupplierService(SupplierClient supplierClient) {
        this.supplierClient = supplierClient;
    }

    @CircuitBreaker(name = "suppliers", fallbackMethod = "findByIdFallback")
    public List<SupplierEntity> getSupplier(String id) {
        return supplierClient.getSuppliers(id);
    }

    private List<SupplierEntity>  findByIdFallback(String id, Throwable throwable) {
        log.warn("[WARN] fallback with id {}", id);
        return List.of();
    }

}
