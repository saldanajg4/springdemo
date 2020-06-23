package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.demo.dao.PersonDao;
import com.example.demo.models.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonDao personDao;

    /***
     * We're autowiring DAO interface into service.  And if Qualifier is 
     * mongo, or mysql just change the qualifier name for different 
     * implementation
     * @param pDao
     */
    @Autowired
    // public PersonService(@Qualifier("fakeDao") PersonDao pDao) {
        public PersonService(@Qualifier("postgres") PersonDao pDao) {
        this.personDao = pDao;
    }
    
    /***
     * We have the option to provide the id or not.  If not, UUID will be
     * randomly generated
     * @param person
     * @return
     */
    public int insertPerson(Person person){
        return this.personDao.insertPerson(person);
    } 
    public List<Person> getAllPeople(){
        return this.personDao.selectAll();
    }
    public Optional<Person> getPersonById(UUID id){
        return this.personDao.selectPersonById(id);
    }
    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }
    public int updatePerson(UUID id, Person person){
        return personDao.updatePersonById(id, person);
    }
}