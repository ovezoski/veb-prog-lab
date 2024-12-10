//package com.finki.lab.web;
//
//import com.finki.lab.service.ArtistService;
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
//@WebServlet("/artist")
//public class ArtistServlet  extends HttpServlet {
//
//    private final ArtistService artistService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//
//    public ArtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine){
//            this.artistService = artistService;
//            this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, res);
//
//        WebContext context = new WebContext(webExchange);
//
//        context.setVariable("artists", artistService.listArtists());
//        context.setVariable("trackId", req.getParameter("trackId"));
//
//        springTemplateEngine.process("artistsList.html",  context, res.getWriter());
//    }
//
//
//}
