//package org.oxyl.persistence;
//
//import junit.framework.TestCase;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.oxyl.config.SpringApplicationContext;
//import org.oxyl.config.SpringCliContext;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//
//@Component
//public class QuestionDAOTest extends TestCase {
//
//    private final H2Config h2Config = H2Config.getInstance();
//    ApplicationContext applicationContext = SpringCliContext.getContext();
//    private final QuestionDAO questionDAO = applicationContext.getBean(QuestionDAO.class);
//
//
//    @BeforeEach
//    public void setup() {
//        h2Config.setup();
//    }
//
//    @Test
//    public void testGetQuestion() {
//        System.setProperty("environment", "test");
//        questionDAO.getQuestionById(364);
//    }
//
//    @Test
//    public void testDeleteQuestion() {
//        System.setProperty("environment", "test");
//        questionDAO.getQuestionById(365);
//        questionDAO.deleteQuestion(364);
//        questionDAO.getQuestionById(364);
//    }
//}