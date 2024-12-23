package com.finki.lab.web.controller;

import com.finki.lab.model.Album;
import com.finki.lab.model.Artist;
import com.finki.lab.model.Song;
import com.finki.lab.service.AlbumService;
import com.finki.lab.service.ArtistService;
import com.finki.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService){
        this.songService = songService;

        this.albumService = albumService;

        this.artistService = artistService;

    }
    @GetMapping({"/songs", "/"})
    public String getSongsPage(@RequestParam(required = false) String error, @RequestParam(required = false) Long albumId, Model model){

        List <Song> songs;
        List <Album> albums = albumService.findAll();

        if(albumId == null){
            songs =songService.listSongs();
        }
        else{
            songs = songService.findByAlbumId(albumId);
        }

        model.addAttribute("songs", songs);
        model.addAttribute("albums", albums);
        model.addAttribute("error", error);
        model.addAttribute("bodyContent", "listSongs");
        return "master-template";
//        return "listSongs";

    }

    @GetMapping("/songs/{Id}")
    public String getSongDetails(@PathVariable Long Id, Model model){
        Optional<Song> song = songService.findById(Id);

        if(song.isPresent()){
            model.addAttribute("song", song.orElseThrow());

            return "songDetails.html";
        }
        return "redirect:/songs?error=SongNotFound";
    }

    @PostMapping(path = "/songs/{Id}/delete")
    public String deleteSong(@PathVariable Long Id ){

        songService.deleteSong(Id);

        return "redirect:/songs";
    }

    @GetMapping(path="/songs/{Id}/edit")
    public String getEditForm(@PathVariable Long Id, Model model){

        Optional<Song> song = songService.findById(Id);

        if(song.isPresent()) {


            List<Album> albums = albumService.findAll();
            List<Artist> artists = artistService.listArtists();

            model.addAttribute("song", song.orElseThrow());

            model.addAttribute("albums", albums);
            model.addAttribute("performers", artists);


            return "add-song.html";
        }

        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping(path="/songs/add")
    public String getAddForm(Model model){
        List<Album> albums = albumService.findAll();
        List<Artist> artists = artistService.listArtists();
        model.addAttribute("albums", albums);
        model.addAttribute("performers", artists);
        return "add-song.html";
    }

    @PostMapping(path="/songs/save")
    public String saveSong(
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam String releaseYear,
            @RequestParam List<Long> performerIds,
            @RequestParam Long albumId
    ){

        songService.create(trackId, title, genre, releaseYear, performerIds, albumId);

        return "redirect:/songs";
    }

    @PostMapping("/songs/{Id}/edit")
    public String editSong(
            @PathVariable Long Id,
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam String releaseYear,
            @RequestParam List<Long> performerIds,
            @RequestParam Long albumId
    ){
        if(songService.findById(Id).isPresent()) {


            songService.edit(Id, trackId, title, genre, releaseYear, performerIds, albumId);

            return "redirect:/songs";
        }

        return "redirect:/songs?error=SongNotFound";
    }

    @GetMapping("/songs/album/{Id}")
    public String getSongsByAlbum(@PathVariable Long Id,  Model model){
        List<Song> songs = songService.findByAlbumId(Id);

        model.addAttribute("songs", songs);

        return "listSongs.html";
    }


}
