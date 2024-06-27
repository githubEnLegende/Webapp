package org.oxyl.persistence.entitymapper;

import org.oxyl.core.model.Chapitre;
import org.oxyl.persistence.entities.ChapterEntity;
import org.springframework.stereotype.Component;

@Component
public class ChapitreEntityMapper {
    public Chapitre toModel(ChapterEntity chapterEntity) {
        return new Chapitre.ChapitreBuilder(
                chapterEntity.getId(),
                chapterEntity.getName(),
                chapterEntity.getParentPath()
        ).build();
    }
}
