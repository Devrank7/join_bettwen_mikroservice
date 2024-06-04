package com.example.demo_client_1_1.controller;

import com.example.demo_client_1_1.IItem;
import com.example.demo_client_1_1.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cl1")
public class Controller {

    private IItem iItem;

    @Autowired
    public Controller(IItem iItem) {
        this.iItem = iItem;
    }

    @GetMapping("/test")
    public ResponseEntity<String> responseEntity() {
        return new ResponseEntity<>("Hello 1", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Item>> response() {
        return new ResponseEntity<>(iItem.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Item> responseEntityi(@PathVariable("id") int id) {
        return new ResponseEntity<>(iItem.findById(id).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> crete(@RequestBody Item item) {
        return new ResponseEntity<>(iItem.save(item), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> update(@RequestBody Item person, @PathVariable("id") int id) {
        person.setId(id);
        return new ResponseEntity<>(iItem.save(person), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable("id") int id) {
        iItem.deleteById(id);
        return HttpStatus.ACCEPTED;
    }

}
