package org.oxyl.persistence;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


import java.sql.Connection;


public class UtilitairesDAOTest{

    private UtilitairesDAO utilitairesDAO;
    @Test
    public void testGetMaxID() {
        System.setProperty("environment", "test");
        int id = utilitairesDAO.getMaxID();
        assertEquals(50, id);
    }

    @Test
    public void testGetTotalPages() {
        System.setProperty("environment", "test");
        int pages = utilitairesDAO.getTotalPages("intern", 10);
        assertEquals(5, pages);

    }
}