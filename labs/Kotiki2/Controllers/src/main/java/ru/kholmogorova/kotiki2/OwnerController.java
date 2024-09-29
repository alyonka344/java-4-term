package ru.kholmogorova.kotiki2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.DTO.OwnerDTO;
import ru.kholmogorova.kotiki2.Entity.CatEntity;
import ru.kholmogorova.kotiki2.Entity.OwnerEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OwnerDTO>> GetAllCats() {
        List<OwnerDTO> owners = new ArrayList<>();
        ownerService.getAll().forEach((o) -> owners.add(new OwnerDTO(o)));
        return owners.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @GetMapping("/Id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OwnerDTO> GetCatById(@PathVariable Long id) {
        OwnerEntity owner = ownerService.getById(id);
        return owner == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(new OwnerDTO(owner), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> DeleteCat(@RequestBody OwnerDTO ownerDTO) {
        try {
            ownerService.delete(new OwnerEntity(ownerDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> PutCat(@RequestBody OwnerDTO ownerDTO) {
        try {
            ownerService.save(new OwnerEntity(ownerDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> PostCat(@RequestBody OwnerDTO ownerDTO) {
        try {
            ownerService.save(new OwnerEntity(ownerDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
