package com.mglvarella.listadecompras.domain.shoppinglist;

import com.mglvarella.listadecompras.domain.product.Product;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "shopping_list")
@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "shopping_list_product",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public ShoppingList() {
    }

    public ShoppingList(Long id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
