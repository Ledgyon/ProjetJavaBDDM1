package fr.insa.ProjetJavaBDD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	/*@Query("SELECT COUNT(s) FROM Client c WHERE c.agence.Code_agence = :agence_id")
    public int numberOfStudent(@Param("agence_id") int agence_id);*/
}
