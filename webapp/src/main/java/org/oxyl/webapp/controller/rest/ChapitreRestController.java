package org.oxyl.webapp.controller.rest;

import org.oxyl.bindings.mapper.MapperChapitre;
import org.oxyl.core.model.Chapitre;
import org.oxyl.bindings.dto.ChapterDTO;
import org.oxyl.service.service.ChapterService;
import org.oxyl.webapp.controller.ChapitreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chapitres")
public class ChapitreRestController {

    private final static Logger logger = LoggerFactory.getLogger(ChapitreController.class);
    private final ChapterService chapterService;
    private final MapperChapitre mapperChapitre;

    public ChapitreRestController(ChapterService chapterService,
                                  MapperChapitre mapperChapitre) {
        this.chapterService = chapterService;
        this.mapperChapitre = mapperChapitre;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ChapterDTO>> getChapitres() {

        List<Chapitre> chapitres = chapterService.getAllChapter();
        return ResponseEntity.ok(
                chapitres
                        .stream()
                        .map(mapperChapitre::convertToChapterDTO)
                        .toList()
        );
    }

    @GetMapping(value = "/root", produces = "application/json")
    public ResponseEntity<List<ChapterDTO>> getRootChapter(){
        return ResponseEntity.ok(chapterService.getRootChapter()
                .stream()
                .map(mapperChapitre::convertToChapterDTO)
                .toList()
        );
    }

}
