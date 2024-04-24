package org.oxyl.persistence;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


import java.sql.Connection;

import static org.oxyl.persistence.UtilitairesDAO.getMaxID;
import static org.oxyl.persistence.UtilitairesDAO.getTotalPages;

public class UtilitairesDAOTest{

    @Test
    public void testGetMaxID() {
        System.setProperty("environment", "test");
        int id = getMaxID(MySqlConnexion.getInstance().getConnection());
        assertEquals(50, id);
    }

    @Test
    public void testGetTotalPages() {

        int pages = getTotalPages("intern", 10);
        assertEquals(5, pages);

    }
}