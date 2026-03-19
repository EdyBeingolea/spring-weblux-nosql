package ap1.edy.beingolea.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ap1.edy.beingolea.model.Product;
import ap1.edy.beingolea.service.ProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> listarProductos() {
        return productService.listarProductos();
    }

    @PostMapping
    public Mono<String> crearProducto(@RequestBody Product product) {
        return productService.crearProduct(product);
    }

    @PutMapping("/editar/{id}")
    public Mono<String> editarProducto(@PathVariable UUID id, @RequestBody Product product) {
        return productService.editarProduct(id, product);
    }

    @PutMapping("/eliminar/{id}")
    public Mono<String> eliminarProducto(@PathVariable UUID id) {
        return productService.eliminarProduct(id);
    }
}
