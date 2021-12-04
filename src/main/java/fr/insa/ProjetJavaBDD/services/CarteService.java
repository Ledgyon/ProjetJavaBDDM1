package fr.insa.ProjetJavaBDD.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.models.Carte;
import fr.insa.ProjetJavaBDD.repositories.CarteRepository;

@Service
public class CarteService {
	@Autowired
	private CarteRepository carteRepository;
	
	public Optional<Carte> getOptionalCarteById(Integer NumeroCarte) {
        return this.carteRepository.findById(NumeroCarte);
    } 
}
