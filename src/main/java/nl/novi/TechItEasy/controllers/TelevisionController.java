package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.InvalidTitle;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static nl.novi.TechItEasy.controllers.Common.constructURI;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionRepository repos;

    public TelevisionController(TelevisionRepository repos  ) {
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<Object> televisions() {
        return ResponseEntity.ok(repos.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> televisions(@PathVariable long id) {
        Optional<Television> op = repos.findById(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> addTelevision(@RequestBody Television television) {
        if (television.getName().length() > 20) {
            throw new InvalidTitle("Name must contain at most 20 characters");
        }
        this.repos.save(television);
        return ResponseEntity.created(constructURI(television.getId())).body(television);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateTelevision(@PathVariable int id, @RequestBody String title) {
//        try {
//            televisionDataBase.set(--id, title);
//        }
//        catch (IndexOutOfBoundsException e) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTelevision(@PathVariable int id) {
//        try {
//            televisionDataBase.set(--id, null);
//        }
//        catch (IndexOutOfBoundsException e) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.noContent().build();
//    }
}

