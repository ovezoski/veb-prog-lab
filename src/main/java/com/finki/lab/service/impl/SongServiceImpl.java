package com.finki.lab.service.impl;

import com.finki.lab.model.Album;
import com.finki.lab.model.Artist;
import com.finki.lab.model.Song;
import com.finki.lab.repository.AlbumRepository;
import com.finki.lab.repository.ArtistRepository;
import com.finki.lab.repository.SongRepository;
import com.finki.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, AlbumRepository albumRepository, ArtistRepository artistRepository){
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public void addArtistToSong(Artist artist, Song song) {

        Song localSong = songRepository.findByTrackId(song.getTrackId()).orElseThrow();

        localSong.getPerformers().add(artist);

        songRepository.save(localSong);

    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId).orElseThrow();
    }

    @Override
    public void deleteSong(Long Id) {
        songRepository.deleteById(Id);
    }

    @Override
    public Optional<Song> findById(Long Id) {
        return songRepository.findById(Id);
    }

    public void create(String trackId, String title, String genre, String releaseYear, List<Long> performerIds, Long albumId) {

        List<Artist> performers = artistRepository.findAllById(performerIds);

        Album album = albumRepository.findById(albumId).orElseThrow();

        Song song = new Song(null, trackId, title, genre, Integer.parseInt(releaseYear), performers, album);

        songRepository.save(song);
    }

    @Override
    public void edit(Long Id, String trackId, String title, String genre, String releaseYear, List<Long> performerIds, Long albumId) {
        Song song = songRepository.findById(Id).orElseThrow();
        List<Artist> artists = artistRepository.findAllById(performerIds);
        Album album = albumRepository.findById(albumId).orElseThrow();

        song.setTrackId(trackId);
        song.setTitle(title);
        song.setGenre(genre);
        song.setReleaseYear(Integer.parseInt(releaseYear));
        song.setPerformers(artists);
        song.setAlbum(album);

        songRepository.save(song);

    }

    @Override
    public List<Song> findByAlbumId(Long Id) {
        return songRepository.findByAlbumId(Id);
    }
}
