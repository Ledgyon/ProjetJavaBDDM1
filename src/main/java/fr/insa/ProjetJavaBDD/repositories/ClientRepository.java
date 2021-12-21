package fr.insa.ProjetJavaBDD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Client;


//Création du Repository en extend de JPARepository pour l'accès aux fonctions find,...
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
}
