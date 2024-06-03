package com.mpp.backend.Controllers;

import com.mpp.backend.Model.Character;
import com.mpp.backend.Model.Genre;
import com.mpp.backend.Repository.CharacterRepository;
import com.mpp.backend.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    GenreService genreService;


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        try {
            genreService.addGenre(genre);
            return new ResponseEntity<>(genre, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenres(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        try {
            Page<Genre> genresPage = genreService.getGenres(page, pageSize);
            List<Genre> genres = genresPage.getContent();

            for (Genre genre : genres) {
                genre.setNumberOfCharacters(genreService.countByGenreID(genre.getGenreID()));
                genreService.addGenre(genre);
            }


            return new ResponseEntity<>(genres, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
        Genre genre = genreService.getGenreByID(id);
        if (genre != null) {
            genre.setNumberOfCharacters(genreService.countByGenreID(genre.getGenreID()));
            genreService.addGenre(genre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<String>> getGenreByName(@PathVariable String name) {
        List<String> genres = genreService.findGenresByName(name);

        if (!genres.isEmpty()) {
            return new ResponseEntity<>(genres, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Genre>> getGenresByUser(@PathVariable String username) {
        List<Genre> genres = genreService.getGenresByUsername(username);
        if (genres != null) {
            return new ResponseEntity<>(genres, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/count/{id}")
    public ResponseEntity<Map<String, Integer>> getNumberOfCharacters(@PathVariable Long id) {
        Genre genre = genreService.getGenreByID(id);
        if (genre != null) {
            Map<String, Integer> response = new HashMap<>();
            response.put("numberOfCharacters", genreService.countByGenreID(id));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id,
                                             @RequestBody Genre updatedGenre) {
        Genre genre = genreService.getGenreByID(id);
        if (genre != null) {
            genre.setName(updatedGenre.getName());
            genre.setTypicalTraits(updatedGenre.getTypicalTraits());
            genre.setDescription(updatedGenre.getDescription());
            genreService.addGenre(genre);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        Genre genre = genreService.getGenreByID(id);
        if (genre != null) {
            genreService.removeGenre(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
