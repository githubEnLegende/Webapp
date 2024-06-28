package org.oxyl.service.service;

import org.oxyl.bindings.dto.questiondto.QuestionEditDTO;
import org.oxyl.bindings.dto.questiondto.QuestionQuizDTO;
import org.oxyl.core.model.Question;
import org.oxyl.core.model.Reponse;
import org.oxyl.persistence.AnswerDAO;
import org.oxyl.persistence.QuestionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

    private final QuestionDAO questionDAO;
    private final AnswerDAO answerDAO;

    public QuestionService(QuestionDAO questionDAO, AnswerDAO answerDAO) {
        this.questionDAO = questionDAO;
        this.answerDAO = answerDAO;
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

    public List<Question> getQuestionOfChapter(
            int chapterId,
            int number) {
        return questionDAO.getQuestionOfChapter(chapterId, number);
    }

    @Transactional
    public long save(Question question) {
        questionDAO.save(question);
        question.getAnswerList().forEach(answer -> {
            answer.setQuestionId(question.getId());
            answerDAO.save(answer);
        });
        return question.getId();
    }

    public boolean exist(int id) {
        return questionDAO.getQuestionById(id).isPresent();
    }

    @Transactional
    public long edit(Question question) {
        answerDAO.deleteAnswerByQuestionId(question.getId());
        return save(question);
    }
}
