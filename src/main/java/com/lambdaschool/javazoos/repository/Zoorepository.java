package com.lambdaschool.javazoos.repository;

import com.lambdaschool.javazoos.model.Zoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface Zoorepository extends JpaRepository<Zoo, Long>
{
    public List<Zoo> findZooByZoonameLike(String name);

    public List<Zoo> findZooByZoonameContains(String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM zooanimals WHERE zooid = :zooid AND animalid = :animalid", nativeQuery = true)
    public void deleteAnimalFromZoo(long zooid, long animalid);
}
