package org.oxyl.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.oxyl.core.model.Chapitre;
import org.oxyl.persistence.entities.ChapterEntity;
import org.oxyl.persistence.entitymapper.ChapitreEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ChapterDAO {

    private static final Logger logger = LoggerFactory.getLogger(ChapterDAO.class);
    private final Session session;
    private final ChapitreEntityMapper mapperChapitre;


    public ChapterDAO(Session session, ChapitreEntityMapper mapperChapitre) {
        this.mapperChapitre = mapperChapitre;
        this.session = session;
    }

    @Transactional
    public List<Chapitre> getAllChapter() {
        try {
            Query<ChapterEntity> query = session.createQuery("from ChapterEntity", ChapterEntity.class);

            List<Chapitre> chapitreList = query.list().stream().map(mapperChapitre::toModel).toList();
            if (!chapitreList.isEmpty()) {
                return chapitreList;
            } else {
                return null;
            }



        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de tous les chapitres", e);
            return null;
        }
    }
}
