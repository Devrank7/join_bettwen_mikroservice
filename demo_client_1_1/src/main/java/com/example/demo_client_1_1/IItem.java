package com.example.demo_client_1_1;

import com.example.demo_client_1_1.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItem extends JpaRepository<Item, Integer> {
}
