package tn.esprit.planstrategicmicroservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tn.esprit.planstrategicmicroservice.entities.ObjectifStra;
import tn.esprit.planstrategicmicroservice.entities.PlanStrategique;
import tn.esprit.planstrategicmicroservice.services.PlanStrategiqueIService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/PlanStrategic")
@Transactional
public class PlanStrategiqueController {

    @Autowired
    PlanStrategiqueIService planStrategiqueIService ;

    @PostMapping("/create")
    public PlanStrategique createPlan(@RequestBody PlanStrategique planStrategique ) {
        return planStrategiqueIService.addPlan(planStrategique) ;
    }

    @GetMapping("/getPlan/{id}")
    public PlanStrategique getPlan(@PathVariable Long id , @RequestBody Date date) {
        return planStrategiqueIService.findByIdAndDate(id, date) ;
    }

    @PostMapping("/{planStrategiqueId}/add-objectifs")
    public ResponseEntity<PlanStrategique> addObjectifsToPlanStrategique(
            @PathVariable Long planStrategiqueId,
            @RequestBody List<ObjectifStra> objectifs) {
        PlanStrategique updatedPlanStrategique = planStrategiqueIService.addObjectifsToPlanStrategique(planStrategiqueId, objectifs);
        return ResponseEntity.ok(updatedPlanStrategique);
    }

    @GetMapping("/all-with-objectifs")
    public ResponseEntity<List<PlanStrategique>> getAllPlanStrategiquesWithObjectifs() {
        List<PlanStrategique> planStrategiques = planStrategiqueIService.getAllPlanStrategiquesWithObjectifs();
        return ResponseEntity.ok(planStrategiques);
    }

    @PostMapping("/create-for-entreprise")
    public ResponseEntity<PlanStrategique> createPlanStrategiqueForEntreprise(
            @RequestParam Long entrepriseId,
            @RequestBody PlanStrategique planStrategique
    ) {
        PlanStrategique createdPlanStrategique = planStrategiqueIService.createPlanStrategiqueForEntreprise(entrepriseId, planStrategique);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlanStrategique);
    }

    @GetMapping("/all-for-entreprise")
    public ResponseEntity<List<PlanStrategique>> getAllPlansForEntreprise(
            @RequestParam String nom
    ) {
        List<PlanStrategique> plans = planStrategiqueIService.getAllPlansForEntreprise(nom);
        return ResponseEntity.ok(plans);
    }
}
