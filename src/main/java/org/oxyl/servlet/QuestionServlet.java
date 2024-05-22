package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.model.Question;
import org.oxyl.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionServlet {

    private final static Logger logger = LoggerFactory.getLogger(QuestionServlet.class);
    private QuestionService questionService;

    public QuestionServlet(QuestionService questionService) {
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
