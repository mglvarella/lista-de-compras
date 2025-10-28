package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListCreateDTO;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListUpdateDto;
import com.mglvarella.listadecompras.service.ShoppingListService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.net.URI;

@RestController
@RequestMapping("/shoppinglists")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody @Valid ShoppingListCreateDTO data) {
        ShoppingList shoppingList = this.shoppingListService.createShoppingList(data);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(shoppingList.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable Long id, @RequestBody @Valid ShoppingListUpdateDto data) {
        ShoppingList shoppingList = this.shoppingListService.updateShoppingList(id, data);
        return shoppingList == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(shoppingList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShoppingList> deleteShoppingList(@PathVariable Long id){
       return shoppingListService.deleteShoppingList(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity<List<ShoppingList>> findAllShoppingLists() {
        List<ShoppingList> shoppingLists = this.shoppingListService.findAll();
        return shoppingLists.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(shoppingLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> findShoppingListById(@PathVariable("id") Long id) {
       ShoppingList shoppingList = this.shoppingListService.findById(id);
        return shoppingList == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(shoppingList);
    }
}