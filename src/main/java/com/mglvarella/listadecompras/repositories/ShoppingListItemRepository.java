package com.mglvarella.listadecompras.repositories;

import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShoppingListItemRepository extends JpaRepository<ShoppingListItem, Long> {

    Optional<ShoppingListItem> findByIdAndShoppingListId(Long itemId, Long listId);

}
