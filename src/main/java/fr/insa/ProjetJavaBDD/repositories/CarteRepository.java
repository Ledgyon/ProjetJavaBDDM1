package fr.insa.ProjetJavaBDD.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Carte;


//Création du Repository en extend de JPARepository pour l'accès aux fonctions find,...
@Repository
public interface CarteRepository extends JpaRepository<Carte, Long> {
    //public List<Carte> findByPlafond(Integer Plafond);
}
