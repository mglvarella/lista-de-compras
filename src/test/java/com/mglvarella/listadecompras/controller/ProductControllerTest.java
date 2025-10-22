package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void shouldReturnProductWhenFoundById() {
        Product product = new Product(1L, "Produto A", "Produto Teste A");
        when(productService.findById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.findProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto A", response.getBody().getName());

        verify(productService, times(1)).findById(1L);
    }

    @Test
    void shouldReturnNotFoundWhenProductDoesNotExist() {
        when(productService.findById(2L)).thenReturn(null);

        ResponseEntity<Product> response = productController.findProductById(2L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(productService, times(1)).findById(2L);
    }

    @Test
    void shouldReturnAllProductsWhenProductsExist() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Produto A", "Produto A"));
        products.add(new Product(2L, "Produto B", "Produto B"));

        when(productService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.findAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Produto A", response.getBody().get(0).getName());
        assertEquals("Produto B", response.getBody().get(1).getName());

        verify(productService, times(1)).findAll();
    }

    @Test
    void shouldReturnNotFoundWhenProductsDoesNotExist() {
        when(productService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Product>> response = productController.findAllProducts();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());

        verify(productService, times(1)).findAll();
    }
}
