package com.mglvarella.listadecompras.repositories;

import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {
}
