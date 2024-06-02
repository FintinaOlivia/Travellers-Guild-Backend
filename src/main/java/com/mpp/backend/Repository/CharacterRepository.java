package com.mpp.backend.Repository;

import com.mpp.backend.Model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query("SELECT c FROM Character c WHERE LOWER(c.characterName) LIKE LOWER(concat('%', :characterName, '%'))")
    List<Character> findCharactersByName(String characterName);
    @Query("SELECT COUNT(c) FROM Character c WHERE c.genreID = :genreID")
    Integer countByGenreID(Long genreID);
}
