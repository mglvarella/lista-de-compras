package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListRequestDTO;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
        ShoppingList shoppingList = shoppingListRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encntrada"));

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não econtrado"));
    }
}
