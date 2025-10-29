package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductCreateDTO;
import com.mglvarella.listadecompras.domain.product.ProductUpdateDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductCreateDTO data){
        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());

        productRepository.save(newProduct);

        return newProduct;
    }

    public Product updateProduct(Long id, ProductUpdateDTO data){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

        if(data.name() != null){
            product.setName(data.name());
        }

        if(data.description() != null){
            product.setDescription(data.description());
        }

        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long id){
        Product productToDelete = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));

        productRepository.deleteById(id);
    }

    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found: " + id));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
