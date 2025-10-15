package com.mglvarella.listadecompras.controller;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListRequestDTO;
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
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody @Valid ShoppingListRequestDTO data) {
        ShoppingList shoppingList = this.shoppingListService.createShoppingList(data);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(shoppingList.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable Long id, @RequestBody @Valid ShoppingListRequestDTO data) {
        try{
            return ResponseEntity.ok(this.shoppingListService.updateShoppingList(id, data));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ShoppingList>> findAllShoppingLists() {
        List<ShoppingList> shoppingLists = this.shoppingListService.findAll();
        return shoppingLists.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(shoppingLists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingList> findShoppingListById(@PathVariable("id") Long id) {
       ShoppingList product = this.shoppingListService.findById(id);
        return ResponseEntity.ok(product);
    }
}