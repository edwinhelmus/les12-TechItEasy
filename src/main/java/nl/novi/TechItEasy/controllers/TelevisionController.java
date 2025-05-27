package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
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
        if (id != 0) {
            return ResponseEntity.ok("Television ID: " + id);
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }

    @PostMapping("/television")
    public ResponseEntity<Object> addTelevision(@RequestBody String title) {
        return ResponseEntity.created(null).body("Television: "+ title);
    }

    @PutMapping("/television/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable long id, @RequestBody String title) {
        if (id != 0) {
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }

    @DeleteMapping("/television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable long id) {
        if (id != 0) {
            return ResponseEntity.noContent().build();
        } else {
            throw new RecordNotFoundException("ID not found");
        }
    }
}

