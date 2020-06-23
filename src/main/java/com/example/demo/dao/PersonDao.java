package com.example.demo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.models.Person;

/***
 * interface to add a person with id and person and another one with person
 * only and another one randomly generated id 
 */
public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }

    List<Person> selectAll();

    /***
     * To handle values as available or not available
     * @param id
     * @return
     */
    Optional<Person> selectPersonById(UUID id);
    
    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);


}