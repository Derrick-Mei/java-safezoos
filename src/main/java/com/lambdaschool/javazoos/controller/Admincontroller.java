package com.lambdaschool.javazoos.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lambdaschool.javazoos.model.Animal;
import com.lambdaschool.javazoos.model.Telephone;
import com.lambdaschool.javazoos.model.Zoo;
import com.lambdaschool.javazoos.repository.Animalrepository;
import com.lambdaschool.javazoos.repository.Telephonerepository;
import com.lambdaschool.javazoos.repository.Zoorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Set;

//import com.lambdaschool.javazoos.model.Zooanimals;
//import com.lambdaschool.javazoos.repository.Zooanimalsrepository;

@RestController
@RequestMapping(path = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class Admincontroller
{
    @Autowired
    Animalrepository animalrepos;

    @Autowired
    Telephonerepository telephonerepos;

    @Autowired
    Zoorepository zoorepos;

//    @Autowired
//    Zooanimalsrepository zooanimalsrepos;

    @PutMapping("/zoos/{id}")
    public Zoo updateZooById(@RequestBody Zoo updatedZoo, @PathVariable long id) throws URISyntaxException
    {
        Optional<Zoo> foundZooById = zoorepos.findById(id);
        if (foundZooById.isPresent())
        {
            if (updatedZoo.getZooname() == null) updatedZoo.setZooname(foundZooById.get().getZooname());
            if (updatedZoo.getAnimals() == null) updatedZoo.setAnimals(foundZooById.get().getAnimals());
            if (updatedZoo.getTelephones() == null) updatedZoo.setTelephones((foundZooById.get().getTelephones()));
            updatedZoo.setZooid(id);
            return zoorepos.save(updatedZoo);
        } else
        {
            return null;
        }
    }

    @PutMapping("/phones/{id}")
    public Telephone updateTelephoneById(@RequestBody Telephone updatedTelephone, @PathVariable long id) throws URISyntaxException
    {
        Optional<Telephone> foundTelephoneById = telephonerepos.findById(id);
        if (foundTelephoneById.isPresent())
        {
            if (updatedTelephone.getPhonenumber() == null)
                updatedTelephone.setPhonenumber(foundTelephoneById.get().getPhonenumber());
            if (updatedTelephone.getPhonetype() == null)
                updatedTelephone.setPhonetype(foundTelephoneById.get().getPhonetype());
            if (updatedTelephone.getZoo() == null) updatedTelephone.setZoo(foundTelephoneById.get().getZoo());
            updatedTelephone.setPhoneid(id);
            return telephonerepos.save(updatedTelephone);
        } else
        {
            return null;
        }
    }

    @PutMapping("/animals/{id}")
    public Animal updateAnimalById(@RequestBody Animal updatedAnimal, @PathVariable long id) throws URISyntaxException
    {
        Optional<Animal> foundAnimalById = animalrepos.findById(id);
        if (foundAnimalById.isPresent())
        {
            if (updatedAnimal.getAnimaltype() == null)
                updatedAnimal.setAnimaltype(foundAnimalById.get().getAnimaltype());
            if (updatedAnimal.getZoos() == null) updatedAnimal.setZoos(foundAnimalById.get().getZoos());
            updatedAnimal.setAnimalid(id);
            return animalrepos.save(updatedAnimal);
        } else
        {
            return null;
        }
    }

    // ========================================================================================

    @PostMapping("/zoos")
    public Zoo addNewZoo(@RequestBody Zoo newZoo) throws URISyntaxException
    {
        return zoorepos.save(newZoo);
    }

    @PostMapping("/phones")
    public Telephone addNewTelephone(@RequestBody Telephone newTelephone) throws URISyntaxException
    {
        return telephonerepos.save(newTelephone);
    }

    @PostMapping("/animals")
    public Animal addNewAnimal(@RequestBody Animal newAnimal) throws URISyntaxException
    {
        return animalrepos.save(newAnimal);
    }

//    @PostMapping("/zoos/animals")
//    public Zooanimals addAnimalToZoo(@RequestBody Zooanimals newZooAnimalRelation)
//    {
//        return zooanimalsrepos.save(newZooAnimalRelation);
//    }

    // Holly
    @PostMapping(value = "/zoos/animals")
    public ObjectNode addAnimalToZoo(@RequestBody ObjectNode zooanimal)
    {
        long zooid = zooanimal.get("zooid").asLong();
        long animalid = zooanimal.get("animalid").asLong();

        Optional<Zoo> foundZoo = zoorepos.findById(zooid);
        Optional<Animal> foundAnimal = animalrepos.findById(animalid);

        if (foundZoo.isPresent() && foundAnimal.isPresent())
        {
            Zoo zoo = foundZoo.get();
            Set<Animal> animals = zoo.getAnimals();
            animals.add(foundAnimal.get());
            zoo.setAnimals(animals);
            zoorepos.save(zoo);
            return zooanimal;
        }
        else
        {
            return null;
        }
    }

    // ========================================================================================

    @DeleteMapping("/zoos/{id}")
    public Zoo deleteZooById(@PathVariable long id)
    {
        Optional<Zoo> foundZooByid = zoorepos.findById(id);
        if (foundZooByid.isPresent())
        {
            zoorepos.deleteById(id);
            return foundZooByid.get();
        } else
        {
            return null;
        }
    }

    @DeleteMapping("/phones/{id}")
    public Telephone deleteTelephoneById(@PathVariable long id)
    {
        Optional<Telephone> foundTelephoneById = telephonerepos.findById(id);
        if (foundTelephoneById.isPresent())
        {
            telephonerepos.deleteById(id);
            return foundTelephoneById.get();
        }
        else
        {
            return null;
        }
    }

    @DeleteMapping("/animals/{id}")
    public Animal deleteAnimalById(@PathVariable long id)
    {
        Optional<Animal> foundAnimalById = animalrepos.findById(id);
        if (foundAnimalById.isPresent())
        {
            animalrepos.deleteById(id);
            return foundAnimalById.get();
        }
        else
        {
            return null;
        }
    }

    // Works but is kinda hackish
//    @DeleteMapping("/zoos/{zooid}/animals/{animalid}")
//    public Zoo deleteAnimalFromZoo(@PathVariable long zooid, @PathVariable long animalid)
//    {
//        Optional<Zoo> foundZoo = zoorepos.findById(zooid);
//        Optional<Animal> foundAnimal = animalrepos.findById(animalid);
//
//        Set<Animal> ZooAnimals = foundZoo.get().getAnimals();
//        if (ZooAnimals.contains(foundAnimal.get()))
//        {
//            ZooAnimals.remove(foundAnimal.get());
//            foundZoo.get().setAnimals(ZooAnimals);
//            zoorepos.save(foundZoo.get());
//            return foundZoo.get();
//        }
//        else
//        {
//            return null;
//        }
//    }

    @DeleteMapping("/zoos/{zooid}/animals/{animalid}")
    public Optional<Zoo> deleteAnimalFromZoo(@PathVariable long zooid, @PathVariable long animalid)
    {
        Optional<Zoo> foundZoo = zoorepos.findById(zooid);

        if (foundZoo.isPresent())
        {
            zoorepos.deleteAnimalFromZoo(zooid, animalid);
            return zoorepos.findById(zooid);
        }
        else
        {
            return null;
        }
    }

}
