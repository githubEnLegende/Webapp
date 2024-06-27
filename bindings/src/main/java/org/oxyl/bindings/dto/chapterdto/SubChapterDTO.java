package org.oxyl.bindings.dto.chapterdto;

import java.util.List;

public record SubChapterDTO(ChapterIdNameDTO name, List<SubChapterDTO> subChapters) { }
