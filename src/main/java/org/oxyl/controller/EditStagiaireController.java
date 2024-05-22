package org.oxyl.controller;

import org.oxyl.mapper.MapperDate;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.service.PromotionService;
import org.oxyl.service.InternService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editStagiaire")
public class EditStagiaireController {

    private final PromotionService promotionService;
    private final InternService internService;
    private final MapperDate mapperDate;

    public EditStagiaireController(PromotionService promotionService, InternService internService, MapperDate mapperDate) {
        this.promotionService = promotionService;
        this.internService = internService;
        this.mapperDate = mapperDate;
    }

    @GetMapping
    public String setEdit(Model model, @RequestParam(value = "id") int id) {

        Optional<Stagiaire> optStagiaire = internService.detailStagiaire(id);
        if (optStagiaire.isPresent()) {
            Stagiaire stagiaire = optStagiaire.get();
            model.addAttribute("stagiaire", stagiaire);
        }

        List<Promotion> listPromo = promotionService.getAllPromotion();
        model.addAttribute("listPromo", listPromo);

        return "editStagiaire";
    }

    @PostMapping
    public String editStagiaire(@RequestParam(value = "id") String id,
                                @RequestParam(value = "lastName") String lastName,
                                @RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "arrival") String arrival,
                                @RequestParam(value = "finFormation", required = false) String finFormation,
                                @RequestParam(value = "promotion") String promo) {
        String[] promotion = promo.replace("[", "").replace("]", "").split(",");
        Promotion promotionObj = new Promotion.PromotionBuilder(Integer.parseInt(promotion[0]), promotion[1]).build();
        Stagiaire intern;
        if (!finFormation.isEmpty()) {
            intern = new Stagiaire.StagiaireBuilder(Integer.parseInt(id),
                    firstName,
                    lastName,
                    mapperDate.stringtoLocalDate(arrival)).formationOver(mapperDate.stringtoLocalDate(finFormation))
                    .promotion(promotionObj).build();
        } else {
            intern = new Stagiaire.StagiaireBuilder(Integer.parseInt(id),
                    firstName,
                    lastName,
                    mapperDate.stringtoLocalDate(arrival)).formationOver(mapperDate.stringtoLocalDate(finFormation))
                    .build();
        }
        internService.updateIntern(intern);
        return "redirect:/dashboard";
    }
}


