package com.lambdaschool.javazoos.controller;

import com.lambdaschool.javazoos.model.Animal;
import com.lambdaschool.javazoos.repository.Animalrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/animals", produces = MediaType.APPLICATION_JSON_VALUE)
public class Animalcontroller
{
    @Autowired
    Animalrepository animalrepos;

    @GetMapping("")
    public List<Animal> findAllAnimalsWithTheirZoo()
    {
        return animalrepos.findAll();
    }

    @GetMapping("/animaltype/{type}")
    public Animal findAnimalByName(@PathVariable String type)
    {
        return animalrepos.findAnimalByAnimaltypeContains(type);
    }

}
