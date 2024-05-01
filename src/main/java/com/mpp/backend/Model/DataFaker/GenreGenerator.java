package com.mpp.backend.Model.DataFaker;

import com.github.javafaker.Faker;
import com.mpp.backend.Model.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GenreGenerator {
    public List<Genre> generateGenres(int n){
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Genre genre = new Genre();
            genre.setName("Genre " + i);
            genre.setTypicalTraits("Typical Traits " + i);
            genre.setNumberOfCharacters(0);
            genre.setDescription("Description " + i);
            genres.add(genre);
        }
        return genres;
    }

    public Genre generateGenre(){
        Faker faker = new Faker();
        Genre genre = new Genre();
        genre.setName(faker.book().genre() + " " + faker.number().numberBetween(1, 100));
        genre.setTypicalTraits("Typical Traits");
        genre.setNumberOfCharacters(0);
        genre.setDescription("Description");
        return genre;
    }
}
