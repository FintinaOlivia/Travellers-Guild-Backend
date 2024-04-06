package com.mpp.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    private Long id;
    private String characterName;
    private Integer age;
    private String iconicLines;
    private String creator;
    private String description;
}