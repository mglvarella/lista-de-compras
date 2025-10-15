package com.mglvarella.listadecompras.domain.shoppinglistitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ShoppingListItemCreateDTO(
        @NotNull(message = "É necessário informar o produto") Long productId,
        @NotNull @Positive(message = "A quantidade deve ser maior que zero") Long quantity,
        @NotNull @Positive(message = "O preço deve ser maior que zero") BigDecimal itemPrice
) {}
