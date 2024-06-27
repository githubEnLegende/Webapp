package org.oxyl.webapp.controller;

import org.oxyl.core.model.Chapitre;
import org.oxyl.service.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chapitre")
public class ChapitreController {

    private final static Logger logger = LoggerFactory.getLogger(ChapitreController.class);
    private final ChapterService chapterService;

    public ChapitreController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public String displayChapter(Model model, @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrez dans le Display Chapitre");

        List<Chapitre> chapitres = chapterService.getAllChapter();

        model.addAttribute("chapitres", chapitres);
        model.addAttribute("lang", lang);


        return "chapitre";
    }

}
