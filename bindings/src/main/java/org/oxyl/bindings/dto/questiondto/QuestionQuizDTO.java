package org.oxyl.bindings.dto.questiondto;

import org.oxyl.bindings.dto.answersDTO.AnswerDTO;

import java.util.List;

public record QuestionQuizDTO(String title, String statement, List<AnswerDTO> answers) { }
