package com.mglvarella.listadecompras.repositories;

import com.mglvarella.listadecompras.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<Product, Long> {

}
