//package com.finki.lab.web;
//
//
//import com.finki.lab.service.SongService;
//import jakarta.servlet.ServletException;
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
//
//@WebServlet(name = "SongServlet" , urlPatterns = "/listSongs")
//public class SongListServlet extends HttpServlet {
//
//    private final SongService songService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public SongListServlet(SongService songService, SpringTemplateEngine springTemplateEngine){
//        this.songService =  songService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(request, response);
//
//        WebContext context = new WebContext(webExchange);
//
//
//
//        context.setVariable("songs", songService.listSongs());
//
//        springTemplateEngine.process("listSongs.html", context, response.getWriter());
//    }
//
//
//
//
//
//
//}
