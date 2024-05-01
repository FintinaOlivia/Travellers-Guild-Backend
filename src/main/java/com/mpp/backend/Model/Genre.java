package com.mpp.backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Genre {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long genreID;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    public List<Character> characters;

    @Column(name = "name")
    public String name;

    @Column(name = "typical_traits")
    public String typicalTraits;

    @Column(name = "number_of_characters")
    public int numberOfCharacters;

    public String description;
}
