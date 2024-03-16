package br.com.mmmsieto.product.infrastructure.service;

import br.com.mmmsieto.product.domain.entity.SupplierEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisService {

    private static final String KEY = "Suppliers";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public RedisService(RedisTemplate<String, Object> redisTemplate,
                        ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void save(List<SupplierEntity> supplierEntityList, String id) {
        redisTemplate.opsForHash().put(KEY, id, supplierEntityList);
    }

    public List<SupplierEntity> findById(String id) {
        Object result = redisTemplate.opsForHash().get(KEY, id);
        if (result instanceof List<?>) {
            List<?> list = (List<?>) result;
            return list.stream()
                    .map(this::convertToSupplierEntity)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private SupplierEntity convertToSupplierEntity(Object map) {
        return objectMapper.convertValue(map, SupplierEntity.class);
    }

}
