package org.oxyl.controller;

import org.oxyl.mapper.MapperDate;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.service.InternService;
import org.oxyl.service.PromotionService;
import org.oxyl.service.UtilitairesService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/addStagiaire")
public class AddStagiaireController {

    private final MapperDate mapperDate;
    private final PromotionService promotionService;
    private final InternService internService;
    private final UtilitairesService utilitairesService;

    public AddStagiaireController(MapperDate mapperDate, PromotionService promotionService,
                                  InternService internService, UtilitairesService utilitairesService) {
        this.mapperDate = mapperDate;
        this.promotionService = promotionService;
        this.internService = internService;
        this.utilitairesService = utilitairesService;
    }

    @GetMapping
    public String getPromo(Model model) {
        List<Promotion> listPromo = promotionService.getAllPromotion();
        model.addAttribute("listPromo", listPromo);
        return "addStagiaire";
    }

    @PostMapping
    public String addStagiaire(@RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "arrival") String arrival,
                               @RequestParam(required = false, name = "finFormation") String finFormation,
                               @RequestParam(value = "promotionId") String promotionId) {

        Promotion promotion = new Promotion.PromotionBuilder(Integer.parseInt(promotionId), promotionId).build();
        Stagiaire intern;
        if (!finFormation.isEmpty()) {
            LocalDate finFormationDate = mapperDate.stringtoLocalDate(finFormation);
            intern = new Stagiaire.StagiaireBuilder(utilitairesService.getMaxID() + 1,
                    firstName, lastName, LocalDate.parse(arrival))
                    .formationOver(finFormationDate)
                    .promotion(promotion).build();
        } else {
            intern = new Stagiaire.StagiaireBuilder(utilitairesService.getMaxID() + 1,
                    firstName, lastName, LocalDate.parse(arrival)).promotion(promotion).build();
        }
        internService.insertIntern(intern);
        return "redirect:/dashboard";
    }

}
