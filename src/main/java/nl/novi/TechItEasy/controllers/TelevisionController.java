package nl.novi.TechItEasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class TelevisionController {

    @GetMapping("/televisions")
    public ResponseEntity<Object> televisions() {
        return ResponseEntity.ok("televisions");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> televisions(@PathVariable long id) {
        return ResponseEntity.ok("Television ID: " + id);
    }

    @PostMapping("/television")
    public ResponseEntity<Object> addTelevision(@RequestBody String title) {
        return ResponseEntity.created(null).body("Television: "+ title);
    }

    @PutMapping("/television/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable long id, @RequestBody String title) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable long id) {
        return ResponseEntity.noContent().build();
    }
}

