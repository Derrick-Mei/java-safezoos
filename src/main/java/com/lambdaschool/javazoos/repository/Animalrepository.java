package com.lambdaschool.javazoos.repository;

import com.lambdaschool.javazoos.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Animalrepository extends JpaRepository<Animal, Long>
{
    public Animal findAnimalByAnimaltypeContains(String type);
}
