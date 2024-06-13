package org.oxyl.webapp.controller;

import org.oxyl.core.model.Chapitre;
import org.oxyl.service.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chapitres")
public class ChapitreRestController {

    private final static Logger logger = LoggerFactory.getLogger(ChapitreController.class);
    private final ChapterService chapterService;

    public ChapitreRestController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Chapitre>> getChapitres() {

        var optChapitre = chapterService.getAllChapter();
        List<Chapitre> chapitres = optChapitre.orElseGet(ArrayList::new);
        return ResponseEntity.ok(chapitres);
    }

}
