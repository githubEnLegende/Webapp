package org.oxyl.webapp.controller;

import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOAdd;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOEdit;
import org.oxyl.bindings.mapper.MapperStagiaire;
import org.oxyl.core.model.Page;
import org.oxyl.core.model.Promotion;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.service.service.InternService;
import org.oxyl.service.service.PromotionService;
import org.oxyl.service.service.UtilitairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class InternController {

    private static final Logger logger = LoggerFactory.getLogger(InternController.class);

    private final PromotionService promotionService;
    private final InternService internService;
    private final UtilitairesService utilitairesService;
    private final MapperStagiaire mapperStagiaire;

    public InternController(InternService internService, UtilitairesService utilitairesService,
                            PromotionService promotionService, MapperStagiaire mapperStagiaire) {
        this.internService = internService;
        this.utilitairesService = utilitairesService;
        this.promotionService = promotionService;
        this.mapperStagiaire = mapperStagiaire;
    }

    @GetMapping({"/dashboard", "/"})
    public String GetInternsPage(Model model,
                                 @RequestParam(value = "page", defaultValue = "1") long pageParam,
                                 @RequestParam(value = "size", defaultValue = "50") long pageTaille,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "order", required = false) String order,
                                 @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrée dans le feur");

        Page<Stagiaire> page = new Page<>();
        page.setPageNumber(pageParam);
        page.setNbRow(pageTaille);

        page.setOrder(SecureOrder.inEnum(order));
        long countStagiaire = internService.countStagiaire();
        model.addAttribute("countStagiaire", countStagiaire);

        long totalPages = 0;
        if (search != null && !search.isEmpty()) {
            long countSize = internService.getPageStagiaire(search, page);
            model.addAttribute("countStagiaire", countSize);
            totalPages = utilitairesService.getTotalPages(countSize, page.getNbRow());

        } else {
            internService.getPageStagiaire(page);
            totalPages = internService.getTotalPages(page.getNbRow());
        }


        model.addAttribute("size", page.getNbRow());
        model.addAttribute("page", page.getPageNumber());
        model.addAttribute("order", page.getOrder());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("stagiaires", mapperStagiaire.listModelToListDtoPage(page.getContent()));
        model.addAttribute("search", search);
        model.addAttribute("lang", lang);
        return "dashboard";
    }

    @PostMapping({"/dashboard", "/"})
    public String delete(@RequestParam(value = "selection") String selection) {
        if (!selection.isEmpty()) {
            String[] idsToDelete = selection.split(",");
            for (String id : idsToDelete) {
                internService.deleteIntern(Long.parseLong(id));
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
                               @RequestParam(value = "promotionId") String promo) {

        String[] promotion = promo.replace("[", "").replace("]", "").split(",");
        StagiaireDTOAdd intern = new StagiaireDTOAdd(lastName, firstName, arrival, finFormation, promotion[0], promotion[1]);
        System.out.println(intern);
        internService.insertIntern(mapperStagiaire.dtoAddToModel(intern));
        return "redirect:/dashboard";
    }

    @GetMapping("/{id}")
    public String setEdit(Model model, @PathVariable(value = "id") long id) {

        Stagiaire stagiaire = internService.detailStagiaire(id);
        if (stagiaire != null) {
            model.addAttribute("stagiaire", mapperStagiaire.modelToDtoAdd(stagiaire));
        } else {
            return "redirect:/404";
        }

        List<Promotion> listPromo = promotionService.getAllPromotion();
        model.addAttribute("listPromo", listPromo);

        return "editStagiaire";
    }

    @PostMapping("/{id}")
    public String editStagiaire(@PathVariable(value = "id") long id,
                                @RequestParam(value = "lastName") String lastName,
                                @RequestParam(value = "firstName") String firstName,
                                @RequestParam(value = "arrival") String arrival,
                                @RequestParam(value = "finFormation", required = false) String finFormation,
                                @RequestParam(value = "promotion") String promo) {

        String[] promotion = promo.replace("[", "").replace("]", "").split(",");
        StagiaireDTOEdit intern = new StagiaireDTOEdit(id, firstName, lastName, arrival, finFormation, promotion[0], promotion[1]);
        internService.updateIntern(mapperStagiaire.dtoEditToModel(intern));
        return "redirect:/dashboard";
    }

}
