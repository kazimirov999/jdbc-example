package com.jdbc.example.domain;

import com.jdbc.example.jpa.mapping.annotation.Column;
import com.jdbc.example.jpa.mapping.annotation.Entity;
import com.jdbc.example.jpa.mapping.annotation.Table;

@Entity
@Table("PRODUCT")
public class Product {

    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PRISE")
    private double prise;
    private int category_id;

    public Product(int id, String name, double prise, int category_id) {
        this.id = id;
        this.name = name;
        this.prise = prise;
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", prise=" + prise +
                ", category_id=" + category_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrise() {
        return prise;
    }

    public int getCategory_id() {
        return category_id;
    }
}
