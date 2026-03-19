package ap1.edy.beingolea.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ap1.edy.beingolea.model.Product;
import ap1.edy.beingolea.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Flux<Product> listarProductos() {
        return productRepository.findAll();
    }

    public Mono<String> crearProduct(Product product) {
        Product nuevoProduct = Product.builder()
                .id(UUID.randomUUID())
                .nombre(product.getNombre())
                .descripcion(product.getDescripcion())
                .precio(product.getPrecio())
                .stock(product.getStock())
                .categoria(product.getCategoria())
                .marca(product.getMarca())
                .activo(product.getActivo() != null ? product.getActivo() : true)
                .build();

        return productRepository.save(nuevoProduct)
                .map(saved -> "se guardo el producto" + saved.getId());
    }

    public Mono<String> editarProduct(UUID id, Product product) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setNombre(product.getNombre());
                    existingProduct.setDescripcion(product.getDescripcion());
                    existingProduct.setPrecio(product.getPrecio());
                    existingProduct.setStock(product.getStock());
                    existingProduct.setCategoria(product.getCategoria());
                    existingProduct.setMarca(product.getMarca());
                    existingProduct.setActivo(product.getActivo());

                    return productRepository.save(existingProduct);
                })
                .map(saved -> "Producto actualizado correctamente con ID: " + saved.getId())
                .switchIfEmpty(Mono.just("Producto no encontrado"));
    }

    public Mono<String> eliminarProduct(UUID id) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setActivo(false);
                    return productRepository.save(existingProduct);
                })
                .map(saved -> "Producto Eliminado correctamente con ID: " + saved.getId())
                .switchIfEmpty(Mono.just("Producto no encontrado"));
    }

}
