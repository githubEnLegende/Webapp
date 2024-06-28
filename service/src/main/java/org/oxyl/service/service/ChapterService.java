package org.oxyl.service.service;

import org.oxyl.core.model.Chapitre;
import org.oxyl.persistence.ChapterDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public record ChapterWithSubChapter(Chapitre chapter, ChapterWithSubChapter subChapter){ }

    @Transactional
    public Map<Chapitre, List<Chapitre>> getSubChapter(long id) {
        var chapter = chapterDAO.getChapterById(id);
        if(chapter.isEmpty()){
            return new HashMap<>();
        }
        var subChapter = chapterDAO.getSubchapterOfChapter(chapter.get());
        var allChapter = new ArrayList<>(subChapter);
        allChapter.add(chapter.get());
        var map = new HashMap<Chapitre, List<Chapitre>>();
        subChapter.forEach(chapitre -> {
            var key = allChapter.stream()
                    .filter(x -> (x.getParent() + x.getName() + "/").equals(chapitre.getParent()))
                    .findFirst()
                    .orElseThrow();
            map.computeIfAbsent(key, x -> new ArrayList<>());
            map.get(key).add(chapitre);
        });
        return map;
    }
}
