package com.finki.lab.web.controller;

import com.finki.lab.model.Artist;
import com.finki.lab.model.Song;
import com.finki.lab.service.ArtistService;
import com.finki.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ArtistController {

    private final SongService songService;
    private final ArtistService artistService;

    public ArtistController(SongService songService, ArtistService artistService){
            this.songService = songService;
            this.artistService = artistService;
    }

    @GetMapping("/artist")
    public String showArtistSongPage(Model model, @RequestParam String trackId){
        List<Artist> artists = artistService.listArtists();

        model.addAttribute("artists", artists);
        model.addAttribute("trackId", trackId);

        return "artistsList";
    }

    @PostMapping("/artist/song/add")
    public String addArtistToSong(Model model,
                                  @RequestParam String trackId,
                                  @RequestParam Long artistId
    ){

        Song song = songService.findByTrackId(trackId);
        Artist artist = artistService.findById(artistId);

        if(song.getPerformers().contains(artist)){
            return "redirect:/songs?error=PerformerAlreadyAdded";
        }

         songService.addArtistToSong(artist, song);


         model.addAttribute("song", song);

        return "songDetails.html";
    }

}
