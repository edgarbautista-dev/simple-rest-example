package com.bautista.simplerestexample.controller;

import com.bautista.simplerestexample.exception.CharacterNotFound;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/characters")
@RestController
public class CharacterController {
    private Faker faker=new Faker();
    private List<String> characters= new ArrayList<>();
    @PostConstruct
    public void init(){
        for(int i=0;i<20;i++){
            characters.add(faker.dragonBall().character());
        }

    }

    //@RequestMapping(value="/dragonBall", method = RequestMethod.GET)
    @GetMapping("/dragonBall")
    public List<String> getCharacters(){
        return characters;
    }

    @GetMapping(value = "/dragonBall/{name}")
    public String getCharacterByName(@PathVariable("name")String name){
        return characters.stream()
                .filter(c->c.equals(name))
                .findAny()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("%s not found", null)));
    }

    @GetMapping("/dragonBall/search")
    public List<String> getCharactersByPrefix(@RequestParam("prefix") String prefix){
          List<String> result= characters.stream().filter(c->c.startsWith(prefix)).collect(Collectors.toList());
          if(result.isEmpty()){
            throw new CharacterNotFound();
          }
          return result;
    }

}
