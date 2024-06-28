package org.oxyl.webapp.controller.rest;

import jakarta.validation.Valid;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOAdd;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOEdit;
import org.oxyl.bindings.dto.stagiairedto.StagiaireDTOPage;
import org.oxyl.bindings.mapper.MapperStagiaire;
import org.oxyl.core.model.Page;
import org.oxyl.core.model.Stagiaire;
import org.oxyl.service.service.InternService;
import org.oxyl.service.service.UtilitairesService;
import org.oxyl.webapp.controller.SecureOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interns")
public class InternRestController {

    private static final Logger logger = LoggerFactory.getLogger(InternRestController.class);

    private final InternService internService;
    private final UtilitairesService utilitairesService;
    private final MapperStagiaire mapperStagiaire;

    public InternRestController(InternService internService, UtilitairesService utilitairesService,
                                MapperStagiaire mapperStagiaire) {
        this.internService = internService;
        this.utilitairesService = utilitairesService;
        this.mapperStagiaire = mapperStagiaire;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<StagiaireDTOPage>> GetInternsPage(@RequestParam(value = "page", defaultValue = "1") long pageParam,
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
            internService.getPageStagiaire(search, page);
            var countSize = page.getCount();
            page.setTotalPages(utilitairesService.getTotalPages(countSize, page.getNbRow()));

        } else {
            internService.getPageStagiaire(page);
            page.setTotalPages(internService.getTotalPages(page.getNbRow()));
        }
        return ResponseEntity.ok(page.map(mapperStagiaire::modelToDtoPage));
    }

    @GetMapping(value = {"/{id}"}, produces = "application/json")
    public ResponseEntity<StagiaireDTOEdit> getInternID(@PathVariable(value = "id") long id) {
        var stagiaire = internService.detailStagiaire(id);
        return stagiaire.map(value ->
                ResponseEntity.ok(mapperStagiaire.modelToDtoEdit(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping(consumes = "application/json")
    public ResponseEntity<StagiaireDTOEdit> addStagiaire(@Valid @RequestBody StagiaireDTOAdd stagiaireDTOAdd) {
        System.out.println(stagiaireDTOAdd);
        Stagiaire stagiaire = mapperStagiaire.dtoAddToModel(stagiaireDTOAdd);
        internService.insertIntern(stagiaire);
        return ResponseEntity.status(201).body(mapperStagiaire.modelToDtoEdit(stagiaire));

    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<StagiaireDTOEdit> editStagiaire(@Valid @RequestBody StagiaireDTOEdit stagiaireDTOEdit) {
        Stagiaire stagiaire = mapperStagiaire.dtoEditToModel(stagiaireDTOEdit);
        internService.updateIntern(stagiaire);
        return ResponseEntity.ok(mapperStagiaire.modelToDtoEdit(stagiaire));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable(value = "id") long id) {
        internService.deleteIntern(id);
        return ResponseEntity.noContent().build();
    }
}
