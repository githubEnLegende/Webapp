package org.oxyl.bindings.dto.stagiairedto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record StagiaireDTOPage(
        @NotNull(message = "L'ID est OBLIGATOIRE")
        long id,


        @NotEmpty(message = "Le lastName est obligatoire")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s'-]+$", message = "le lastName contient des caractères non autorisés")
        String nom,

        @NotEmpty(message = "Le prénom est obligatoire")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s'-]+$", message = "le prénom contient des caractères non autorisés")
        String prenom,

        @NotNull(message = "la date d'arrivée est obligatoire")
        String arrival,
        String finFormation,

        @NotNull(message = "la promotion est obligatoire")
        String promotionName) {
}
