package org.oxyl.controller;

import org.oxyl.mapper.MapperDate;
import org.oxyl.model.Promotion;
import org.oxyl.model.Stagiaire;
import org.oxyl.model.Page;
import org.oxyl.service.InternService;
import org.oxyl.service.PromotionService;
import org.oxyl.service.UtilitairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class InternController {

    private static final Logger logger = LoggerFactory.getLogger(InternController.class);

    private final MapperDate mapperDate;
    private final PromotionService promotionService;
    private final InternService internService;
    private final UtilitairesService utilitairesService;

    public InternController(InternService internService, UtilitairesService utilitairesService,
                            PromotionService promotionService, MapperDate mapperDate) {
        this.internService = internService;
        this.utilitairesService = utilitairesService;
        this.promotionService = promotionService;
        this.mapperDate = mapperDate;
    }

    @GetMapping("/dashboard")
    public String GetInternsPage(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") int pageParam,
                                 @RequestParam(value = "size", defaultValue = "50") int pageTaille,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "order", required = false) String order,
                                 @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrée dans le feur");

        Page<Stagiaire> page = new Page<>();
        page.setPageNumber(pageParam);
        page.setNbRow(pageTaille);

        page.setOrder(SecureOrder.inEnum(order));
        int countStagiaire = internService.countStagiaire();
        model.addAttribute("countStagiaire", countStagiaire);

        int totalPages = 0;
        if (search != null && !search.isEmpty()) {
            int countSize = internService.getPageStagiaire(search, page);
            model.addAttribute("countStagiaire", countSize);
            totalPages = utilitairesService.getTotalPages(countSize, page.getNbRow());

        } else {
            internService.getPageStagiaire(page);
            totalPages = utilitairesService.getTotalPages("intern", page.getNbRow());
        }

        model.addAttribute("size", page.getNbRow());
        model.addAttribute("page", page.getPageNumber());
        model.addAttribute("order", page.getOrder());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("stagiaires", page.getStagiaires());
        model.addAttribute("search", search);
        model.addAttribute("lang", lang);
        return "dashboard";
    }

    @Transactional
    @PostMapping("/dashboard")
    public String delete(@RequestParam(value = "selection") String selection) {
        if (!selection.isEmpty()) {
            String[] idsToDelete = selection.split(",");
            for (String id : idsToDelete) {
                internService.deleteIntern(Integer.parseInt(id));
            }
        }
        return "redirect:/dashboard";
    }


    @GetMapping("/addStagiaire")
    public String getPromo(Model model) {
        List<Promotion> listPromo = promotionService.getAllPromotion();
        model.addAttribute("listPromo", listPromo);
        return "addStagiaire";
    }

    @PostMapping("/addStagiaire")
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

    @GetMapping("/{id}")
    public String setEdit(Model model, @PathVariable(value = "id") int id) {

        Optional<Stagiaire> optStagiaire = internService.detailStagiaire(id);
        if (optStagiaire.isPresent()) {
            Stagiaire stagiaire = optStagiaire.get();
            model.addAttribute("stagiaire", stagiaire);
        }else{
            return "redirect:/404";
        }

        List<Promotion> listPromo = promotionService.getAllPromotion();
        model.addAttribute("listPromo", listPromo);

        return "editStagiaire";
    }

    @PostMapping("/{id}")
    public String editStagiaire(@PathVariable(value = "id") int id,
                                @RequestParam(value = "lastName") String lastName,
                                @RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "arrival") String arrival,
                                @RequestParam(value = "finFormation", required = false) String finFormation,
                                @RequestParam(value = "promotion") String promo) {

        String[] promotion = promo.replace("[", "").replace("]", "").split(",");
        Promotion promotionObj = new Promotion.PromotionBuilder(Integer.parseInt(promotion[0]), promotion[1]).build();
        System.out.println(id);
        Stagiaire intern;
        if (!finFormation.isEmpty()) {
            intern = new Stagiaire.StagiaireBuilder(id,
                    firstName,
                    lastName,
                    mapperDate.stringtoLocalDate(arrival)).formationOver(mapperDate.stringtoLocalDate(finFormation))
                    .promotion(promotionObj).build();
        } else {
            intern = new Stagiaire.StagiaireBuilder(id,
                    firstName,
                    lastName,
                    mapperDate.stringtoLocalDate(arrival)).promotion(promotionObj).build();
        }
        internService.updateIntern(intern);
        return "redirect:/dashboard";
    }

}
