package com.mpp.backend.Services;

import com.mpp.backend.Controllers.WebSocket.WebSocketConfig;
import com.mpp.backend.Controllers.WebSocket.WebSocketController;
import com.mpp.backend.Model.Character;
import com.mpp.backend.Model.DataFaker.CharacterGenerator;
import com.mpp.backend.Repository.CharacterRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


@Service
public class CharacterService {

    @Autowired
    CharacterRepository characterRepository;

    @Autowired
    CharacterGenerator characterGenerator;

    @Autowired
    WebSocketController webSocketController;


//    @PostConstruct
//    public void initializeCharacters() {
//        for(int i = 0; i < 3000; i++){
//            Character character = characterGenerator.generateCharacter();
//            addCharacter(character);
//        }
//        generateAndSendCharacterEveryKSeconds(2);
//    }

    public void generateAndSendCharacterEveryKSeconds(int k){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Character character = characterGenerator.generateCharacter();

//                if (validateCharacter(character)) {
                    characterRepository.save(character);
                    webSocketController.broadcast(character.getCharacterName() + " has been added!");
                    webSocketController.newCharacter(character.getCharacterName() + " has been added to the list!");
//                }
                System.out.println("Character list size: " + characterRepository.findAll().size());
            }
        }, 0, 2 * 1000L); // k seconds interval

    }


    public List<Character> getCharacters(){
        return characterRepository.findAll();
    }

    public Page<Character> getCharacters(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return characterRepository.findAll(pageable);
    }

    public Character findCharacterById(Long id){
        return characterRepository.findById(id).orElse(null);
    }

    public void addCharacter(Character character){
        characterRepository.save(character);
    }

    public void removeCharacter(Character character){
        characterRepository.delete(character);
    }

    public List<Character> getCharactersByName(String name){
        return characterRepository.findCharactersByName(name);
    }

    public List<Character> getCharactersByUser(String username){
        return characterRepository.findCharactersByUser(username);
    }

    private static boolean noNullFields(Character character){
        return character.getCharacterName() != null &&
                character.getId() != null &&
                character.getAge() != null &&
                character.getCreator() != null &&
                character.getDescription() != null;
    }

    public boolean noDuplicateCharacters(Character toValidateCharacter) {
        return getCharacters().stream()
                .anyMatch(character ->
                        character.getCharacterName().equals(toValidateCharacter.getCharacterName()) &&
                                character.getCreator().equals(toValidateCharacter.getCreator()) &&
                                !character.getId().equals(toValidateCharacter.getId()));
    }

    private boolean isUnique(Long newId){
        return getCharacters().stream()
                .noneMatch(character -> character.getId().equals(newId));

    }

    public boolean validateCharacter(Character character){
        return noNullFields(character) && (character.getAge() >= 0)
                && isUnique(character.getId());
    }

}
