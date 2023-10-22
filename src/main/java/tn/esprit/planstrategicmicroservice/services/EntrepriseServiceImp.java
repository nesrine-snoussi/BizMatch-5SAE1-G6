package tn.esprit.planstrategicmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.planstrategicmicroservice.entities.Entreprise;
import tn.esprit.planstrategicmicroservice.repositories.EntrepriseRepository;

@Service
public class EntrepriseServiceImp implements EntrepriseIService {


    @Autowired
    EntrepriseRepository entrepriseRepository;
    @Override
    public Entreprise addEntreprise(Entreprise e) {
        return entrepriseRepository.save(e);
    }
}
