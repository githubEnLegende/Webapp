package org.oxyl.bindings.dto.questiondto;

import org.oxyl.bindings.dto.answersDTO.AnswerDTO;
import org.oxyl.bindings.dto.chapterdto.ChapterDTO;

import java.util.List;

public record QuestionInformationDTO(
    long id,
    String title,
    String statement,
    ChapterDTO chapterDTO,
    List<AnswerDTO> answers
) { }
