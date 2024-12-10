package com.finki.lab.service;

import com.finki.lab.model.Artist;
import com.finki.lab.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongService{
    List<Song> listSongs();
    void addArtistToSong(Artist artist, Song song);
    public Song findByTrackId(String trackId);
    public void deleteSong(Long Id);
    public Optional<Song> findById(Long Id);
    public void create(String trackId, String title, String genre, String releaseYear, List<Long> performerIds, Long albumId);
    public void edit(Long Id, String trackId, String title, String genre, String releaseYear, List<Long> performerIds, Long albumId);
    public List<Song> findByAlbumId(Long id);
}