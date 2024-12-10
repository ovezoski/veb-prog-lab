package com.finki.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String trackId;

    private String title;

    private String genre;

    private int releaseYear;

    @Getter
    @ManyToMany
    private List<Artist> performers;

    @Getter
    @ManyToOne
    private Album album;

}


