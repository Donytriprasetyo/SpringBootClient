/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.monolith.service;

import com.ksm.monolith.model.Belonging;
import com.ksm.monolith.model.Person;
import com.ksm.monolith.repository.BelongingRepository;
import com.ksm.monolith.repository.PersonRepository;
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
public class BelongingService {
    @Autowired
    BelongingRepository belongingRepository;
    @Autowired
    PersonRepository personRepository;
    
    public boolean save(Belonging belonging) {
        Belonging b = belongingRepository.save(belonging);
        return belongingRepository.existsById(b.getId());
    }
    
    public Belonging insert(Belonging belonging) {
        if (belonging.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exists");
        }
//        personRepository.save(person);
        return belongingRepository.save(belonging);
    }
    
    public List<Belonging> getAllBelonging() {
        List<Belonging> belongings = belongingRepository.findAll();
        return belongings;
    }
    
    public Belonging getById(Integer id) {
        if (belongingRepository.existsById(id)) {
            return belongingRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
    public Belonging update(Integer id, Belonging belonging) {
        Belonging oldBelonging = getById(id);
        oldBelonging.setName(belonging.getName());
        oldBelonging.setQuantity(belonging.getQuantity());
        oldBelonging.setPerson(belonging.getPerson());

        return belongingRepository.save(oldBelonging);
    }
    
    public Belonging deleteById(Integer id) {
        Belonging belonging = getById(id);
        belongingRepository.delete(belonging);
        return belonging;
    }

}
