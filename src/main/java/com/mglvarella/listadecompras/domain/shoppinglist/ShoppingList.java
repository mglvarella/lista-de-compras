package com.mglvarella.listadecompras.domain.shoppinglist;

import com.mglvarella.listadecompras.domain.shoppinglistitem.ShoppingListItem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate creationDate;

    private String description;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShoppingListItem> items;

    public ShoppingList(String listName, String description) {
        this.name = listName;
        this.setCreationDate(LocalDate.now());
    }

    public ShoppingList() {
    }

    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String listName) { this.name = listName; }
    public LocalDate getCreationDate() { return creationDate; }
    private void setCreationDate(LocalDate creationDate) { this.creationDate = creationDate; }
    public List<ShoppingListItem> getItems() { return items; }
    public void setItems(List<ShoppingListItem> items) { this.items = items; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingList that = (ShoppingList) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}