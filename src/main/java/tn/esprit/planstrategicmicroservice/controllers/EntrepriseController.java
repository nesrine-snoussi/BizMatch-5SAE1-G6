package tn.esprit.planstrategicmicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.planstrategicmicroservice.entities.Entreprise;
import tn.esprit.planstrategicmicroservice.services.EntrepriseIService;

@RestController
@RequestMapping("/entreprise")
public class EntrepriseController {

    @Autowired
    private EntrepriseIService entrepriseIService;

    @PostMapping("/add")
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise) {
        Entreprise addedEntreprise = entrepriseIService.addEntreprise(entreprise);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEntreprise);
    }
}
