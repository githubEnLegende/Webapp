package org.oxyl.servlet;

import org.oxyl.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/displayQuestion")
public class DisplayQuestionServlet{

    private QuestionService questionService;

    public DisplayQuestionServlet(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String displayQuestion(Model model, @RequestParam(value = "id") String id) {
        List<String> result = questionService.getQuestionAnswer(Integer.parseInt(id));

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
