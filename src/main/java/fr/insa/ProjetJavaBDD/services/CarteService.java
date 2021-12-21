package fr.insa.ProjetJavaBDD.services;

import javax.transaction.Transactional;

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
	//Init de la variable de répertoire, permettant l'appel aux fonctions de cette classe
	@Autowired
	private CarteRepository carteRepository;
	
	//Init de la variable de service
	private CompteService compteService;
	
	//Init du message d'erreur si l'netité n'existe pas
	private static final String CARTE_NOT_FOUND="Carte non trouvée avec l'id : %s";

	/*
	 * Fonction de récupération d'une carte précise
	 */
	public Carte getCarteById(Long Id) throws FunctionnalProcessException{
		Carte carte=carteRepository
					.findById(Id)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(CARTE_NOT_FOUND,Id)));
        return carte;
    }
	
	/*
	 * Fonction de sauvegarde d'une carte
	 */
	@Transactional(rollbackOn = Exception.class)
	public Carte saveCarte( CarteCreateModel  carteToCreate)  throws FunctionnalProcessException
	{
		// Stockage du compte attaché a cette carte
		Compte compte= compteService.getCompteById(carteToCreate.getNumCompte());
		
		//Création de l'entité
		Carte carte = Carte.builder()
				.plafond(carteToCreate.getPlafond())
				.numeroCarte(carteToCreate.getNumeroCarte())
				.motDePasse(carteToCreate.getMotDePasse())
				.compte(compte)
				.build();
		
		return this.carteRepository.save(carte); //sauvegarde
	}
	
	/*
	 * Fonction de suppression d'une carte
	 */
	public void deleteCarte(long id) {
        this.carteRepository.deleteById(id);
    }
}
