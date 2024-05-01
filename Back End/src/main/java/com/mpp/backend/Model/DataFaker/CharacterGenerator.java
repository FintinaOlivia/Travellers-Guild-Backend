package com.mpp.backend.Model.DataFaker;

import com.github.javafaker.Faker;
import com.mpp.backend.Model.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterGenerator {
    private final Faker faker;

    @Autowired
    public CharacterGenerator(Faker faker) {
        this.faker = faker;
    }

    public List<Character> generateCharacters(int amount) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            Character character = new Character();
            character.setCharacterName(faker.name().fullName());
            character.setAge(faker.number().numberBetween(1, 100));
            character.setIconicLines(faker.harryPotter().quote());
            character.setCreator(faker.book().author());
            character.setDescription(faker.lorem().sentence());
            characters.add(character);
        }
        return characters;
    }

    public Character generateCharacter() {
        Character character = new Character();
        character.setCharacterName(faker.name().fullName());
        character.setAge(faker.number().numberBetween(1, 100));
        character.setIconicLines(faker.harryPotter().quote());
        character.setCreator(faker.book().author());
        character.setDescription(faker.lorem().sentence());
        return character;
    }
}
