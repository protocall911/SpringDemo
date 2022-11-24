package com.example.demo.repository;

import com.example.demo.models.Star;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StarRepository extends CrudRepository<Star,Long> {

    public List<Star> findByName(String name);

    public List<Star> findByNameContains(String name);

//    public List<Star> findByClass_star(String class_star);

}
