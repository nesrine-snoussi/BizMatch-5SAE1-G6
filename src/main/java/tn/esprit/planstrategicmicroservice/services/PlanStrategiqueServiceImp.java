package tn.esprit.planstrategicmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.planstrategicmicroservice.entities.Entreprise;
import tn.esprit.planstrategicmicroservice.entities.ObjectifStra;
import tn.esprit.planstrategicmicroservice.entities.PlanStrategique;
import tn.esprit.planstrategicmicroservice.repositories.EntrepriseRepository;
import tn.esprit.planstrategicmicroservice.repositories.ObjectifStraRepository;
import tn.esprit.planstrategicmicroservice.repositories.PlanStrategiqueRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlanStrategiqueServiceImp implements PlanStrategiqueIService{

    @Autowired
    PlanStrategiqueRepository planStrategiqueRepository;
    @Autowired
    ObjectifStraRepository objectifStraRepository ;

    @Autowired
    private EntrepriseRepository entrepriseRepository;



    @Override
    public PlanStrategique addPlan(PlanStrategique plan) {
        return planStrategiqueRepository.save(plan);
    }

    @Override
    public PlanStrategique findByIdAndDate(Long id, Date date) {
        return planStrategiqueRepository.findPlanStrategiqueByIdAndDate(id, date);
    }


    @Override
    public PlanStrategique addObjectifsToPlanStrategique(Long planStrategiqueId, List<ObjectifStra> objectifs) {
        // Load the PlanStrategique from the database
        PlanStrategique planStrategique = planStrategiqueRepository.findById(planStrategiqueId)
                .orElseThrow(() -> new EntityNotFoundException("PlanStrategique not found"));

        // Save and associate the provided Objectif instances
        List<ObjectifStra> savedObjectifs = new ArrayList<>();
        for (ObjectifStra objectif : objectifs) {

            objectif.setPlanStrategique(planStrategique);
            savedObjectifs.add(objectifStraRepository.save(objectif));


        }
        planStrategique.getObjectifStras().addAll(savedObjectifs);

        // Save the updated PlanStrategique
        return planStrategiqueRepository.save(planStrategique);
    }

    @Override
    public List<PlanStrategique> getAllPlanStrategiquesWithObjectifs() {
        return planStrategiqueRepository.findAllWithObjectifs();
    }

    @Override
    public PlanStrategique createPlanStrategiqueForEntreprise(Long entrepriseId, PlanStrategique planStrategique) {
        // Find the Entreprise by ID
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new EntityNotFoundException("Entreprise not found"));

        // Set the Entreprise for the PlanStrategique
        planStrategique.setEntreprise(entreprise);

        // Save the PlanStrategique
        return planStrategiqueRepository.save(planStrategique);
    }

    @Override
    public List<PlanStrategique> getAllPlansForEntreprise(String nom) {
        return planStrategiqueRepository.findAllByEntreprise_Nom(nom);
    }


}
