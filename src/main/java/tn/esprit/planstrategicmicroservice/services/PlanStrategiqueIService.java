package tn.esprit.planstrategicmicroservice.services;

import tn.esprit.planstrategicmicroservice.entities.ObjectifStra;
import tn.esprit.planstrategicmicroservice.entities.PlanStrategique;

import java.util.Date;
import java.util.List;

public interface PlanStrategiqueIService {

 public PlanStrategique addPlan(PlanStrategique plan);
public PlanStrategique findByIdAndDate (Long id , Date date);

 public PlanStrategique addObjectifsToPlanStrategique(Long planStrategiqueId, List<ObjectifStra> objectifs);

 public List<PlanStrategique> getAllPlanStrategiquesWithObjectifs();

 public PlanStrategique createPlanStrategiqueForEntreprise(Long entrepriseId, PlanStrategique planStrategique);

 public List<PlanStrategique> getAllPlansForEntreprise(String nom);

}
