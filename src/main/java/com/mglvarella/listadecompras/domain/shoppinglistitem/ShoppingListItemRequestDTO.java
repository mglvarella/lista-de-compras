package com.mglvarella.listadecompras.domain.shoppinglistitem;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ShoppingListItemRequestDTO(
        @NotBlank(message = "É necessário adicionar ao menos um produto") Long itemId,
        Long quantity,
        BigDecimal itemPrice
) {
}
