package com.mpp.backend.Repository;

import com.mpp.backend.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("SELECT g.name FROM Genre g WHERE g.name LIKE %:name%")
    List<String> findGenresByName(String name);

}
