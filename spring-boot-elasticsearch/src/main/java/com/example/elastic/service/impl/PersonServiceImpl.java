package com.example.elastic.service.impl;

import com.example.elastic.entities.Person;
import com.example.elastic.repository.PersonRepository;
import com.example.elastic.service.IPersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fzy
 * @description:
 * @date 2023/6/2 14:08
 */
@Service
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    public void doWork() {
        repository.deleteAll();
        Person person = new Person();
        person.setFirstname("Oliver");
        person.setLastname("Gierke");
        repository.save(person);
        List<Person> lastNameResults = repository.findByLastname("Gierke");
        List<Person> firstNameResults = repository.findByFirstnameLike("Oli");
        System.out.println("lastNameResults = " + lastNameResults);
        System.out.println("firstNameResults = " + firstNameResults);

    }
}
