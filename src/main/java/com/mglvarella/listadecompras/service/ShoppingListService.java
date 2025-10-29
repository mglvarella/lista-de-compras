package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListCreateDTO;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListUpdateDto;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import jakarta.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException("ShoppingList not found: " + id));

        if(shoppingListDTO.name() != null){
            shoppingList.setName(shoppingListDTO.name());
        }

        if(shoppingListDTO.description() != null){
            shoppingList.setName(shoppingListDTO.description());
        }

        shoppingListRepository.save(shoppingList);
        return shoppingList;
    }

    public void deleteShoppingList(Long id){
        ShoppingList shoppingListToDelete = shoppingListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingList not found: " + id));

        shoppingListRepository.deleteById(id);
    }

    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();
    }

    public ShoppingList findById(Long id) {
        return shoppingListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ShoppingList not found: " + id));
    }
}
