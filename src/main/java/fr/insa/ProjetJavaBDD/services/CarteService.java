package fr.insa.ProjetJavaBDD.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;
import fr.insa.ProjetJavaBDD.models.Carte;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.repositories.CarteRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.CarteCreateModel;

@Service
public class CarteService {
	//Init de la variable de répertoire, permettant l'appel aux fonctions de cette classe
	@Autowired
	private CarteRepository carteRepository;
	
	//Init de la variable de service
	@Autowired
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
	public Carte saveCarte( CarteCreateModel  carteToCreate)  throws FunctionnalProcessException,ModelNotValidException
	{
		// Stockage du compte attaché a cette carte
		Compte compte= compteService.getCompteById(carteToCreate.getNumCompte());
		
		// Vérification du nombre de carte d'un compte (ne pouvant posséder plus de 2 cartes)
		if(compte.getCartes().size() < 2) 
		{
			//MotDePasse hashés
			String Password = carteToCreate.getMotDePasse();
			int motDePasse = Password.hashCode();
			
			//Création de l'entité
			Carte carte = Carte.builder()
					.plafond(carteToCreate.getPlafond())
					.numeroCarte(carteToCreate.getNumeroCarte())
					.motDePasse(motDePasse)
					.compte(compte)
					.build();
			
			return this.carteRepository.save(carte); //sauvegarde
		}
		else  // Message d'erreur
        {
        	ModelNotValidException ex = new ModelNotValidException();
        	ex.getMessages().add("Le compte possède le maximum de carte possible, impossible d'en créer une pour celui-ci.");
        	throw ex;
        }
	}
	
	/*
	 * Fonction de suppression d'une carte
	 */
	public void deleteCarte(long id) {
        this.carteRepository.deleteById(id);
    }
}
