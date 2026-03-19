package ap1.edy.beingolea.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import ap1.edy.beingolea.model.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

}
