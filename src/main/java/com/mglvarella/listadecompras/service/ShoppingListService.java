package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingListDTO;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList createShoppingList(ShoppingListDTO shoppingListDTO) {
        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setListName(shoppingListDTO.name());
        newShoppingList.setCreationDate(LocalDate.now());

        shoppingListRepository.save(newShoppingList);

        return newShoppingList;
    }

    public List<ShoppingList> findAll() {
        return shoppingListRepository.findAll();

    }

    public Optional<ShoppingList> findById(Long id) {
        return shoppingListRepository.findById(id);
    }
}
