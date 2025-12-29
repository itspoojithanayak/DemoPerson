package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {


    @Autowired
    public PersonRepo personRepo;

    @PostMapping("/addPerson")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        Person response = personRepo.save(person);
        return ResponseEntity.ok(response);

    }
    @PostMapping("/addAllPerson")
    public List<Person> addAllPerson(@RequestBody List<Person> personList ){
        return personRepo.saveAll(personList);
    }
    @GetMapping("/getAll")
    public List<Person> getAllPerson(){
        List<Person> personList = personRepo.findAll();
        return personList;
    }
    @GetMapping("/getById/{id}")
    public Person getPerson(@PathVariable Long id){
        Person person = personRepo.findById(id).orElse(null);
        return person;
    }
    @PutMapping("/updateById/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person person){
        Person existingData = personRepo.findById(id).orElse(null);
        if(existingData!=null){
            existingData.setName(person.getName());
            existingData.setEmail(person.getEmail());
            personRepo.save(existingData);
            return existingData;
        }else{
            return null;
        }
    }
    @DeleteMapping("/deleteById/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepo.deleteById(id);
    }

}
