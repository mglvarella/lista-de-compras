package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemCreateDTO;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemUpdateDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShoppingListItemService {

    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingListItemService(
            ShoppingListRepository shoppingListRepository,
            ProductRepository productRepository
    ) {
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<ShoppingListItem> addItemsToList(Long listId, List<ShoppingListItemCreateDTO> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }

        ShoppingList shoppingList = shoppingListRepository.findById(listId)
                .orElse(null);

        if (shoppingList == null) {
            return null;
        }

        List<Long> productIds = items.stream()
                .map(ShoppingListItemCreateDTO::productId)
                .distinct()
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        if (products.size() != productIds.size()) {
            List<Long> foundIds = products.stream().map(Product::getId).toList();
            List<Long> missing = productIds.stream()
                    .filter(id -> !foundIds.contains(id))
                    .toList();
            return null;
        }

        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        List<ShoppingListItem> newItems = items.stream()
                .map(dto -> new ShoppingListItem(
                        shoppingList,
                        productMap.get(dto.productId()),
                        dto.quantity(),
                        dto.itemPrice()
                ))
                .toList();

        shoppingList.getItems().addAll(newItems);
        return newItems;

    }

    @Transactional
    public ShoppingListItem updateItem(Long listId, Long itemId, ShoppingListItemUpdateDTO dto) {
        ShoppingList shoppingList = shoppingListRepository.findById(listId)
                .orElse(null);
        if (shoppingList == null) {
            return null;
        }

        List<ShoppingListItem> items = shoppingList.getItems();

        if (items == null || items.isEmpty()) {
            return null;
        }

        ShoppingListItem itemToUpdate = items.stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElse(null);

        if (itemToUpdate == null) {
            return null;
        }

        if (dto.quantity() != null && !dto.quantity().equals(itemToUpdate.getQuantity())) {
            itemToUpdate.setQuantity(dto.quantity());
        }

        if (dto.itemPrice() != null && !dto.itemPrice().equals(itemToUpdate.getPrice())) {
            itemToUpdate.setPrice(dto.itemPrice());
        }

        if(dto.purchased() != null && itemToUpdate.isPurchased() != dto.purchased()) {
            itemToUpdate.setPurchased(dto.purchased());
        }

        return itemToUpdate;
    }
}
