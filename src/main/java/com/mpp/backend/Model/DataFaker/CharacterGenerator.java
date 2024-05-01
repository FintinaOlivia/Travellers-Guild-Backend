package com.mpp.backend.Model.DataFaker;

import com.github.javafaker.Faker;
import com.mpp.backend.Model.Character;
import com.mpp.backend.Model.Genre;
import com.mpp.backend.Repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterGenerator {
    private final Faker faker;
    private final List<String> authors = Arrays.asList(
            "J.K. Rowling", "Stephen King", "Agatha Christie", "George Orwell", "Jane Austen",
            "Charles Dickens", "Mark Twain", "Leo Tolstoy", "Ernest Hemingway", "F. Scott Fitzgerald",
            "Gabriel Garcia Marquez", "Harper Lee", "J.R.R. Tolkien", "William Shakespeare", "Toni Morrison",
            "Kurt Vonnegut", "Virginia Woolf", "John Steinbeck", "Franz Kafka", "Emily Dickinson",
            "Oscar Wilde", "Aldous Huxley", "Edgar Allan Poe", "H.G. Wells", "Nathaniel Hawthorne",
            "Chinua Achebe", "Milan Kundera", "Haruki Murakami", "Salman Rushdie", "Gabriel Garcia Lorca",
            "James Joyce", "Margaret Atwood", "Jack Kerouac", "Gabriel Garcia Lorca", "Herman Melville",
            "Agatha Christie", "Franz Kafka", "Stephen Hawking", "Arthur Conan Doyle", "Dan Brown",
            "Haruki Murakami", "Ken Follett", "Isabel Allende", "Mario Vargas Llosa", "Ernest Hemingway",
            "William Faulkner", "John Grisham", "Paulo Coelho", "Dan Brown", "Anne Rice"
    );

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    public CharacterGenerator(Faker faker) {
        this.faker = faker;
    }

    public List<Character> generateCharacters(int amount) {
        Random random = new Random();
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Character character = new Character();
            character.setCharacterName(faker.name().fullName());
            character.setAge(faker.number().numberBetween(1, 100));
            character.setIconicLines(faker.harryPotter().quote());
            character.setCreator(authors.get(random.nextInt(authors.size())));
            character.setGenreID(random.nextInt(genreRepository.findAll().size()));

            Genre genre = genreRepository.findById(
                    (long) character.getGenreID()).get();
            character.setGenre(genre);
            genre.setNumberOfCharacters(genre.getNumberOfCharacters() + 1);
            genreRepository.save(genre);

            character.setDescription(faker.lorem().sentence());
            characters.add(character);
        }
        return characters;
    }

    public Character generateCharacter() {
        Random random = new Random();
        Character character = new Character();
        character.setCharacterName(faker.name().fullName());
        character.setAge(faker.number().numberBetween(1, 100));
//        character.setIconicLines(faker.harryPotter().quote());
        character.setIconicLines("Iconic Lines " + character.getCharacterName());
//        character.setCreator(authors.get(random.nextInt(authors.size())));
        character.setCreator("Creator of " + character.getCharacterName());
//        character.setGenreID(random.nextInt(1,genreRepository.findAll().size()));
        character.setGenreID(random.nextInt(1, 100000));

//        Optional<Genre> genreOptional = genreRepository.findById((long) character.getGenreID());
//        if (genreOptional.isPresent()) {
//            character.setGenre(genreOptional.get());
//        } else {
//            System.err.println("Genre not found for ID: " + character.getGenreID());
//            character.setGenre(genreRepository.findAll().getFirst());
//        }

//        character.setDescription(faker.lorem().sentence());
        character.setDescription("Description of " + character.getCharacterName());
        return character;
    }
}
