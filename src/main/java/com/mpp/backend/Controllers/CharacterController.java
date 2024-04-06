package com.mpp.backend.Controllers;
import com.mpp.backend.Model.Character;
import com.mpp.backend.Repository.CharacterRepository;
import com.mpp.backend.Services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import static com.mpp.backend.Repository.CharacterRepository.characters;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    // Endpoint to create a new Character
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) throws Exception {
        try{
            characterService.assignID(character);
            if(characterService.validateCharacter(character) &&
                    !characterService.noDuplicateCharacters(character)){
                characterService.addCharacter(character);
                return new ResponseEntity<>(character, HttpStatus.CREATED);
            } else {
                throw new Exception("Invalid Operation!");
            }

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    // Endpoint to retrieve all Characters
    @GetMapping
    public ResponseEntity<List<Character>> getAllCharacters() {
        return new ResponseEntity<>(characterService.getCharacters(), HttpStatus.OK);
    }

    // Endpoint to retrieve a Character by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        Character Character = findCharacterById(id);
        if (Character != null) {
            return new ResponseEntity<>(Character, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to update a Character by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id,
                                                     @RequestBody Character updatedCharacter) throws Exception {
        try {
            Character character = findCharacterById(id);
            characterService.validateCharacter(character);
            if (character != null) {
                character.setCharacterName(updatedCharacter.getCharacterName());
                character.setAge(updatedCharacter.getAge());
                character.setCreator(updatedCharacter.getCreator());
                character.setIconicLines(updatedCharacter.getIconicLines());
                character.setDescription(updatedCharacter.getDescription());
                return new ResponseEntity<>(character, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            throw new Exception("ID already in use!");
        }
    }

    // Endpoint to delete a Character by ID
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        Character character = findCharacterById(id);
        if (character != null) {
            characterService.removeCharacter(character);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Helper method to find a Character by ID
    private Character findCharacterById(Long id) {
        return characterService.getCharacters().stream()
                .filter(Character -> Character.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
