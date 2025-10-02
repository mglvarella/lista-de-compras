package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemRequestDTO;
import com.mglvarella.listadecompras.repositories.ShoppingListItemRepository;
import com.mglvarella.listadecompras.service.ShoppingListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListItemController {

    private ShoppingListItemService shoppingListItemService;
    public ShoppingListItemController(ShoppingListItemService shoppingListItemService) {
        this.shoppingListItemService = shoppingListItemService;
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<ShoppingList> addItem(
            @PathVariable Long listId,
            @RequestBody List<ShoppingListItemRequestDTO> dto) {

        ShoppingList shoppingList = shoppingListItemService.addItemsToList(listId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(shoppingList);
    }
}
