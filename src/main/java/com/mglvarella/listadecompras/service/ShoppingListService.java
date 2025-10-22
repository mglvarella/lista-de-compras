package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListCreateDTO;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListUpdateDto;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList createShoppingList(ShoppingListCreateDTO shoppingListDTO) {
        ShoppingList newShoppingList = new ShoppingList(shoppingListDTO.name(), shoppingListDTO.description());

        shoppingListRepository.save(newShoppingList);

        return newShoppingList;
    }

    public ShoppingList updateShoppingList(Long id, ShoppingListUpdateDto shoppingListDTO) {
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElse(null);

        if (shoppingList == null) {
            return null;
        }

        if(shoppingListDTO.name() != null){
            shoppingList.setName(shoppingListDTO.name());
        }

        if(shoppingListDTO.description() != null){
            shoppingList.setName(shoppingListDTO.description());
        }

        shoppingListRepository.save(shoppingList);
        return shoppingList;
    }

    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList findById(Long id) {
        return shoppingListRepository.findById(id)
                .orElse(null);
    }
}
