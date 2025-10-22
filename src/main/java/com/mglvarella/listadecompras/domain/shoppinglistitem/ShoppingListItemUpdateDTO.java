package com.mglvarella.listadecompras.domain.shoppinglistitem;

import java.math.BigDecimal;
import jakarta.validation.constraints.Positive;


public record ShoppingListItemUpdateDTO(
        @Positive(message = "A quantidade deve ser maior que zero") Long quantity,
        @Positive(message = "O preço deve ser maior que zero") BigDecimal itemPrice,
        Boolean purchased
    ) {}

