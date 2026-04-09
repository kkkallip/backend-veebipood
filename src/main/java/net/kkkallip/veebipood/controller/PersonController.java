package net.kkkallip.veebipood.controller;

import net.kkkallip.veebipood.dto.PersonLoginRecordDto;
import net.kkkallip.veebipood.entity.Person;
import net.kkkallip.veebipood.entity.Product;
import net.kkkallip.veebipood.repository.PersonRepository;
import net.kkkallip.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    //Dependency injection. Kui luuakse klass (PersonController), seotakse ara samal ajal temage koik allolevad muutujad. Injectida voib ka labi ka constructurite
    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("persons")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return personRepository.findAll();
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person) {
        if (person.getId() != null) {
            throw new RuntimeException("Cannot sign up with ID");
        }
        personService.validate(person);
        return personRepository.save(person);
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto dto) {
        Person dbPerson = personRepository.findByEmail(dto.email());
        if (dbPerson == null) {
            throw new RuntimeException("Invalid email");
        }
        if (!dbPerson.getPassword().equals(dto.password())) {
            throw new RuntimeException("Invalid password");
        }
        else return dbPerson;
    }
}
