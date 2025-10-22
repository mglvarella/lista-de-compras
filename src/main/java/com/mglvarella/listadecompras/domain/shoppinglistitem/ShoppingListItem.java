package com.mglvarella.listadecompras.domain.shoppinglistitem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mglvarella.listadecompras.domain.product.Product;
import com.mglvarella.listadecompras.domain.shoppinglist.ShoppingList;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class ShoppingListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    @JsonBackReference
    private ShoppingList shoppingList;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal price;

    private Long quantity;

    private boolean purchased;

    public ShoppingListItem() {
    }

    public ShoppingListItem(Long id, ShoppingList shoppingList, Product product, Long quantity, BigDecimal price, boolean purchased) {
        this.id = id;
        this.shoppingList = shoppingList;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.purchased = purchased;
    }

    public ShoppingListItem(ShoppingList shoppingList, Product product, Long quantity, BigDecimal price) {
        this.shoppingList = shoppingList;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.purchased = false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingListItem that = (ShoppingListItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ShoppingListItem{" +
                "id=" + id +
                ", shoppingListId=" + (shoppingList != null ? shoppingList.getId() : "null") +
                ", productId=" + (product != null ? product.getId() : "null") +
                ", price=" + price +
                ", quantity=" + quantity +
                ", purchased=" + purchased +
                '}';
    }
}