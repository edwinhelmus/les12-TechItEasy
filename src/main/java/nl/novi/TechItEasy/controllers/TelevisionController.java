package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;

@RestController
public class TelevisionController {

    ArrayList<String> televisionDataBase = new ArrayList<>();

    @GetMapping("/televisions")
    public ResponseEntity<Object> televisions() {
        return ResponseEntity.ok(televisionDataBase.toString());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> televisions(@PathVariable int id) {
        if (televisionDataBase.get(id) != null) {
            return ResponseEntity.ok(televisionDataBase.get(id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/television")
    public ResponseEntity<Object> addTelevision(@RequestBody String title) {
        televisionDataBase.add(title);
        return ResponseEntity.created(null).body("Television id: " + (televisionDataBase.size() - 1));
    }

    @PutMapping("/television/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String title) {
        televisionDataBase.set(id, title);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        televisionDataBase.set(id, null);
        return ResponseEntity.noContent().build();
    }
}

