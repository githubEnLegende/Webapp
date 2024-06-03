package org.oxyl.service.service;

import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOEditAdd;
import org.oxyl.bindings.mapper.MapperStagiaire;
import org.oxyl.core.model.Page;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.persistence.StagiaireDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternService {

    private final StagiaireDAO stagiaireDAO;
    private final MapperStagiaire mapperStagiaire;

    public InternService(StagiaireDAO stagiaireDAO, MapperStagiaire mapperStagiaire) {
        this.stagiaireDAO = stagiaireDAO;
        this.mapperStagiaire = mapperStagiaire;
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

    public Stagiaire detailStagiaire(long id) {
        return stagiaireDAO.detailStagiaire(id).orElse(null);

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

    public long getMaxID() {
        return stagiaireDAO.getMaxID();
    }

    public long getTotalPages(long rowsPerPage) {
        return stagiaireDAO.getTotalPages(rowsPerPage);
    }

}
