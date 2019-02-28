package com.lambdaschool.javazoos.controller;

import com.lambdaschool.javazoos.model.Zoo;
import com.lambdaschool.javazoos.repository.Zoorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = {"/zoos"})
public class Zoocontroller
{
    @Autowired
    Zoorepository zoorepos;

    @GetMapping()
    public List<Zoo> getAllZoosAndTheirPhoneNumbersAndAnimals()
    {
        return zoorepos.findAll();
    }

    @GetMapping("/name/{name}")
    public List<Zoo> findZooByName(@PathVariable String name)
    {
        return  zoorepos.findZooByZoonameLike(name);
    }

    @GetMapping("/namecontains/{name}")
    public List<Zoo> findZooByNameContains(@PathVariable String name)
    {
        return  zoorepos.findZooByZoonameContains(name);
    }
}
