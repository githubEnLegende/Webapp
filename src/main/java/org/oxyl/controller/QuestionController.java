package org.oxyl.controller;

import org.oxyl.model.Question;
import org.oxyl.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String questions(Model model) {
        logger.info("Entrée dans le Get Chapitre");
        var optQuestions = questionService.getAllQuestion();
        List<Question> questions;
        if (optQuestions.isPresent()) {
            questions = optQuestions.get();
        } else {
            questions = new ArrayList<>();
        }

        model.addAttribute("questions", questions);
        return "question";
    }

}
