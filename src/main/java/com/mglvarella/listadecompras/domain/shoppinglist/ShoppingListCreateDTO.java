package com.mglvarella.listadecompras.domain.shoppinglist;
import jakarta.validation.constraints.NotBlank;

public record ShoppingListCreateDTO(
        @NotBlank(message = "A lista deve ter um nome")
        String name,
        @NotBlank(message = "A lista deve ter uma descrição")
        String description
) {}
