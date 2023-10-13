package tn.esprit.registermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.registermicroservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername (String username);

    User findByEmail(String email);

    User findByToken(String token);
}
