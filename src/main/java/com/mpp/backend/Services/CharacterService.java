package com.mpp.backend.Services;

import com.mpp.backend.Model.Character;
import com.mpp.backend.Model.DataFaker.CharacterGenerator;
import com.mpp.backend.Repository.CharacterRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import static com.mpp.backend.Repository.CharacterRepository.characters;

@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterGenerator characterGenerator;

    private List<Character> characters;

    @PostConstruct
    public void initializeCharacters() {
        if (characters == null) {
            characters = characterRepository.getCharacters();
        }
        List<Character> generatedCharacters = characterGenerator.generateCharacters(70);
        for (Character character : generatedCharacters) {
            assignID(character);
            if (validateCharacter(character)) {
                addCharacter(character);
            }
        }

    }

    public List<Character> getCharacters(){
        return characterRepository.getCharacters();
    }


    public void addCharacter(Character character){
        characters.add(character);
    }

    public void removeCharacter(Character character){
        characterRepository.removeCharacter(character);
    }

    private static boolean noNullFields(Character character){
        return character.getCharacterName() != null &&
                character.getId() != null &&
                character.getAge() != null &&
                character.getCreator() != null &&
                character.getDescription() != null;
    }

    public boolean noDuplicateCharacters(Character toValidateCharacter) {
        // Check if any character in the list has the same name and creator as the given character
        return characters.stream()
                .anyMatch(character ->
                        character.getCharacterName().equals(toValidateCharacter.getCharacterName()) &&
                                character.getCreator().equals(toValidateCharacter.getCreator()) &&
                                !character.getId().equals(toValidateCharacter.getId())); // Exclude the character itself
    }

    private boolean isUnique(Long newId){
        return characters.stream()
                .noneMatch(character -> character.getId().equals(newId));

    }

    public boolean validateCharacter(Character character){
        return noNullFields(character) && (character.getAge() >= 0)
                && isUnique(character.getId());
    }

    public void assignID(Character character){
        character.setId(characters.getLast().getId() + 1);
    }
}
