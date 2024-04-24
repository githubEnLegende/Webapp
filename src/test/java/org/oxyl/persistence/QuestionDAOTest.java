package org.oxyl.persistence;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.oxyl.persistence.QuestionDAO.getQuestionById;

public class QuestionDAOTest extends TestCase {

    private final H2Config h2Config = H2Config.getInstance();

    @BeforeEach
    public void setup(){
        h2Config.setup();
    }

    @Test
    public void testGetQuestion(){
        System.setProperty("environment", "test");
        getQuestionById(364);
    }
}