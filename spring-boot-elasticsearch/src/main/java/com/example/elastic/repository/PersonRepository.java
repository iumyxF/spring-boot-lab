package com.example.elastic.repository;

import com.example.elastic.entities.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The interface Person repository.
 *
 * @author iumyxF
 * @description: es crud接口实现
 * @date 2023 /6/2 14:03
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * Find by lastname list.
     *
     * @param lastname the lastname
     * @return the list
     */
    List<Person> findByLastname(String lastname);

    /**
     * Find by firstname like list.
     *
     * @param firstname the firstname
     * @return the list
     */
    List<Person> findByFirstnameLike(String firstname);
}
