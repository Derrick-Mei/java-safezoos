package com.lambdaschool.javazoos.controller;//package com.lambdaschool.javazoos.controller;
//
//import com.lambdaschool.javazoos.model.Zooanimals;
//import com.lambdaschool.javazoos.repository.Zooanimalsrepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/zooanimals")
//public class Zooanimalscontroller
//{
//    @Autowired
//    Zooanimalsrepository zooanimalsrepos;
//
//    @GetMapping("")
//    public List<Zooanimals> getAllZooAnimals()
//    {
//        return zooanimalsrepos.findAll();
//    }
//}
