package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.InvalidTitle;
import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.Television;
import nl.novi.TechItEasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static nl.novi.TechItEasy.controllers.Common.constructURI;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionRepository repos;


    public TelevisionController(TelevisionRepository repos) {
        this.repos = repos;
    }

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {
        List<Television> televisions;
        if (brand == null) {
            televisions = repos.findAll();
        } else {
            televisions = repos.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        }
        return ResponseEntity.ok().body(televisions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") long id) {
        Optional<Television> op = repos.findById(id);
        if (op.isPresent()) {
            return ResponseEntity.ok(op.get());
        } else {
            throw new RecordNotFoundException("Televisie niet gevonden met id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addTelevision(@RequestBody Television television) {
        if (television.getName().length() > 20) {
            throw new InvalidTitle("Name must contain at most 20 characters");
        }
        Television returnTelevision = this.repos.save(television);
        return ResponseEntity.created(constructURI(television.getId())).body(returnTelevision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTelevision(@PathVariable long id, @RequestBody Television television) {

        Optional<Television> op = repos.findById(id);
        if (op.isPresent()) {
            Television television1 = op.get();
            television1.setAmbiLight(television.getAmbiLight());
            television1.setAvailableSize(television.getAvailableSize());
            television1.setAmbiLight(television.getAmbiLight());
            television1.setBluetooth(television.getBluetooth());
            television1.setBrand(television.getBrand());
            television1.setHdr(television.getHdr());
            television1.setName(television.getName());
            television1.setOriginalStock(television.getOriginalStock());
            television1.setPrice(television.getPrice());
            television1.setRefreshRate(television.getRefreshRate());
            television1.setScreenQuality(television.getScreenQuality());
            television1.setScreenType(television.getScreenType());
            television1.setSmartTv(television.getSmartTv());
            television1.setSold(television.getSold());
            television1.setType(television.getType());
            television1.setVoiceControl(television.getVoiceControl());
            television1.setWifi(television.getWifi());

            Television returnTelevision = repos.save(television1);
            return ResponseEntity.ok().body(returnTelevision);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") long id) {

        Optional<Television> op = repos.findById(id);
        if (op.isPresent()) {
            this.repos.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Television> updatePartialTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = repos.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {
            Television television1 = television.get();
            if (newTelevision.getAmbiLight() != null) {
                television1.setAmbiLight(newTelevision.getAmbiLight());
            }
            if (newTelevision.getAvailableSize() != null) {
                television1.setAvailableSize(newTelevision.getAvailableSize());
            }
            if (newTelevision.getBluetooth()) {
                television1.setBluetooth(newTelevision.getBluetooth());
            }
            if (newTelevision.getBrand() != null) {
                television1.setBrand(newTelevision.getBrand());
            }
            if (newTelevision.getHdr() != null) {
                television1.setHdr(newTelevision.getHdr());
            }
            if (newTelevision.getName() != null) {
                television1.setName(newTelevision.getName());
            }
            if (newTelevision.getOriginalStock() != null) {
                television1.setOriginalStock(newTelevision.getOriginalStock());
            }
            if (newTelevision.getPrice() != null) {
                television1.setPrice(newTelevision.getPrice());
            }
            if (newTelevision.getRefreshRate() != null) {
                television1.setRefreshRate(newTelevision.getRefreshRate());
            }
            if (newTelevision.getScreenQuality() != null) {
                television1.setScreenQuality(newTelevision.getScreenQuality());
            }
            if (newTelevision.getScreenType() != null) {
                television1.setScreenType(newTelevision.getScreenType());
            }
            if (newTelevision.getSmartTv() != null) {
                television1.setSmartTv(newTelevision.getSmartTv());
            }
            if (newTelevision.getSold() != null) {
                television1.setSold(newTelevision.getSold());
            }
            if (newTelevision.getType() != null) {
                television1.setType(newTelevision.getType());
            }
            if (newTelevision.getVoiceControl() != null) {
                television1.setVoiceControl(newTelevision.getVoiceControl());
            }
            if (newTelevision.getWifi() != null) {
                television1.setWifi(newTelevision.getWifi());
            }

            Television returnTelevision = repos.save(television1);
            return ResponseEntity.ok().body(returnTelevision);
        }
    }

}

