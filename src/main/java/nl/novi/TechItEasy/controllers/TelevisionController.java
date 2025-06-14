package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.InvalidTitle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
public class TelevisionController {

    private List<String> televisionDataBase;

    public TelevisionController() {
        televisionDataBase = new ArrayList<>();
    }

    @GetMapping("/televisions")
    public ResponseEntity<Object> televisions() {
        return ResponseEntity.ok(televisionDataBase.toString());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Object> televisions(@PathVariable int id) {
        String television;
        try {
            television = televisionDataBase.get(--id);
        }
        catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }

        if (television != null) {
            return ResponseEntity.ok(television);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/television")
    public ResponseEntity<Object> addTelevision(@RequestBody String title) {

        if (title.length() > 20) {
            throw new InvalidTitle("Title must contain at most 20 characters");
        }
        televisionDataBase.add(title);


        return ResponseEntity.created(null).body("Television id: " + (televisionDataBase.size()));
    }

    @PutMapping("/television/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String title) {
        try {
            televisionDataBase.set(--id, title);
        }
        catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/television/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
        try {
            televisionDataBase.set(--id, null);
        }
        catch (IndexOutOfBoundsException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}

