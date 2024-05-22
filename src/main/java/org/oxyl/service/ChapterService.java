package org.oxyl.service;

import org.oxyl.model.Chapitre;
import org.oxyl.persistence.ChapterDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    public final ChapterDAO chapterDAO;

    public ChapterService(ChapterDAO chapterDAO) {
        this.chapterDAO = chapterDAO;
    }

    public Optional<List<Chapitre>> getAllChapter(){
        return chapterDAO.getAllChapter();
    }


}
