package org.oxyl.service.service;

import org.springframework.stereotype.Service;

@Service
public class UtilitairesService {

    public int getTotalPages(long count, long rowsPerPage) {
        return (int) Math.ceil((double) count / rowsPerPage);
    }

}
