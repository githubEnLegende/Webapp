package org.oxyl.bindings.dto.questiondto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.oxyl.bindings.dto.answersDTO.AnswerDTO;
import org.oxyl.bindings.dto.validator.ValidAnswers;

import java.util.List;


public record QuestionEditDTO(
        @NotNull int id,
        @NotBlank String title,
        @NotBlank String statement,
        long chapterId,
        @NotEmpty @ValidAnswers List<AnswerDTO> answers
    ) {
}
