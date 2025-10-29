package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemCreateDTO;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemUpdateDTO;
import com.mglvarella.listadecompras.service.ShoppingListItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingListItemController {

    private final ShoppingListItemService shoppingListItemService;
    public ShoppingListItemController(ShoppingListItemService shoppingListItemService) {
        this.shoppingListItemService = shoppingListItemService;
    }

    @PostMapping("/{listId}/items")
    public ResponseEntity<List<ShoppingListItem>> addItems(
            @PathVariable Long listId,
            @Valid @RequestBody List<ShoppingListItemCreateDTO> items
    ) {
        List<ShoppingListItem> createdItems = shoppingListItemService.addItemsToList(listId, items);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItems);
    }

    @PutMapping("/{listId}/items/{itemId}")
    public ResponseEntity<ShoppingListItem> updateItem(@PathVariable Long listId, @PathVariable Long itemId, @RequestBody ShoppingListItemUpdateDTO dto) {
        ShoppingListItem updatedItem = shoppingListItemService.updateItem(listId, itemId, dto);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{listId}/items/{itemId}")
    public ResponseEntity<String> deleteItemFromList(@PathVariable Long listId, @PathVariable Long itemId){
        shoppingListItemService.removeItem(listId, itemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{listId}/items/")
    public ResponseEntity<List<ShoppingListItem>> findAllItems(@PathVariable Long listId){
        return ResponseEntity.ok(shoppingListItemService.findAllItems(listId));
    }

    @GetMapping("/{listId}/items/{itemId}")
    public ResponseEntity<ShoppingListItem> findById (@PathVariable Long listId, @PathVariable Long itemId){
        ShoppingListItem shoppingListItem = shoppingListItemService.findItem(listId, itemId);
        return ResponseEntity.ok(shoppingListItem);
    }
}
