package tn.esprit.planstrategicmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.planstrategicmicroservice.entities.PlanStrategique;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlanStrategiqueRepository extends JpaRepository<PlanStrategique , Long> {
    public PlanStrategique findPlanStrategiqueByIdAndDate(Long id , Date date);
    public Optional<PlanStrategique> findById (Long id );

    @Query("SELECT DISTINCT p FROM PlanStrategique p LEFT JOIN FETCH p.objectifStras")
    List<PlanStrategique> findAllWithObjectifs();

    List<PlanStrategique> findAllByEntreprise_Nom (String nom);
}
