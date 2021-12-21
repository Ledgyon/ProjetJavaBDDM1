package fr.insa.ProjetJavaBDD.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Compte;


//Création du Repository en extend de JPARepository pour l'accès aux fonctions find,...
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    
}
