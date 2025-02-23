package org.oxyl.webapp.controller;

import org.oxyl.core.model.Question;
import org.oxyl.service.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    private final static Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String questions(Model model, @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrée dans le Get Chapitre");
        List<Question> questions = questionService.getAllQuestion();
        model.addAttribute("questions", questions);
        model.addAttribute("lang", lang);
        return "question";
    }

}
