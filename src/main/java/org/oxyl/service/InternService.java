package org.oxyl.service;

import org.oxyl.model.Page;
import org.oxyl.model.Stagiaire;
import org.oxyl.persistence.StagiaireDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {

    private final StagiaireDAO stagiaireDAO;

    public InternService(StagiaireDAO stagiaireDAO) {
        this.stagiaireDAO = stagiaireDAO;
    }

    public long countStagiaire() {
        return stagiaireDAO.countStagiaire();
    }

    public Optional<List<Stagiaire>> getAllStagiaire() {
        return stagiaireDAO.getAllStagiaires();
    }

    public void getPageStagiaire(Page<Stagiaire> page) {
        stagiaireDAO.getPageStagiaire(page);
    }

    public long getPageStagiaire(String name, Page<Stagiaire> page) {
        return stagiaireDAO.getPageStagiaire(name, page);
    }

    public Optional<Stagiaire> detailStagiaire(long id) {
        return stagiaireDAO.detailStagiaire(id);
    }

    public void insertIntern(Stagiaire intern) {
        stagiaireDAO.insertIntern(intern);
    }

    public void deleteIntern(long id) {
        stagiaireDAO.deleteIntern(id);
    }

    public void updateIntern(Stagiaire intern) {
        stagiaireDAO.updateIntern(intern);
    }

}
