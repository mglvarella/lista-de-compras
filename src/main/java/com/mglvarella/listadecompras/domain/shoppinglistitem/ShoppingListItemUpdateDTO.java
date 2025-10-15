package com.mglvarella.listadecompras.domain.shoppinglistitem;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ShoppingListItemUpdateDTO(
        @NotNull @Positive(message = "A quantidade deve ser maior que zero") Long quantity,
        @NotNull @Positive(message = "O preço deve ser maior que zero") BigDecimal itemPrice
) {}

