package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.context.Context;
import org.oxyl.model.Question;
import org.oxyl.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {

    private final static Logger logger = LoggerFactory.getLogger(QuestionServlet.class);
    ApplicationContext context = Context.getInstance().getContext();
    private QuestionService questionService;


    public void init() {
        questionService = context.getBean(QuestionService.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Entrée dans le Get Chapitre");
        var optQuestions = questionService.getAllQuestion();
        List<Question> questions;
        if (optQuestions.isPresent()) {
            questions = optQuestions.get();
        } else {
            questions = new ArrayList<>();
        }

        request.setAttribute("questions", questions);


        request.getRequestDispatcher("WEB-INF/question.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
