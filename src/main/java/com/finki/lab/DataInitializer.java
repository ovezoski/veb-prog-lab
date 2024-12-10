package com.finki.lab;

import com.finki.lab.model.Album;
import com.finki.lab.model.Artist;
import com.finki.lab.model.Song;
import com.finki.lab.repository.AlbumRepository;
import com.finki.lab.repository.ArtistRepository;
import com.finki.lab.repository.SongRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer {

    private final AlbumRepository albumRepository;

    private final SongRepository songRepository;

    private final ArtistRepository artistRepository;

    public DataInitializer(AlbumRepository albumRepository, SongRepository songRepository, ArtistRepository artistRepository){
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
    }

    @PostConstruct
    @Transactional
    public void initData(){

//        songs.add(new Song(null, "111-111", "Skandal", "Rap", 2005, performers, album));
//        songs.add(new Song(null , "222-111", "Galama", "Rap", 2006,  performers, album));
//        songs.add(new Song(null, "333-111", "Bass", "Rap", 2007,    performers, album));
//        songs.add(new Song(null, "444-111", "Uptown", "Rap", 2008,   performers, album));
//        songs.add(new Song(null , "555-111", "Utopia", "Rap", 2009,   performers, album));


        List<Album> albums = new ArrayList<>();
        albums.add(new Album(null, "Graduation", "Rap", "2008"));
        albums.add(new Album(null, "Rodeo", "Rap", "2010"));
        albums.add(new Album(null, "Astroworld", "Rap", "2008"));
        albums.add(new Album(null, "The Life of Pablo", "Rap", "2016"));

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(null, "Travis", "Scott", "La Flame"));
        artists.add(new Artist(null, "George", "Michael", "Careless Whisper author"));
        artists.add(new Artist(null, "Toni", "Zen", "Tonimontana"));
        artists.add(new Artist(null, "Kanye", "West", "And I still drove 30 hours"));
        artists.add(new Artist(null, "Taylor", "Swift", "La Swifty"));

        List<Song> songs = new ArrayList<>();
        List<Artist> performers = new ArrayList<>();
        performers.add(artists.get(0));

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int randomAlbumIndex = random.nextInt(albums.size());
            Album randomAlbum = albums.get(randomAlbumIndex);

            songs.add(new Song(null, (i+1) + "-123-444-555", "Song " + (i+1), "Rap", 2000 + i, performers, randomAlbum));
        }

         artistRepository.saveAll(artists);
         albumRepository.saveAll(albums);
         songRepository.saveAll(songs);
    }

}
