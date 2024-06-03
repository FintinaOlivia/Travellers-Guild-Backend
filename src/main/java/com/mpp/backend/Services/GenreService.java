package com.mpp.backend.Services;

import com.mpp.backend.Model.Character;
import com.mpp.backend.Model.DataFaker.GenreGenerator;
import com.mpp.backend.Model.Genre;
import com.mpp.backend.Repository.CharacterRepository;
import com.mpp.backend.Repository.GenreRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    GenreGenerator genreGenerator;

    @Autowired
    CharacterRepository characterRepository;

//    @PostConstruct
//    public void initializeGenres() {
//        for(Genre genre : genreRepository.findAll()){
//            int numberOfCharacters = (int) characterRepository
//                    .findAll()
//                    .stream()
//                            .filter(character -> character.getGenreID().longValue() == genre.getGenreID())
//                                    .count();
//            genre.setNumberOfCharacters(numberOfCharacters);
//            genreRepository.save(genre);
//        }
//        for(int i = 0; i < 40000; i++){
//            Genre genre = genreGenerator.generateGenre();
//            addGenre(genre);
//        }
//    }

    public List<Genre> getGenres(){
        return genreRepository.findAll();
    }

    public Page<Genre> getGenres(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        return genreRepository.findAll(pageable);
    }

    public Genre getGenreByID(Long id){
        Optional<Genre> genreOptional = genreRepository.findById(id);
        return genreOptional.orElse(null);
    }

    public List<Genre> getGenresByUsername(String username){
        return genreRepository.findGenresByUsername(username);
    }

    public void addGenre(Genre genre){
            genreRepository.save(genre);
    }

    public void removeGenre(Long id){
        if(genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        }
    }

    public Integer countByGenreID(Long genreID){
        return characterRepository.countByGenreID(genreID);
    }

    public List<String> findGenresByName(String name){
        return genreRepository.findGenresByName(name);
    }

}
