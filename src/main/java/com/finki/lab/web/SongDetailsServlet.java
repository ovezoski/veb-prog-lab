//package com.finki.lab.web;
//
//import com.finki.lab.model.Artist;
//import com.finki.lab.model.Song;
//import com.finki.lab.service.ArtistService;
//import com.finki.lab.service.SongService;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet("/songDetails")
//public class SongDetailsServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final SongService songService;
//    private final ArtistService artistService;
//
//    public SongDetailsServlet(SpringTemplateEngine springTemplateEngine, SongService songService, ArtistService artistService){
//        this.springTemplateEngine = springTemplateEngine;
//        this.songService = songService;
//        this.artistService = artistService;
//    }
//
//    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, res);
//
//        WebContext context = new WebContext(webExchange);
//
//        String trackId = req.getParameter("trackId");
//        Long artistId = Long.valueOf(req.getParameter("artistId"));
//
//        Optional<Song> song = songService.findByTrackId(trackId);
//        Optional<Artist> artist = artistService.ArtistfindById(artistId);
//
//        artist.ifPresent(a -> song.ifPresent(s -> songService.addArtistToSong(a, s)));
//
//        song.ifPresent(s-> context.setVariable("song", s));
//
//        song.ifPresent(s -> context.setVariable("performers", s.getPerformers()));
//
//
//        springTemplateEngine.process("songDetails.html", context, res.getWriter());
//    }
//
//}
