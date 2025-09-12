package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListDTO;
import com.mglvarella.listadecompras.service.ShoppingListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shoppinglists")
public class ShoppingListController {
    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody ShoppingListDTO data) {
        ShoppingList shoppingList = this.shoppingListService.createShoppingList(data);
        return ResponseEntity.ok(shoppingList);
    }


    @GetMapping
    public ResponseEntity<List<ShoppingList>> findAllShoppingLists() {
        List<ShoppingList> shoppingLists = this.shoppingListService.findAll();
        return shoppingLists.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(shoppingLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> findShoppingListById(@PathVariable("id") Long id) {
        Optional<ShoppingList> product = this.shoppingListService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}