package tn.esprit.planstrategicmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.planstrategicmicroservice.entities.Entreprise;
@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise , Long> {
}
