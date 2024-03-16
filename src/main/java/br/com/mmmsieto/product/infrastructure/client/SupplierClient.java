package br.com.mmmsieto.product.infrastructure.client;

import br.com.mmmsieto.product.domain.entity.SupplierEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "SupplierClient", url = "${client.supplier.url}")
public interface SupplierClient {

    @GetMapping("/suppliers/{idProduct}")
    List<SupplierEntity> getSuppliers(@PathVariable("idProduct") String idProduct);

}
