package br.ufrn.imd.PotyCine.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//Controller de teste, apagar depois
@RestController
public class HelloController {

    @GetMapping("/users/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Olá, mundo");
    }

    @GetMapping("/users/test/user")
    public ResponseEntity<String> testUser(){
        return ResponseEntity.ok("Olá, user");
    }

    @GetMapping("/users/test/admin")
    public ResponseEntity<String> testAdmin(){
        return ResponseEntity.ok("Olá, admin");
    }

    @GetMapping("/users/test/producer")
    public ResponseEntity<String> testProducer(){
        return ResponseEntity.ok("Olá, producer");
    }
}
