package com.system.springboot.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Status")
public class StatusController {
    @GetMapping
    public ResponseEntity<Void> getStatus(){
        return ResponseEntity.ok().build();
    }
}
