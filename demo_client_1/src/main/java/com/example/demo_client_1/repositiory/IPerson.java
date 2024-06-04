package com.example.demo_client_1.repositiory;

import com.example.demo_client_1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerson extends JpaRepository<Person, Integer> {
}
