package org.oxyl.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.oxyl.core.model.Chapitre;
import org.oxyl.persistence.entities.ChapterEntity;
import org.oxyl.persistence.entities.QuestionEntity;
import org.oxyl.persistence.entitymapper.ChapitreEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

            return query.list().stream().map(mapperChapitre::toModel).toList();



        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de tous les chapitres", e);
            return null;
        }
    }

    @Transactional
    public List<Chapitre> getRootChapter() {
        try {
            Query<ChapterEntity> query = session.createQuery("from ChapterEntity where parentPath LIKE '/'", ChapterEntity.class);
            return query.list().stream().map(mapperChapitre::toModel).toList();
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de tous les chapitres", e);
            throw new IllegalStateException(e);
        }
    }

    public Optional<Chapitre> getChapterById(long id) {
        try {
            var entity = session.get(ChapterEntity.class, id);
            if(entity == null) {
                return Optional.empty();
            }
            return Optional.of(mapperChapitre.toModel(entity));
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de tous les chapitres", e);
            throw new IllegalStateException(e);
        }
    }

    public List<Chapitre> getSubchapterOfChapter(Chapitre chapter) {
        try {
            Query<ChapterEntity> query = session.createQuery("from ChapterEntity where parentPath LIKE :path", ChapterEntity.class);
            return query.setParameter("path",  "/" + chapter.getName() + "/%").list().stream().map(mapperChapitre::toModel).toList();
        } catch (HibernateException e) {
            logger.error("Erreur lors de la récupération de tous les chapitres", e);
            throw new IllegalStateException(e);
        }
    }
}
