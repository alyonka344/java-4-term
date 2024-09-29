package ru.kholmogorova.kotiki2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kholmogorova.kotiki2.DTO.CatDTO;
import ru.kholmogorova.kotiki2.DTO.OwnerDTO;
import ru.kholmogorova.kotiki2.Entity.CatEntity;
import ru.kholmogorova.kotiki2.Entity.OwnerEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cats")
public class CatController {
    CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CatDTO>> GetAllCats() {
        List<CatDTO> cats = new ArrayList<>();
        catService.getAll().forEach((c) -> cats.add(new CatDTO(c)));
        return cats.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CatDTO>> GetUserCats(Authentication authentication) {
//        List<CatDTO> cats = new ArrayList<>();
//        List<CatEntity> catEntities = catService.getAll();
//        catEntities.removeIf(n -> n.getOwner().getUsername().equals(authentication.getName()));
//        catEntities.forEach((c) -> cats.add(new CatDTO(c)));
//
//
//        return cats.isEmpty()
//                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
//                : new ResponseEntity<>(cats, HttpStatus.OK);
        List<CatDTO> cats = new ArrayList<>();
        catService.getAll().forEach((c) -> cats.add(new CatDTO(c)));
        return cats.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CatDTO> GetCatById(@PathVariable("id") Long id) {
        CatEntity cat = catService.getById(id);
        return cat == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(new CatDTO(cat), HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Boolean> DeleteCat(@RequestBody CatDTO catDTO) {
        try {
            catService.delete(new CatEntity(catDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/put")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<Boolean> PutCat(@RequestBody CatDTO catDTO) {
        try {
            catService.save(new CatEntity(catDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> PostCat(@RequestBody CatDTO catDTO) {
        try {
            catService.save(new CatEntity(catDTO));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
