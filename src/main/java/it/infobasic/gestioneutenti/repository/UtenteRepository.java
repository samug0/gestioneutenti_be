package it.infobasic.gestioneutenti.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import it.infobasic.gestioneutenti.model.User;

public interface UtenteRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  
    



}




