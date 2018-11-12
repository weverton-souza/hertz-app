package com.rent.hertz.model;

import java.util.Objects;

public class Vehicle {

    private long id;

    private Manufacturer make;

    private Model model;

    private Category category;


    public long getId() {
        return id;
    }

    public Vehicle setId(long id) {
        this.id = id;
        return this;
    }

    public Manufacturer getMake() {
        return make;
    }

    public Vehicle setMake(Manufacturer make) {
        this.make = make;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Vehicle setModel(Model model) {
        this.model = model;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Vehicle setCategory(Category category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make=" + make +
                ", model=" + model +
                ", category=" + category +
                '}';
    }
}
