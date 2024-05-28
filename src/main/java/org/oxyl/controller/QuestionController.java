package org.oxyl.controller;

import org.oxyl.model.Question;
import org.oxyl.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String questions(Model model, @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrée dans le Get Chapitre");
        var optQuestions = questionService.getAllQuestion();
        List<Question> questions;
        questions = optQuestions.orElseGet(ArrayList::new);

        model.addAttribute("questions", questions);
        model.addAttribute("lang", lang);
        return "question";
    }

    @GetMapping("/{id}")
    public String displayQuestion(Model model, @PathVariable(value = "id") String id) {
        List<String> result = questionService.getQuestionAnswer(Long.parseLong(id));

        String title = result.removeFirst();
        String statement = result.removeFirst();
        String chapter_id = result.removeFirst();

        model.addAttribute("title", title);
        model.addAttribute("statement", statement);
        model.addAttribute("chapter_id", chapter_id);
        model.addAttribute("answers", result);

        return "displayQuestion";
    }

}
