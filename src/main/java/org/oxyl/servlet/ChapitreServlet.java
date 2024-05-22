package org.oxyl.servlet;

import org.oxyl.model.Chapitre;
import org.oxyl.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chapitre")
public class ChapitreServlet {

    private final static Logger logger = LoggerFactory.getLogger(ChapitreServlet.class);
    private ChapterService chapterService;

    public ChapitreServlet(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public String displayChapter(Model model){
        logger.info("Entrez dans le Display Chapitre");

        var optChapitre = chapterService.getAllChapter();
        List<Chapitre> chapitres;
        chapitres = optChapitre.orElseGet(ArrayList::new);

        model.addAttribute("chapitres", chapitres);

        return "chapitre";
    }

}
