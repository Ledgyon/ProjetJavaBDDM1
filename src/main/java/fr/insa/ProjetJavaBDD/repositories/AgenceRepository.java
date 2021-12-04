package fr.insa.ProjetJavaBDD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Agence;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, Integer> {
    
}

