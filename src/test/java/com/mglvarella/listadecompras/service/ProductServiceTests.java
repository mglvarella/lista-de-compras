package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductCreateDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldCreateProduct() {
        ProductCreateDTO productCreateDTO = new ProductCreateDTO("Create product test", "Create product service test");
        Product product = new Product(1L, "Create product test", "Create product service test");

        when(productRepository.save(any())).thenReturn(product);

        assertEquals(product, productService.createProduct(productCreateDTO));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void shouldFindProductById() {
        Product product = new Product(1L, "Test Product", "Test Product find by id");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertEquals(product, productService.findById(1L));
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void shouldNotFindProductById() {
        when(productRepository.findById(2L)).thenReturn(Optional.empty());

        Product result = productService.findById(2L);

        assertNull(result);
        verify(productRepository, times(1)).findById(2L);
    }
}
