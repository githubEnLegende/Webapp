package org.oxyl.persistence;

import org.hibernate.HibernateException;
import org.oxyl.persistence.entities.InternEntity;
import org.oxyl.persistence.entitymapper.StagiaireEntityMapper;
import org.oxyl.core.model.Page;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.persistence.repository.StagiaireRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class StagiaireDAO {

    private static final Logger logger = LoggerFactory.getLogger(StagiaireDAO.class);
    private final StagiaireEntityMapper mapperStagiaire;
    private final StagiaireRepository stagiaireRepository;

    public StagiaireDAO(StagiaireEntityMapper mapperStagiaire, StagiaireRepository stagiaireRepository) {
        this.mapperStagiaire = mapperStagiaire;
        this.stagiaireRepository = stagiaireRepository;
    }

    public long countStagiaire() {
        try {
            return stagiaireRepository.count();
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération du nombre total de stagiaires", e);
            return 0;
        }
    }

    public Optional<List<Stagiaire>> getAllStagiaires() {
        try {
            List<InternEntity> internEntities = stagiaireRepository.findAll();
            List<Stagiaire> stagiaires = internEntities.stream()
                    .map(mapperStagiaire::toModel)
                    .collect(Collectors.toList());
            return Optional.of(stagiaires);
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de tous les stagiaires", e);
            return Optional.empty();
        }
    }

    @Transactional
    public void getPageStagiaire(Page<Stagiaire> page) {
        Pageable pageable = PageRequest.of((int) (page.getPageNumber() - 1), (int) page.getNbRow(), Sort.by(Sort.Direction.ASC, page.getOrder()));
        try {
            List<Stagiaire> stagiaires = stagiaireRepository.findAll(pageable)
                    .getContent().stream().map(mapperStagiaire::toModel).toList();
            page.setContent(stagiaires);
        } catch (HibernateException e) {
            logger.error("Erreur lors de l'affichage de la page des stagiaires.", e);
        }

    }

    @Transactional
    public long getPageStagiaire(String name, Page<Stagiaire> page) {
        Pageable pageable = PageRequest.of((int) (page.getPageNumber() - 1), (int) page.getNbRow(), Sort.by(Sort.Direction.ASC, page.getOrder()));
        try {
            List<Stagiaire> stagiaires = stagiaireRepository.findAllByFirstNameOrLastNameContainsIgnoreCaseWithPromotion(name, pageable)
                    .getContent().stream().map(mapperStagiaire::toModel).toList();
            page.setContent(stagiaires);
            return stagiaireRepository.countByFirstNameOrLastNameContainsIgnoreCase(name);
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de la page des stagiaires avec le nom spécifié", e);
            return 0;
        }
    }

    @Transactional
    public Optional<Stagiaire> detailStagiaire(long id) {
        try {
            Optional<InternEntity> internEntity = stagiaireRepository.findById(id);
            return internEntity.map(mapperStagiaire::toModel);
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération du détail du stagiaire", e);
            return Optional.empty();
        }
    }

    public void insertIntern(Stagiaire intern) {
        try {
            InternEntity internEntity = mapperStagiaire.toEntity(intern);
            stagiaireRepository.save(internEntity);
            System.out.println("Stagiaire inséré avec succès");
        } catch (HibernateException e) {
            logger.error("Probleme lors de l'insertion d'un stagiaire", e);
        }
    }

    public void deleteIntern(long id) {
        try {
            stagiaireRepository.deleteById(id);
            System.out.println("Stagiaire supprimé avec succès !");
        } catch (HibernateException e) {
            logger.error("Erreur lors de la suppression d'un stagiaire", e);
        }
    }

    public void updateIntern(Stagiaire intern) {
        try {
            InternEntity internEntity = mapperStagiaire.toEntity(intern);
            stagiaireRepository.save(internEntity);
        } catch (HibernateException e) {
            logger.error("Erreur SQL lors de la mise à jour d'un stagiaire", e);
        }
    }

    public long getMaxID() {
        try {
            return stagiaireRepository.findMaxId();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du max ID", e);
            return 0;
        }
    }

    public long getTotalPages(long rowsPerPage) {
        try {
            long totalRows = stagiaireRepository.count();
            return (int) Math.ceil((double) totalRows / rowsPerPage);
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération du nombre total de pages", e);
            return 0;
        }
    }


}
