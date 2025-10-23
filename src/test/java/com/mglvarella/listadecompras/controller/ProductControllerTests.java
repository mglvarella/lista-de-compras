package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.product.ProductCreateDTO;
import com.mglvarella.listadecompras.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void shouldCreateProduct() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ProductCreateDTO productCreateDTO = new ProductCreateDTO("Product A", "Product A Test");
        Product product = new Product(1L, "Product A", "Product A Test");

        when(productService.createProduct(productCreateDTO)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(productCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(productService, times(1)).createProduct(productCreateDTO);

        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void shouldDeleteProduct() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(productService.deleteProduct(1L)).thenReturn(true);

        ResponseEntity<Product> response = productController.deleteProductById(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void shouldNotDeleteProduct() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(productService.deleteProduct(1L)).thenReturn(false);

        ResponseEntity<Product> response = productController.deleteProductById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void shouldReturnProductWhenFoundById() {
        Product product = new Product(1L, "Product A", "Product A Test");
        when(productService.findById(1L)).thenReturn(product);

        ResponseEntity<Product> response = productController.findProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product A", response.getBody().getName());

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
        products.add(new Product(1L, "Product A", "Product A"));
        products.add(new Product(2L, "Product B", "Product B"));

        when(productService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.findAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Product A", response.getBody().get(0).getName());
        assertEquals("Product B", response.getBody().get(1).getName());

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
