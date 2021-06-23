/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.monolith.service;

import com.ksm.monolith.model.Belonging;
import com.ksm.monolith.model.Person;
import com.ksm.monolith.model.Wadah;
import com.ksm.monolith.repository.BelongingRepository;
import com.ksm.monolith.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Dony Tri P
 */
@Service
public class PersonService {

    @Autowired //dependency injection 
    PersonRepository personRepository;
    @Autowired
    BelongingRepository belongingRepository;

    //Create
    public boolean save(Person person) {
        Person p = personRepository.save(person);
        return personRepository.existsById(p.getId());
    }
    
    public boolean save(Belonging belonging) {
        Belonging b = belongingRepository.save(belonging);
        return belongingRepository.existsById(b.getId());
    }

    //Create
    public Person insert(Person person) {
        if (person.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        return personRepository.save(person);
    }
    
    public void insert(Wadah wadah) {
        Person person = new Person();
        
        if (wadah.getid() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        personRepository.save(person);
    }
    
    public Belonging insert(Belonging belonging, Integer id) {
        if (belonging.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
        Person person = getById(id);
        personRepository.save(person);
        return belongingRepository.save(belonging);
    }

    //Read All
    public List<Person> getAllPerson() {
        List<Person> people = personRepository.findAll();
        return people;
    }
    
    public List<Belonging> getAllBelonging() {
        List<Belonging> belongings = belongingRepository.findAll();
        return belongings;
    }

//    public void dummyData() {
//        List<Person> people = new ArrayList<>();
//
//        people.add(new Person("Aqira", "Kelana"));
//        people.add(new Person("Kevin", "Harlim"));
//        people.add(new Person("Lois", "Ceka"));
//        personRepository.saveAll(people);
//    }

    //Read -> getById(Integer id) -> findById(id)
    public Person getById(Integer id) {
        if (personRepository.existsById(id)) {
            return personRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
    public Belonging getByIdBelonging(Integer id) {
        if (belongingRepository.existsById(id)) {
            return belongingRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    //Update -> update(Integer id) -> 1 Kevin Harlim
    public Person update(Integer id, Person person) {
        Person oldPerson = getById(id);
        oldPerson.setFirstName(person.getFirstName());
        oldPerson.setLastName(person.getLastName());
        oldPerson.setGender(person.getGender());

        return personRepository.save(oldPerson);
    }
    
    public Belonging updateBelonging(Integer id, Belonging belonging) {
        Belonging oldBelonging = getByIdBelonging(id);
        oldBelonging.setName(belonging.getName());
        oldBelonging.setQuantity(belonging.getQuantity());
        oldBelonging.setPerson(belonging.getPerson());

        return belongingRepository.save(oldBelonging);
    }
    
    //Delete -> deleteById(Integer id)
    public Person deleteById(Integer id) {
        Person person = getById(id);
        personRepository.delete(person);
        return person;
    }
    
    public Belonging deleteByIdBelonging(Integer id) {
        Belonging belonging = getByIdBelonging(id);
        belongingRepository.delete(belonging);
        return belonging;
    }
    public Person givePencilAndEraser(Belonging belonging){
       Person person = getById(belonging.getId());
       List<Belonging> belongings = new ArrayList<>();
       belongings.add(new Belonging(belonging.getName(), belonging.getQuantity(), person));
       List<Belonging> insertedBelongings = belongingRepository.saveAll(belongings); 
       person.setBelongings(insertedBelongings);
       personRepository.save(person);
       return person;
    }
    
    
   
}
