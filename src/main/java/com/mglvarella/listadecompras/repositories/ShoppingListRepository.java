package com.mglvarella.listadecompras.repositories;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

}
