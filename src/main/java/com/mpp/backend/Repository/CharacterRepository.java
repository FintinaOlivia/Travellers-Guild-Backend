package com.mpp.backend.Repository;

import com.mpp.backend.Model.Character;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Repository
public class CharacterRepository {
    public List<Character> characters = new ArrayList<>(){{
        add(new Character(2L, "Elizabeth Bennet", 21, "You are too hasty, sir.", "Jane Austen", "Elizabeth Bennet is the protagonist in Jane Austen's novel Pride and Prejudice. She is portrayed as intelligent, independent, and witty."));
        add(new Character(3L, "Sherlock Holmes", 34, "Elementary, my dear Watson.", "Arthur Conan Doyle", "Sherlock Holmes is a fictional detective created by Sir Arthur Conan Doyle. He is known for his logical reasoning, deductive skills, and iconic deerstalker hat."));
        add(new Character(4L, "Hermione Granger", 11, "It's LeviOsa, not LevioSA.", "J.K. Rowling", "Hermione Granger is a character in the Harry Potter series by J.K. Rowling. She is known for her intelligence, bravery, and loyalty to her friends."));
        add(new Character(6L, "Darcy", 29, "In vain have I struggled. It will not do. My feelings will not be repressed. You must allow me to tell you how ardently I admire and love you.", "Jane Austen", "Mr. Fitzwilliam Darcy is a character in Jane Austen's novel Pride and Prejudice. He is initially perceived as proud and arrogant but later reveals himself to be honorable and deeply in love with Elizabeth Bennet."));
        add(new Character(36L, "Emma", 17, "I always deserve the best treatment because I never put up with any other.", "Jane Austen", ""));
        add(new Character(37L, "Gale Dekarios", 40, "I am Gale of Waterdeep, the greatest wizard of all times.", "Larian Studios", "Gale is a wizard companion in the video game Baldur's Gate 3. He is known for his intelligence, magical abilities, and charming personality."));
        add(new Character(38L, "Shadowheart", 22, "I am God's favourite princess.", "Larian Studios", "Shadowheart is a cleric companion in the video game Baldur's Gate 3. She is known for her mysterious past, divine powers, and enigmatic personality."));
        add(new Character(39L, "Astarion", 190, "I am the night.", "Larian Studios", "Astarion is a vampire rogue companion in the video game Baldur's Gate 3. He is known for his stealthy skills, charming demeanor, and dark secret."));
        add(new Character(45L, "Lae'zel", 0, "I am the Absolute.", "Larian Studios", "Lae'zel is a githyanki warrior companion in the video game Baldur's Gate 3. She is known for her martial prowess, devotion to her people, and stoic nature."));
        add(new Character(338L, "Geralt of Rivia", 0, "Evil is evil. Lesser, greater, middling, makes no difference. The degree is arbitrary. The definition’s blurred. If I’m to choose between one evil and another, I’d rather not choose at all.", "Andrzej Sapkowski", "Geralt of Rivia is a monster hunter and the protagonist of The Witcher series by Andrzej Sapkowski. He is known for his combat skills, moral ambiguity, and silver hair."));
        add(new Character(339L, "Yennefer of Vengerberg", 0, "You smell of death and destiny, heroics and heartbreak.", "Andrzej Sapkowski", "Yennefer of Vengerberg is a sorceress and love interest of Geralt of Rivia in The Witcher series by Andrzej Sapkowski. She is known for her beauty, powerful magic, and complex personality."));
        add(new Character(340L, "Ciri", 12, "I am no man’s prize.", "Andrzej Sapkowski", "Ciri is a princess and the adopted daughter of Geralt of Rivia in The Witcher series by Andrzej Sapkowski. She is known for her swordsmanship, magical abilities, and destiny as the Lady of Time and Space."));
        add(new Character(341L, "Jaskier", 27, "Toss a coin to your Witcher, O' Valley of Plenty.", "Andrzej Sapkowski", "Jaskier, also known as Dandelion, is a bard and friend of Geralt of Rivia in The Witcher series by Andrzej Sapkowski. He is known for his music, wit, and penchant for getting into trouble."));
        add(new Character(343L, "Leia Organa", 19, "Help me, Obi-Wan Kenobi. You're my only hope.", "George Lucas", "Leia Organa is a princess and rebel leader in the Star Wars franchise created by George Lucas."));
    }};

    public void setCharacters(List<Character> listOfCharacters){
        this.characters = listOfCharacters;
    }

    public void removeCharacter(Character character){
        characters.remove(character);
    }

    public void addCharacter(Character character){
        characters.add(character);
    }
}
