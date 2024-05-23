package org.oxyl.controller;

import org.oxyl.model.Stagiaire;
import org.oxyl.model.Page;
import org.oxyl.service.InternService;
import org.oxyl.service.UtilitairesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping(value ={"/dashboard", "feur"})
public class DashboardController {

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    private final InternService internService;
    private final UtilitairesService utilitairesService;

    public DashboardController(InternService internService, UtilitairesService utilitairesService) {
        this.internService = internService;
        this.utilitairesService = utilitairesService;
    }

    @GetMapping
    public String GetInternsPage(Model model,
                                 @RequestParam(value = "page", required = false) String pageParam,
                                 @RequestParam(value = "size", required = false) String pageTaille,
                                 @RequestParam(value = "search", required = false) String search,
                                 @RequestParam(value = "order", required = false) String order,
                                 @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrée dans le feur");

        Page<Stagiaire> page = new Page<>();
        if (pageParam != null) {
            try {
                page.setPageNumber(Integer.parseInt(pageParam));
            } catch (NumberFormatException e) {
                logger.error("Mauvais format de nombre pour la page", e);
            }
        }

        if (pageTaille != null && !pageTaille.isEmpty()) {
            page.setNbRow(Integer.parseInt(pageTaille));
        }

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

    @PostMapping
    public String delete(@RequestParam(value = "selection") String selection) {
        if (!selection.isEmpty()) {
            String[] idsToDelete = selection.split(",");
            for (String id : idsToDelete) {
                internService.deleteIntern(Integer.parseInt(id));
            }
        }
        return "redirect:/dashboard";
    }

}
