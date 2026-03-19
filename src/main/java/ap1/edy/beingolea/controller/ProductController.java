package ap1.edy.beingolea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ap1.edy.beingolea.model.Product;
import ap1.edy.beingolea.service.ProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequestMapping("product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> listarProductos() {
        return productService.listarProductos();
    }

}
