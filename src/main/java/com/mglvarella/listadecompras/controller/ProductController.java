package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductRequestDTO;
import com.mglvarella.listadecompras.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO body){
        Product newProduct = this.productService.createProduct(body);
        return ResponseEntity.ok(newProduct);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProductByIds(@RequestBody List<Long> ids){
        return productService.deleteProduct(ids) ? ResponseEntity.ok().body("Successfully deleted") : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") Long id, @RequestBody ProductRequestDTO body){
        try{
            Product product = productService.updateProductById(id, body);
            return ResponseEntity.ok(product);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Product> findProductById(@RequestParam Long id){
        Optional<Product> product = this.productService.findProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all") 
    public ResponseEntity<List<Product>> findAllProducts(){
        List<Product> products = this.productService.findAll();
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }


}
