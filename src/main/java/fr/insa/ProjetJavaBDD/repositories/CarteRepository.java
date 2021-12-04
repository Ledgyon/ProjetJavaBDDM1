package fr.insa.ProjetJavaBDD.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Carte;

@Repository
public interface CarteRepository extends JpaRepository<Carte, Integer> {
    public Optional<Carte> findById(Integer NumeroCarte);
    //public List<Carte> findByPlafond(Integer Plafond);
}
