package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductCreateDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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

        assertThrows(EntityNotFoundException.class, () -> productService.findById(2L));
        verify(productRepository, times(1)).findById(2L);
    }

    @Test
    public void shouldFindAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Test Product", "Test Product find all id 1"));
        products.add(new Product(2L, "Test Product", "Test Product find all id 2"));

        when(productRepository.findAll()).thenReturn(products);

        assertEquals(products, productService.findAll());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void shouldNotFindAnyProducts() {
        List<Product> products = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(products);

        assertEquals(products, productService.findAll());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void shouldDeleteProduct() {
        Long id = 1L;

        when(productRepository.existsById(id)).thenReturn(true);
        doNothing().when(productRepository).deleteById(id);

        assertDoesNotThrow(() -> productService.deleteProduct(id));

        verify(productRepository, times(1)).deleteById(id);
        verify(productRepository, times(1)).existsById(id);
    }

    @Test
    public void shouldNotDeleteProductThatNotExists() {
        Long id = 1L;

        when(productRepository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> productService.deleteProduct(id));

        verify(productRepository, times(1)).existsById(id);
        verify(productRepository, never()).deleteById(id);
    }
}
