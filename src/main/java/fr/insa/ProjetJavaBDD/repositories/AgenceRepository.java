package fr.insa.ProjetJavaBDD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Agence;


//Création du Repository en extend de JPARepository pour l'accès aux fonctions find,...
@Repository
public interface AgenceRepository extends JpaRepository<Agence, Long> {
    
}

