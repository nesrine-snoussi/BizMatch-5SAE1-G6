package tn.esprit.authenbizmatch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.authenbizmatch.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername (String username);
}
