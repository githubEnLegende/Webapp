package org.oxyl.service;

import org.oxyl.persistence.UtilitairesDAO;
import org.springframework.stereotype.Service;

@Service
public class UtilitairesService {

    private final UtilitairesDAO utilitairesDAO;

    public UtilitairesService(UtilitairesDAO utilitairesDAO) {
        this.utilitairesDAO = utilitairesDAO;
    }

    public int getMaxID() {
        return utilitairesDAO.getMaxID();
    }

    public int getTotalPages(String table, int rowsPerPage) {
        return utilitairesDAO.getTotalPages(table, rowsPerPage);
    }

    public int getTotalPages(int count, int rowsPerPage) {
        return utilitairesDAO.getTotalPages(count, rowsPerPage);
    }

}
