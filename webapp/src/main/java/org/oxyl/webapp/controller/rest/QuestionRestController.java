package org.oxyl.webapp.controller.rest;

import jakarta.validation.Valid;
import org.oxyl.bindings.dto.questiondto.*;
import org.oxyl.bindings.mapper.MapperQuestion;
import org.oxyl.core.model.Question;
import org.oxyl.service.service.ChapterService;
import org.oxyl.service.service.QuestionService;
import org.oxyl.webapp.controller.QuestionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionRestController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;
    private final ChapterService chapterService;
    private final MapperQuestion mapperQuestion;

    public QuestionRestController(QuestionService questionService, ChapterService chapterService, MapperQuestion mapperQuestion) {
        this.questionService = questionService;
        this.chapterService = chapterService;
        this.mapperQuestion = mapperQuestion;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<QuestionPageDTO>> getAllQuestions() {
        logger.info("Entrée dans Question");
        List<Question> questions = questionService.getAllQuestion();
        return ResponseEntity.ok(questions.stream()
                .map(mapperQuestion::convertToQuestionPageDTO)
                .toList()
        );
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<QuestionInformationDTO> getQuestionById(@PathVariable("id") long id) {
        var result = questionService.getQuestionById(id);
        return result
                .map(mapperQuestion::convertToQuestionInformationDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable("id") long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de la supression d'une question");
        }

    }

    @PostMapping
    public ResponseEntity<Long> postQuestion(
            @Valid @RequestBody QuestionAddDTO questionAddDTO
    ) {
        if(!chapterService.exist(questionAddDTO.chapterId())){
            return ResponseEntity.notFound().build();
        }
        var id = questionService.save(mapperQuestion.convertToModel(questionAddDTO));
        return ResponseEntity.ok(id);
    }

    @PutMapping
    public ResponseEntity<Long> putQuestion(
            @Valid @RequestBody QuestionEditDTO questionEditDTO
    ) {
        if(!questionService.exist(questionEditDTO.id()) || !chapterService.exist(questionEditDTO.chapterId())){
            return ResponseEntity.notFound().build();
        }
        var id = questionService.edit(mapperQuestion.convertToModel(questionEditDTO));
        return ResponseEntity.ok(id);
    }
}
