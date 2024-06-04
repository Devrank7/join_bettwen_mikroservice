package com.example.demo_client_1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private String email;
    @Transient
    private List<Item_Loader> itemLoader = new ArrayList<>();

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
