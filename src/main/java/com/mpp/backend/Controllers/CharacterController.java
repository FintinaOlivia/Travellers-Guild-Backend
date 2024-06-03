package com.mpp.backend.Controllers;
import com.mpp.backend.Model.Character;
import com.mpp.backend.Repository.CharacterRepository;
import com.mpp.backend.Services.CharacterService;
import com.mpp.backend.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @Autowired
    GenreService genreService;

    @CrossOrigin(origins = "http://localhost:3000/characters")
    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) throws Exception {
        try{
                Character newCharacter = new Character();

                newCharacter.setCharacterName(character.getCharacterName());
                newCharacter.setAge(character.getAge());
                newCharacter.setCreator(character.getCreator());
                newCharacter.setIconicLines(character.getIconicLines());
                newCharacter.setGenreID(character.getGenreID());
                newCharacter.setGenre(character.getGenre());
                newCharacter.setDescription(character.getDescription());
                newCharacter.setAdderUsername(character.getAdderUsername());


                characterService.addCharacter(newCharacter);
                return new ResponseEntity<>(character, HttpStatus.CREATED);

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        try {
            Page<Character> charactersPage = characterService.getCharacters(page, pageSize);
            List<Character> characters = charactersPage.getContent();

            return new ResponseEntity<>(characters, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character Character = characterService.findCharacterById(id);
        if (Character != null) {
            return new ResponseEntity<>(Character, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Character>> getCharactersByUser(@PathVariable String username) {
        List<Character> characters = characterService.getCharactersByUser(username);
        if (characters != null) {
            return new ResponseEntity<>(characters, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Character>> getCharacterByName(@PathVariable String name) {
        List<Character> characters = characterService.getCharactersByName(name);
        if (characters != null) {
            return new ResponseEntity<>(characters, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id,
                                                     @RequestBody Character updatedCharacter) throws Exception {
        try {
            Character character = characterService.findCharacterById(id);
            characterService.validateCharacter(character);
            if (character != null) {
                character.setCharacterName(updatedCharacter.getCharacterName());
                character.setAge(updatedCharacter.getAge());
                character.setCreator(updatedCharacter.getCreator());
                character.setIconicLines(updatedCharacter.getIconicLines());
                character.setGenreID(updatedCharacter.getGenreID());
                character.setDescription(updatedCharacter.getDescription());

                characterService.addCharacter(character);
                return new ResponseEntity<>(character, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new Exception("ID already in use!");
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        Character character = characterService.findCharacterById(id);
        if (character != null) {
            characterService.removeCharacter(character);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
