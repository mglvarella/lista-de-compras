package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductCreateDTO;
import com.mglvarella.listadecompras.domain.product.ProductUpdateDTO;
import com.mglvarella.listadecompras.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductCreateDTO body){
        Product newProduct = this.productService.createProduct(body);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public ResponseEntity<Product> deleteProductById(@RequestBody Long id){
        return productService.deleteProduct(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody ProductUpdateDTO body){
        Product product = this.productService.updateProduct(id, body);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Long id){
        Product product = this.productService.findById(id);
        return product == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts(){
        List<Product> products = this.productService.findAll();
        return ResponseEntity.ok(products);
    }
}