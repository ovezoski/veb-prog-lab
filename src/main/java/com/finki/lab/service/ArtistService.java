package com.finki.lab.service;

import com.finki.lab.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService{
    List<Artist> listArtists();
    Artist findById(Long id);
}
