package org.oxyl.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.oxyl.context.Context;
import org.oxyl.service.QuestionService;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.io.IOException;

@WebServlet("/displayQuestion")
public class DisplayQuestionServlet extends HttpServlet {

    private final ApplicationContext context = Context.getInstance().getContext();
    private QuestionService questionService;

    public void init() {
        questionService = context.getBean(QuestionService.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        List<String> result = questionService.getQuestionAnswer(Integer.parseInt(id));

        String title = result.removeFirst();
        String statement = result.removeFirst();
        String chapter_id = result.removeFirst();

        request.setAttribute("title", title);
        request.setAttribute("statement", statement);
        request.setAttribute("chapter_id", chapter_id);
        request.setAttribute("answers", result);

        request.getRequestDispatcher("WEB-INF/displayQuestion.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

}
