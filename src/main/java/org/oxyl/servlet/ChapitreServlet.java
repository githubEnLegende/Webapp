//package org.oxyl.servlet;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.oxyl.model.Chapitre;
//import org.oxyl.service.ChapterService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/chapitre")
//public class ChapitreServlet extends HttpServlet {
//
//    private final static Logger logger = LoggerFactory.getLogger(ChapitreServlet.class);
////    ApplicationContext context = Context.getInstance().getContext();
//    private ChapterService chapterService;
//
//
////    public void init() {
////        chapterService = context.getBean(ChapterService.class);
////    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        logger.info("Entrez dans le Get Chapitre");
//
//        var optChapitre = chapterService.getAllChapter();
//        List<Chapitre> chapitres;
//        if (optChapitre.isPresent()) {
//            chapitres = optChapitre.get();
//        } else {
//            chapitres = new ArrayList<>();
//        }
//
//        request.setAttribute("chapitres", chapitres);
//
//
//        request.getRequestDispatcher("WEB-INF/chapitre.jsp").forward(request, response);
//
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//    }
//
//}
