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
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stagiaires")
public class InternRestController {

    private static final Logger logger = LoggerFactory.getLogger(InternRestController.class);

    private final PromotionService promotionService;
    private final InternService internService;
    private final UtilitairesService utilitairesService;
    private final MapperStagiaire mapperStagiaire;

    public InternRestController(InternService internService, UtilitairesService utilitairesService,
                                PromotionService promotionService, MapperStagiaire mapperStagiaire) {
        this.internService = internService;
        this.utilitairesService = utilitairesService;
        this.promotionService = promotionService;
        this.mapperStagiaire = mapperStagiaire;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<Stagiaire>> GetInternsPage(@RequestParam(value = "page", defaultValue = "1") long pageParam,
                                         @RequestParam(value = "size", defaultValue = "50") long pageTaille,
                                         @RequestParam(value = "search", required = false) String search,
                                         @RequestParam(value = "order", required = false) String order,
                                         @RequestParam(value = "lang", required = false) String lang) {
        logger.info("Entrée dans le feur");

        Page<Stagiaire> page = new Page<>();
        page.setPageNumber(pageParam);
        page.setNbRow(pageTaille);
        page.setOrder(SecureOrder.inEnum(order));

        if (search != null && !search.isEmpty()) {
            long countSize = internService.getPageStagiaire(search, page);

        } else {
            internService.getPageStagiaire(page);
        }
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = {"/{id}"}, produces = "application/json")
    public ResponseEntity<Stagiaire> getInternID(@PathVariable(value = "id") long id) {
        return ResponseEntity.ok(internService.detailStagiaire(id));
    }

    @GetMapping(value = "/promotions", produces = "application/json")
    public ResponseEntity<List<Promotion>> getPromo() {
        List<Promotion> listPromo = promotionService.getAllPromotion();
        return ResponseEntity.ok(listPromo);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Stagiaire> addStagiaire(@RequestBody StagiaireDTOAdd stagiaireDTOAdd) {
        try{
            Stagiaire stagiaire = mapperStagiaire.dtoAddToModel(stagiaireDTOAdd);
            internService.insertIntern(stagiaire);
            return ResponseEntity.ok(stagiaire);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Stagiaire> editStagiaire(@PathVariable(value = "id") long id,
                                                   @RequestBody StagiaireDTOEdit stagiaireDTOEdit){
        try {
            Stagiaire stagiaire = mapperStagiaire.dtoEditToModel(stagiaireDTOEdit);
            internService.updateIntern(stagiaire);
            return ResponseEntity.ok(stagiaire);
        }catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Stagiaire> deleteIntern(@PathVariable(value = "id") long id) {
        internService.deleteIntern(id);
        return ResponseEntity.noContent().build();
    }
}
