package com.finki.lab.repository;

import com.finki.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    public Optional<Song> findByTrackId(String trackId);
    public List<Song> findByAlbumId(Long Id);
//    public Song findById(String Id);
}
