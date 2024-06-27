package org.oxyl.service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UtilitairesService {

    private static final Logger logger = LoggerFactory.getLogger(UtilitairesService.class);

    public int getTotalPages(long count, long rowsPerPage) {
        logger.info("getTotalPages");
        return (int) Math.ceil((double) count / rowsPerPage);
    }

}
