package org.oxyl.service;

import org.oxyl.persistence.UtilitairesDAO;
import org.springframework.stereotype.Service;

@Service
public class UtilitairesService {

    private final UtilitairesDAO utilitairesDAO;

    public UtilitairesService(UtilitairesDAO utilitairesDAO) {
        this.utilitairesDAO = utilitairesDAO;
    }

    public long getMaxID() {
        return utilitairesDAO.getMaxID();
    }

    public long getTotalPages(String table, long rowsPerPage) {
        return utilitairesDAO.getTotalPages(table, rowsPerPage);
    }

    public long getTotalPages(long count, long rowsPerPage) {
        return utilitairesDAO.getTotalPages(count, rowsPerPage);
    }

}
