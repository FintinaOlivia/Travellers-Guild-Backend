package com.mpp.backend.Tests;

import com.mpp.backend.Model.Character;
import com.mpp.backend.Model.Genre;
import com.mpp.backend.Model.UserEntity;
import com.mpp.backend.Repository.CharacterRepository;
import com.mpp.backend.Services.CharacterService;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharacterServiceTests {
    @Mock
    private CharacterRepository characterRepository;

    @InjectMocks
    private CharacterService characterService;

    private List<Character> characters;

    private Genre genre;

    private UserEntity user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        characters = new ArrayList<>();
        user = new UserEntity();
        user.setUsername("User1");
        genre = new Genre(1L, new ArrayList<>(),"Genre1", "Description1", 0, "Creator1","User1");
        characters.add(new Character(1L, "Character1", 20, "IconicLines1", "Creator1", 1L, genre, "Description1","User1"));
        characters.add(new Character(2L, "Character2", 25, "IconicLinies2","Creator2", 1L, genre,"Description2","User1"));
//        characterService.initializeCharacters();

    }

    @Test
    void testRemoveCharacter() {
        Character characterToRemove = new Character();
        characterToRemove.setId(1L); // Set some ID for identification

        // Call the method under test
        characterService.removeCharacter(characterToRemove);

        // Verify that the mock's removeCharacter method was called with the characterToRemove parameter
        verify(characterRepository).delete(characterToRemove);
    }

    @Test
    void testNoDuplicateCharacters_ExistingCharacter() {
        Character existingCharacter = characters.get(0);

        boolean hasDuplicates = characterService.noDuplicateCharacters(existingCharacter);

        assertFalse(hasDuplicates);
    }

    @Test
    void testNoDuplicateCharacters_NonExistingCharacter() {
        Character nonExistingCharacter = new Character(3L, "Non Existing Character", 30, "New Iconic Lines", "New Creator",1L, genre, "New Description","User1");

        boolean hasDuplicates = characterService.noDuplicateCharacters(nonExistingCharacter);

        assertFalse(hasDuplicates);
    }

    @Test
    void testValidateCharacter_ValidCharacter() {
        Character validCharacter = new Character(3L, "New Character", 30, "New Iconic Lines", "New Creator", 1L, genre, "New Description","User1");

        boolean isValid = characterService.validateCharacter(validCharacter);

        assertTrue(isValid);
    }

    @Test
    void testValidateCharacter_InvalidCharacter() {
        // Character with null fields
        Character invalidCharacter = new Character();

        boolean isValid = characterService.validateCharacter(invalidCharacter);

        assertFalse(isValid);
    }

}

