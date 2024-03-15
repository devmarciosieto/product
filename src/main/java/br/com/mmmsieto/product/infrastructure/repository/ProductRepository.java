package br.com.mmmsieto.product.infrastructure.repository;

import br.com.mmmsieto.product.infrastructure.entity.ProductModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, ObjectId> {
}
