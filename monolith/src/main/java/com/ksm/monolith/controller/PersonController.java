/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.monolith.controller;

import com.ksm.monolith.model.Belonging;
import com.ksm.monolith.model.Person;
import com.ksm.monolith.model.Wadah;
import com.ksm.monolith.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Dony Tri P
 */
@RestController //Restful API
@RequestMapping("api/person") // localhost:8088/api/person
public class PersonController {

    @Autowired
    PersonService personService;

    /**
     * - GET    -> getAll() 
     * - GET    -> getById() 
     * - POST    -> save(Person person) 
     * - PUT    -> saveNewLastName(Integer id, String lastName) 
     * - DELETE    -> delete(Integer id)
     */
    
    @GetMapping()
    public List<Person> getAll(){
        return personService.getAllPerson();
    }
    
    @GetMapping("/{id}") //localhost:8088/api/person/1
    public Person getById(@PathVariable Integer id){
        return personService.getById(id);
    }
    
    @PostMapping()
    public Person save(@RequestBody Person person){
        return personService.insert(person);
    }
    
    @PostMapping("insertBelonging")
    public Belonging save(@RequestBody Belonging belonging, @RequestBody Integer id){
        return personService.insert(belonging, id);
    }
    
    @PutMapping("/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public Person save(@PathVariable Integer id, @RequestBody Person person){
        return personService.update(id, person);
    }
    
    @DeleteMapping("/{id}")
    public Person delete(@PathVariable Integer id){
        return personService.deleteById(id);
    }
    
    @GetMapping("belonging")
    public List<Belonging> getAllBelonging(){
        return personService.getAllBelonging();
    }
    
    @GetMapping("belonging/{id}") //localhost:8088/api/person/1
    public Belonging getByIdBelong(@PathVariable Integer id){
        return personService.getByIdBelonging(id);
    }
    
    @PostMapping("belonging")
    public Person save(@RequestBody Belonging belonging){
//        return personService.insert(person);
       return personService.givePencilAndEraser(belonging);
    }
    
    @PutMapping("belonging/{id}") // Put [..] -> Mudjiarto, Patch Kelana -> Mudjiarto
    public Belonging saveBelonging(@PathVariable Integer id, @RequestBody Belonging belonging){
        return personService.updateBelonging(id, belonging);
    }
    
    @DeleteMapping("belonging/{id}")
    public Belonging deleteBeloging(@PathVariable Integer id){
        return personService.deleteByIdBelonging(id);
    }
    
    
//    @PostMapping("/belonging/{id}")
//    public Person save(@PathVariable Integer id, @RequestBody Belonging belonging){
//        return personService.givePencilAndEraserToNewPersonAndBrand(id);
//    }
}