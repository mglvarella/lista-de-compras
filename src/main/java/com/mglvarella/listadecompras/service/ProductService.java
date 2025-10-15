package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductRequestDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        if(data.name() != null){
            product.setName(data.name());
        }

        if(data.description() != null){
            product.setDescription(data.description());
        }

        productRepository.save(product);
        return product;
    }

    public boolean deleteProducts(List<Long> ids){
        if(ids.isEmpty())
            return false;
        for(Long id : ids){
            productRepository.deleteById(id);
        }
        return true;
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não econtrado"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
