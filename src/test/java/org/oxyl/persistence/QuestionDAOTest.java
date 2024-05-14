package org.oxyl.persistence;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class QuestionDAOTest extends TestCase {

    private final H2Config h2Config = H2Config.getInstance();
    private QuestionDAO questionDAO = QuestionDAO.getInstance();

    @BeforeEach
    public void setup(){
        h2Config.setup();
    }

    @Test
    public void testGetQuestion(){
        System.setProperty("environment", "test");
        questionDAO.getQuestionById(364);
    }

    @Test
    public void testDeleteQuestion(){
        System.setProperty("environment", "test");
        questionDAO.getQuestionById(365);
        questionDAO.deleteQuestion(364);
        questionDAO.getQuestionById(364);
    }
}