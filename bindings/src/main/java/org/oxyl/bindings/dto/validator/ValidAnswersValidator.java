package org.oxyl.bindings.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.oxyl.bindings.dto.answersDTO.AnswerDTO;
import org.oxyl.bindings.dto.questiondto.QuestionAddDTO;

import java.util.List;

public class ValidAnswersValidator implements ConstraintValidator<ValidAnswers, List<AnswerDTO>> {

    @Override
    public boolean isValid(List<AnswerDTO> answers, ConstraintValidatorContext context) {
        if(answers == null) {
            return false;
        }
        return answers.stream().anyMatch(answerDTO -> answerDTO.validAnswer() == 1);
    }
}
