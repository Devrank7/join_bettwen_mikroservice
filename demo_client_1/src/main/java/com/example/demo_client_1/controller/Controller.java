package com.example.demo_client_1.controller;

import com.example.demo_client_1.model.Person;
import com.example.demo_client_1.repositiory.IPerson;
import com.example.demo_client_1.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cl")
public class Controller {
    private IPerson iPerson;
    private KafkaService kafkaService;

    @Autowired
    public Controller(IPerson iPerson, KafkaService kafkaService) {
        this.iPerson = iPerson;
        this.kafkaService = kafkaService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> responseEntity() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Person>> response() {
        List<Person> list = iPerson.findAll();
        for (Person person : list) {
            person.setItemLoader(kafkaService.getItemLoader(person));
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Person> responseEntityi(@PathVariable("id") int id) {
        Person person = iPerson.findById(id).orElseThrow();
        person.setItemLoader(kafkaService.getItemLoader(person));
        return new ResponseEntity<>(iPerson.findById(id).orElseThrow(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Person> crete(@RequestBody Person item) {
        return new ResponseEntity<>(iPerson.save(item), HttpStatus.CREATED);
    }

    @GetMapping("/bet/{id}")
    public ResponseEntity<String> testing(@PathVariable("id") int id) {
        Person person = new Person("Roma", 13, "qwq@doj.cpm");
        person.setId(id);
        kafkaService.sendMessage(person);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable("id") int id) {
        person.setId(id);
        return new ResponseEntity<>(iPerson.save(person), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus delete(@PathVariable("id") int id) {
        iPerson.deleteById(id);
        return HttpStatus.ACCEPTED;
    }


}
