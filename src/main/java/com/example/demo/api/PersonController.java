package com.example.demo.api;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.example.demo.models.Person;
import com.example.demo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * Add the endpoint to be used by clients to make a requestks
 */

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    private final PersonService personService;

    /***
     * dependency injection
     * 
     * @param personService
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /***
     * for this we don't want to return integer, instead return a http status code
     * Or Exceptions. This api layer is the one clients will interact with. We can
     * write http calls using api layer So make this method a POST request coming
     * from clients.
     * 
     * @RequestBody shoved it into Person. It's json obj from client into property
     */

    @PostMapping
    public void insertPerson(@Valid @NonNull @RequestBody Person person) {
        this.personService.insertPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return this.personService.getAllPeople();
    }

    /**
     * If not found, you may send a 404 code/page to user.  {id} or /{id}
     * same thing
     * @param id
     * @return
     */
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return this.personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePersonById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody Person person){
        personService.updatePerson(id, person);
    }
    
}