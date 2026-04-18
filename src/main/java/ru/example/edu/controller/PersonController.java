package ru.example.edu.controller;

import org.springframework.web.bind.annotation.*;
import ru.example.edu.entity.Person;
import ru.example.edu.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @PostMapping("/person")
    public Person create(@RequestBody Person person) {
        Person saved = personRepository.save(person);
        return saved;
    }

    @GetMapping("/person")
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @GetMapping("/person{id}")
    public Person getById(@PathVariable long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        Person person;
        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
        } else {
            throw new RuntimeException("Пользователь не найден!");
        }
        return person;
    }

    @PutMapping("/person{id}")
    public Person update(@PathVariable long id, @RequestBody Person person) {
        person.setId(id);
        Person saved = personRepository.save(person);
        return saved;
    }

    @DeleteMapping("/person{id}")
    public void deleteById(@PathVariable long id) {
        personRepository.deleteById(id);
    }
}
