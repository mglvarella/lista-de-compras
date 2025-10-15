package com.mglvarella.listadecompras.service;

import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemCreateDTO;
import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItemUpdateDTO;
import com.mglvarella.listadecompras.repositories.ProductRepository;
import com.mglvarella.listadecompras.repositories.ShoppingListItemRepository;
import com.mglvarella.listadecompras.repositories.ShoppingListRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShoppingListItemService {

    private final ShoppingListItemRepository shoppingListItemRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingListItemService(
            ShoppingListItemRepository shoppingListItemRepository,
            ShoppingListRepository shoppingListRepository,
            ProductRepository productRepository
    ) {
        this.shoppingListItemRepository = shoppingListItemRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public List<ShoppingListItem> addItemsToList(Long listId, List<ShoppingListItemCreateDTO> items) {
        if (items == null || items.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A lista de itens não pode ser vazia");
        }
        ShoppingList shoppingList = shoppingListRepository.findById(listId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada"));

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtos não encontrados: " + missing);
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista não encontrada"));

        ShoppingListItem shoppingListItem = shoppingListItemRepository.findByIdAndShoppingListId(itemId, listId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado"));

        shoppingListItem.setQuantity(dto.quantity());
        shoppingListItem.setPrice(dto.itemPrice());

        return shoppingListItem;
    }

    @Transactional
    public ShoppingListItem markItemAsPurchased(Long listId, Long itemId) {
        ShoppingListItem item = shoppingListItemRepository
                .findByIdAndShoppingListId(itemId, listId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado nesta lista"));

        item.setPurchased(true);
        return item;
    }



}
