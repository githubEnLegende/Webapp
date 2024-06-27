package org.oxyl.service.service;

import org.oxyl.core.model.Question;
import org.oxyl.persistence.QuestionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public void getQuestionById(int questionId) {
        logger.info("Get question by id: {}", questionId);
        questionDAO.getQuestionById(questionId);
    }

    public void deleteQuestion(long id) {
        logger.info("Delete question by id: {}", id);
        questionDAO.deleteQuestion(id);
    }

    public List<Question> getAllQuestion() {
        logger.info("Get all questions");
        return questionDAO.getAllQuestion();
    }

    public List<String> getQuestionAnswer(long id) {
        logger.info("Get question answer by id: {}", id);
        return questionDAO.getQuestionAnswer(id);
    }
}
