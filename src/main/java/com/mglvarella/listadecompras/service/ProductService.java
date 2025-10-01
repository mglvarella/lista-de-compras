package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductRequestDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequestDTO data){
        Product newProduct = new Product();
        newProduct.setName(data.name());
        newProduct.setDescription(data.description());

        productRepository.save(newProduct);

        return newProduct;
    }

    public Product updateProduct(Long id, ProductRequestDTO data){
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isEmpty()){
            throw new RuntimeException("productOptional not found");
        }

        if(data.name() != null){
            productOptional.get().setName(data.name());
        }

        if(data.description() != null){
            productOptional.get().setDescription(data.description());
        }

        productRepository.save(productOptional.get());
        return productOptional.get();
    }

    public boolean deleteProducts(List<Long> ids){
        if(ids.isEmpty())
            return false;
        for(Long id : ids){
            productRepository.deleteById(id);
        }
        return true;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
