package org.oxyl.webapp.controller.rest;

import org.oxyl.bindings.dto.PromotionDTO;
import org.oxyl.bindings.mapper.MapperPromotion;
import org.oxyl.core.model.Promotion;
import org.oxyl.service.service.PromotionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotions")
public class PromotionRestController {

    private final PromotionService promotionService;
    private final MapperPromotion mapperPromotion;

    public PromotionRestController(PromotionService promotionService, MapperPromotion mapperPromotion) {
        this.promotionService = promotionService;
        this.mapperPromotion = mapperPromotion;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<List<PromotionDTO>> getPromo() {
        List<Promotion> listPromo = promotionService.getAllPromotion();
        return ResponseEntity.ok(listPromo.stream()
                .map(mapperPromotion::convertToPromotionDTO)
                .toList()
        );
    }
}
