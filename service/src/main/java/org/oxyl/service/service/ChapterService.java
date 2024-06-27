package org.oxyl.service.service;

import org.oxyl.core.model.Chapitre;
import org.oxyl.persistence.ChapterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    public static final Logger logger = LoggerFactory.getLogger(ChapterService.class);
    public final ChapterDAO chapterDAO;

    public ChapterService(ChapterDAO chapterDAO) {
        this.chapterDAO = chapterDAO;
    }

    public List<Chapitre> getAllChapter() {
        logger.info("getAllChapter");
        return chapterDAO.getAllChapter();
    }


    public List<Chapitre> getRootChapter() {
        return chapterDAO.getRootChapter();

    }
}
