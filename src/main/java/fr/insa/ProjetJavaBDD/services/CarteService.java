package fr.insa.ProjetJavaBDD.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Carte;
import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.repositories.CarteRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.CarteCreateModel;

@Service
public class CarteService {
	@Autowired
	private CarteRepository carteRepository;
	private CompteService compteService;
	
	private static final String CARTE_NOT_FOUND="Carte non trouvée avec l'id : %s";

	public Carte getCarteById(Integer Id) throws FunctionnalProcessException{
		Carte carte=carteRepository
					.findById(Id)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(CARTE_NOT_FOUND,Id)));
        return carte;
    }
	
	public Carte saveCarte( CarteCreateModel  carteToCreate)  throws FunctionnalProcessException
	{
		Compte compte= compteService.getCompteById(carteToCreate.getNumCompte());
		
		Carte carte = Carte.builder()
				.Plafond(carteToCreate.getPlafond())
				.NumeroCarte(carteToCreate.getNumeroCarte())
				.MotDePasse(carteToCreate.getMotDePasse())
				.compte(compte)
				.build();
		
		return this.carteRepository.save(carte);
	}
}
