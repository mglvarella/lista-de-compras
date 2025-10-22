package com.mglvarella.listadecompras.domain.product;
import jakarta.validation.constraints.NotBlank;

public record ProductCreateDTO(
        @NotBlank(message = "O produto deve ter um nome")
        String name,
        @NotBlank(message = "O produto deve ter uma descrição")
        String description
) {}
