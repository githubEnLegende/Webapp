package org.oxyl.webapp.controller.rest;

import org.oxyl.bindings.dto.chapterdto.SubChapterDTO;
import org.oxyl.bindings.dto.questiondto.QuestionQuizDTO;
import org.oxyl.bindings.mapper.MapperChapitre;
import org.oxyl.bindings.mapper.MapperQuestion;
import org.oxyl.core.model.Chapitre;
import org.oxyl.bindings.dto.chapterdto.ChapterDTO;
import org.oxyl.persistence.QuestionDAO;
import org.oxyl.service.service.ChapterService;
import org.oxyl.service.service.QuestionService;
import org.oxyl.webapp.controller.ChapitreController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chapters")
public class ChapitreRestController {

    private final static Logger logger = LoggerFactory.getLogger(ChapitreController.class);
    private final ChapterService chapterService;
    private final MapperChapitre mapperChapitre;
    private final QuestionService questionService;
    private final MapperQuestion mapperQuestion;

    public ChapitreRestController(ChapterService chapterService,
                                  MapperChapitre mapperChapitre,
                                  QuestionService questionService, MapperQuestion mapperQuestion) {
        this.chapterService = chapterService;
        this.mapperChapitre = mapperChapitre;
        this.questionService = questionService;
        this.mapperQuestion = mapperQuestion;
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

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<SubChapterDTO> getSubChapter(@PathVariable("id") long id) {
        var chapters = chapterService.getSubChapter(id);
        if(chapters.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var chapterDTO =  mapperChapitre.convertToListSubChapterDTO(chapters);
        return ResponseEntity.ok(chapterDTO);
    }

    @GetMapping(value = "/{id}/questions")
    public ResponseEntity<List<QuestionQuizDTO>> getQuestionOfChapter(
            @PathVariable("id") int chapterId,
            @RequestParam(value = "number", required = true) int number) {
        return ResponseEntity.ok(questionService.getQuestionOfChapter(chapterId, number)
                .stream()
                .map(mapperQuestion::convertToQuestionQuizDTO)
                .toList());
    }

}
