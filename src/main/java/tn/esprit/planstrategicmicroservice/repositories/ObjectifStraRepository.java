package tn.esprit.planstrategicmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.planstrategicmicroservice.entities.ObjectifStra;

@Repository
public interface ObjectifStraRepository extends JpaRepository<ObjectifStra, Integer> {


}
