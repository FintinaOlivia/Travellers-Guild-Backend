package com.mpp.backend.Tests;


import com.mpp.backend.Controllers.CharacterController;
import com.mpp.backend.Model.Character;
import com.mpp.backend.Services.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CharacterControllerTests{
    @Mock
    private CharacterService characterService;

    @InjectMocks
    private CharacterController characterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCharacter() throws Exception {
        Character character = new Character();
        character.setCharacterName("TestCharacter");
        when(characterService.validateCharacter(any())).thenReturn(true);
        when(characterService.noDuplicateCharacters(any())).thenReturn(false);
        ResponseEntity<Character> response = characterController.createCharacter(character);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testCreateCharacter_InvalidCharacter() throws Exception {
        Character character = new Character();
        character.setCharacterName("TestCharacter");
        when(characterService.validateCharacter(any())).thenReturn(false);
        try{
            characterController.createCharacter(character);
        } catch (Exception e) {
            assertEquals("Invalid Operation!", e.getMessage());
        }
    }

    @Test
    void testGetAllCharacters() {
        List<Character> characters = new ArrayList<>();
        when(characterService.getCharacters()).thenReturn(characters);
        ResponseEntity<List<Character>> response = characterController.getAllCharacters();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(characters, response.getBody());
    }

    @Test
    void testGetCharacterById_ExistingId() {
        Long existingId = 1L;
        Character mockCharacter = new Character();
        mockCharacter.setId(existingId);
        when(characterService.getCharacters()).thenReturn(List.of(mockCharacter));

        ResponseEntity<Character> response = characterController.getCharacterById(existingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCharacter, response.getBody());
    }

    @Test
    void testGetCharacterById_NonExistingId() {
        Long nonExistingId = 999L;
        when(characterService.getCharacters()).thenReturn(Collections.emptyList());

        ResponseEntity<Character> response = characterController.getCharacterById(nonExistingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateCharacter_ExistingId() throws Exception {
        Long existingId = 1L;
        Character existingCharacter = new Character();
        existingCharacter.setId(existingId);
        when(characterService.getCharacters()).thenReturn(List.of(existingCharacter));

        Character updatedCharacter = new Character();
        updatedCharacter.setId(existingId);
        updatedCharacter.setCharacterName("Updated Name");

        ResponseEntity<Character> response = characterController.updateCharacter(existingId, updatedCharacter);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedCharacter.getCharacterName(), response.getBody().getCharacterName());
        verify(characterService).getCharacters();
    }

    @Test
    void testUpdateCharacter_NonExistingId() throws Exception {
        Long nonExistingId = 1000L;
        when(characterService.getCharacters()).thenReturn(Collections.emptyList());

        ResponseEntity<Character> response = characterController.updateCharacter(nonExistingId, new Character());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(characterService).getCharacters();
    }

    @Test
    void testDeleteCharacter_ExistingId() {
        Long existingId = 1L;
        Character existingCharacter = new Character();
        existingCharacter.setId(existingId);
        when(characterService.getCharacters()).thenReturn(List.of(existingCharacter));

        ResponseEntity<Void> response = characterController.deleteCharacter(existingId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(characterService).removeCharacter(existingCharacter);
    }

    @Test
    void testDeleteCharacter_NonExistingId() {
        Long nonExistingId = 999L;
        when(characterService.getCharacters()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> response = characterController.deleteCharacter(nonExistingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(characterService, never()).removeCharacter(any());
    }
}

