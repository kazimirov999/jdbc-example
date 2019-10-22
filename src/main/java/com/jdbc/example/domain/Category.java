package com.jdbc.example.domain;

import com.jdbc.example.jpa.mapping.annotation.Column;
import com.jdbc.example.jpa.mapping.annotation.Entity;
import com.jdbc.example.jpa.mapping.annotation.Id;

@Entity
public class Category {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;
}
