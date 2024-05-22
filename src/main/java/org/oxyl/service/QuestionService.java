package org.oxyl.service;

import org.oxyl.model.Question;
import org.oxyl.persistence.QuestionDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionDAO questionDAO;

    public QuestionService(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }

    public void getQuestionById(int questionId){
        questionDAO.getQuestionById(questionId);
    }

    public void deleteQuestion(int id){
        questionDAO.deleteQuestion(id);
    }

    public Optional<List<Question>> getAllQuestion(){
        return questionDAO.getAllQuestion();
    }

    public List<String> getQuestionAnswer(int id){
        return questionDAO.getQuestionAnswer(id);
    }
}
