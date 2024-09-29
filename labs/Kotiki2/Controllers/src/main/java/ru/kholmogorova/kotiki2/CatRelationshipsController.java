package ru.kholmogorova.kotiki2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.DTO.CatRelationshipsDTO;
import ru.kholmogorova.kotiki2.Entity.CatEntity;
import ru.kholmogorova.kotiki2.Entity.CatRelationshipsEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class CatRelationshipsController {
    CatRelationshipsService catRelationshipsService;

    @Autowired
    public CatRelationshipsController(CatRelationshipsService catRelationshipsService) {
        this.catRelationshipsService = catRelationshipsService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CatRelationshipsDTO>> GetAllCats() {
        List<CatRelationshipsDTO> relationships = new ArrayList<>();
        catRelationshipsService.getAll().forEach((r) -> relationships.add(new CatRelationshipsDTO(r)));
        return relationships.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(relationships, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> DeleteCat(@RequestBody CatRelationshipsDTO catRelationshipsDTO) {
        try {
            catRelationshipsService.delete(new CatRelationshipsEntity(catRelationshipsDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put")
    public ResponseEntity<Boolean> PutCat(@RequestBody CatRelationshipsDTO catRelationshipsDTO) {
        try {
            catRelationshipsService.save(new CatRelationshipsEntity(catRelationshipsDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    public ResponseEntity<Boolean> PostCat(@RequestBody CatRelationshipsDTO catRelationshipsDTO) {
        try {
            catRelationshipsService.save(new CatRelationshipsEntity(catRelationshipsDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }    }
}
