package fr.insa.ProjetJavaBDD.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
    
}
