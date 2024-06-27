package org.oxyl.service.service;

import org.oxyl.core.model.Page;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.persistence.StagiaireDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {

    public static final Logger logger = LoggerFactory.getLogger(InternService.class);
    private final StagiaireDAO stagiaireDAO;

    public InternService(StagiaireDAO stagiaireDAO) {
        this.stagiaireDAO = stagiaireDAO;
    }

    public long countStagiaire() {
        logger.info("countStagiaire");
        return stagiaireDAO.countStagiaire();
    }

    public List<Stagiaire> getAllStagiaire() {
        logger.info("getAllStagiaire");
        return stagiaireDAO.getAllStagiaires();
    }

    public void getPageStagiaire(Page<Stagiaire> page) {
        logger.info("getPageStagiaire");
        stagiaireDAO.getPageStagiaire(page);
    }

    public long getPageStagiaire(String name, Page<Stagiaire> page) {
        logger.info("getPageStagiaire search");
        return stagiaireDAO.getPageStagiaire(name, page);
    }

    public Optional<Stagiaire> detailStagiaire(long id) {
        logger.info("detailStagiaire");
        return stagiaireDAO.detailStagiaire(id);

    }

    public void insertIntern(Stagiaire intern) {
        logger.info("insertIntern");
        stagiaireDAO.insertIntern(intern);
    }

    public void deleteIntern(long id) {
        logger.info("deleteIntern");
        stagiaireDAO.deleteIntern(id);
    }

    public void updateIntern(Stagiaire intern) {
        logger.info("updateIntern");
        stagiaireDAO.updateIntern(intern);
    }

    public long getMaxID() {
        logger.info("getMaxID");
        return stagiaireDAO.getMaxID();
    }

    public long getTotalPages(long rowsPerPage) {
        logger.info("getTotalPages");
        return stagiaireDAO.getTotalPages(rowsPerPage);
    }

}
