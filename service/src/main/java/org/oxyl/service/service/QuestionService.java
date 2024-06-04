package org.oxyl.service.service;

import org.oxyl.core.model.Question;
import org.oxyl.persistence.QuestionDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public void getQuestionById(int questionId) {
        questionDAO.getQuestionById(questionId);
    }

    public void deleteQuestion(int id) {
        questionDAO.deleteQuestion(id);
    }

    public List<Question> getAllQuestion() {
        return questionDAO.getAllQuestion();
    }

    public List<String> getQuestionAnswer(long id) {
        return questionDAO.getQuestionAnswer(id);
    }
}
