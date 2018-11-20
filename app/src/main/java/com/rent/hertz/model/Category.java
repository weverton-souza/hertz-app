package com.rent.hertz.model;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Category implements Serializable {

    private Long id;

    private String description;

    private Double price;

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Category setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return getId() == category.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toJson() {
        return "Category{" +
                "id=" + getId() +
                ", price=" + getPrice() +
                '}';
    }

    @NonNull @Override
    public String toString() {
        return getId() + " - " + getDescription() + " - " + getPrice();
    }
}
