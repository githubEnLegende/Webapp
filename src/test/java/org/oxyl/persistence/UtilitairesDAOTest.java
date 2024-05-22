//package org.oxyl.persistence;
//
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//
//import static org.junit.Assert.assertEquals;
//import org.springframework.context.ApplicationContext;
//
//
//public class UtilitairesDAOTest{
//
//
//    private ApplicationContext context = Context.getInstance().getContext();
//    private UtilitairesDAO utilitairesDAO = context.getBean(UtilitairesDAO.class);
//
//    private H2Config h2Config = H2Config.getInstance();
//
//    @BeforeEach
//    public void setup(){
//        h2Config.setup();
//    }
//
//    @Test
//    public void testGetMaxID() {
//        System.setProperty("environment", "test");
//        int id = utilitairesDAO.getMaxID();
//        assertEquals(50, id);
//    }
//
//    @Test
//    public void testGetTotalPages() {
//        System.setProperty("environment", "test");
//        int pages = utilitairesDAO.getTotalPages("intern", 10);
//        assertEquals(5, pages);
//
//    }
//}