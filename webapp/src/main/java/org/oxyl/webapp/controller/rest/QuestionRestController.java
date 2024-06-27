package org.oxyl.webapp.controller.rest;

import org.oxyl.core.model.Question;
import org.oxyl.service.service.QuestionService;
import org.oxyl.webapp.controller.QuestionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionRestController {
    private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Question>> getAllQuestions() {
        logger.info("Entrée dans Question");
        List<Question> questions = questionService.getAllQuestion();
        return ResponseEntity.ok(questions);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<List<String>> getQuestionById(@PathVariable("id") long id) {
        List<String> result = questionService.getQuestionAnswer(id);
        return ResponseEntity.ok(result);
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
}
