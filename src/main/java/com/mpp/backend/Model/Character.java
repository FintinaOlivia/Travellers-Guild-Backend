package com.mpp.backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "iconic_lines")
    private String iconicLines;

    @Column(name = "creator")
    private String creator;

    @Column(name = "genre_id")
    private Integer genreID;

    @ManyToOne
    @JoinColumn(name = "genre")
    @JsonIgnore
    private Genre genre;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}