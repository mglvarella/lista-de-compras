package com.mglvarella.listadecompras.repositories;

import com.mglvarella.listadecompras.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
