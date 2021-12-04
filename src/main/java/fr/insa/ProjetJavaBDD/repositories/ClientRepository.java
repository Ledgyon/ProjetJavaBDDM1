package fr.insa.ProjetJavaBDD.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Optional<Client> findById(Integer ID);
}
