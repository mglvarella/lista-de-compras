package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListRequestDTO;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList createShoppingList(ShoppingListRequestDTO shoppingListDTO) {
        ShoppingList newShoppingList = new ShoppingList(shoppingListDTO.name(), shoppingListDTO.description());

        shoppingListRepository.save(newShoppingList);

        return newShoppingList;
    }

    public ShoppingList updateShoppingList(Long id, ShoppingListRequestDTO shoppingListDTO) {
        Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findById(id);
        if (shoppingListOptional.isEmpty()) {
            throw new RuntimeException("ShoppingList with id " + id + " not found");
        }

        if(shoppingListDTO.name() != null){
            shoppingListOptional.get().setName(shoppingListDTO.name());
        }
        if(shoppingListDTO.description() != null){
            shoppingListOptional.get().setName(shoppingListDTO.description());
        }

        shoppingListRepository.save(shoppingListOptional.get());
        return shoppingListOptional.get();
    }

    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();

    }

    public Optional<ShoppingList> findById(Long id) {
        return shoppingListRepository.findById(id);
    }
}
