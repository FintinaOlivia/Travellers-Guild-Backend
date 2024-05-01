package com.mpp.backend.Tests;

import com.mpp.backend.Controllers.GenreController;
import com.mpp.backend.Model.Genre;
import com.mpp.backend.Services.GenreService;
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

public class GenreControllerTests {
    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateGenre() {
        Genre genre = new Genre();
        doNothing().when(genreService).addGenre(genre);

        ResponseEntity<Genre> response = genreController.createGenre(genre);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(genre, response.getBody());
    }

    @Test
    void testCreateGenre_InternalServerError() {
        Genre genre = new Genre();
        doThrow(new RuntimeException()).when(genreService).addGenre(genre);

        ResponseEntity<Genre> response = genreController.createGenre(genre);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(genreService, times(1)).addGenre(genre);
    }

    @Test
    void testGetAllGenres() {
        List<Genre> genres = new ArrayList<>();
        when(genreService.getGenres()).thenReturn(genres);

        ResponseEntity<List<Genre>> response = genreController.getAllGenres();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(genres, response.getBody());
    }

    @Test
    void testGetGenreById_ExistingId() {
        Long existingId = 1L;
        Genre mockGenre = new Genre();
        mockGenre.setGenreID(existingId);
        when(genreService.getGenreByID(existingId)).thenReturn(mockGenre);

        ResponseEntity<Genre> response = genreController.getGenreById(existingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockGenre, response.getBody());
    }

    @Test
    void testGetGenreById_NonExistingId() {
        Long nonExistingId = 999L;
        when(genreService.getGenreByID(nonExistingId)).thenReturn(null);

        ResponseEntity<Genre> response = genreController.getGenreById(nonExistingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testUpdateGenre_ExistingId() {
        Long existingId = 1L;
        Genre existingGenre = new Genre();
        existingGenre.setGenreID(existingId);
        when(genreService.getGenreByID(existingId)).thenReturn(existingGenre);

        Genre updatedGenre = new Genre();
        updatedGenre.setGenreID(existingId);
        updatedGenre.setName("Updated Name");

        ResponseEntity<Genre> response = genreController.updateGenre(existingId, updatedGenre);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedGenre.getName(), response.getBody().getName());
        verify(genreService).addGenre(existingGenre);
    }

    @Test
    void testUpdateGenre_NonExistingId() {
        Long nonExistingId = 1000L;
        when(genreService.getGenreByID(nonExistingId)).thenReturn(null);

        ResponseEntity<Genre> response = genreController.updateGenre(nonExistingId, new Genre());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(genreService, never()).addGenre(any());
    }

    @Test
    void testDeleteGenre_ExistingId() {
        Long existingId = 1L;
        Genre existingGenre = new Genre();
        existingGenre.setGenreID(existingId);
        when(genreService.getGenreByID(existingId)).thenReturn(existingGenre);

        ResponseEntity<Void> response = genreController.deleteGenre(existingId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(genreService).removeGenre(existingId);
    }

    @Test
    void testDeleteGenre_NonExistingId() {
        Long nonExistingId = 999L;
        when(genreService.getGenreByID(nonExistingId)).thenReturn(null);

        ResponseEntity<Void> response = genreController.deleteGenre(nonExistingId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(genreService, never()).removeGenre(any());
    }
}
