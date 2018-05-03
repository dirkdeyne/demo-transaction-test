package com.example.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication  implements CommandLineRunner{
  
  @Autowired
  PersonRepository personRepo;
  
  @Override
  public void run(String... args) throws Exception {
   
   Stream.of("Deyne,Dirk,deynedirk","Devos,Eric,fastric","Mahsum,Demir,mahsumdemir","Long,Josh,starbuxman","Gierke,Oliver,spring-oliver")
           .map(s -> s.split(","))
           .map(a -> new Person(a[0], a[1],a[2]))
           .forEach(personRepo::save);

    Person dirk = personRepo.findById(1L).get();
    dirk.setFriends(new HashSet<>(Arrays.asList(personRepo.findById(3L).get(),personRepo.findById(4L).get(),personRepo.findById(5L).get())));
    personRepo.save(dirk);

    Person demir = personRepo.findById(3L).get();
    demir.setFriends(new HashSet<>(Arrays.asList(personRepo.findById(1L).get(),personRepo.findById(2L).get(),personRepo.findById(5L).get())));
    
    personRepo.save(demir);
    
  }
  
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
