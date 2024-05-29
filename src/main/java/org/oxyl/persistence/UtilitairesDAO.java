package org.oxyl.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UtilitairesDAO {

    private final static Logger logger = LoggerFactory.getLogger(UtilitairesDAO.class);


    public UtilitairesDAO() {
    }

    public int getTotalPages(long count, long rowsPerPage) {
        return (int) Math.ceil((double) count / rowsPerPage);
    }
}
