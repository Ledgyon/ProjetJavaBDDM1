package fr.insa.ProjetJavaBDD.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.repositories.AgenceRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.AgenceCreateModel;
import fr.insa.ProjetJavaBDD.ressouces.dto.AgenceReponseModel;

@Service
public class AgenceService {
	
	//Init du message d'erreur si l'entité n'existe pas
	private static final String AGENCE_NOT_FOUND="Agence non trouvée avec le code : %s";
	
	//Init de la variable de répertoire, permettant l'appel aux fonctions de cette classe
	@Autowired
	private AgenceRepository agenceRepository;
	
	/*
	 * Fonction de récupération d'une agence précise
	 */
	public Agence getAgenceById(Long Code_agence) throws FunctionnalProcessException{
		Agence agence=agenceRepository
					.findById(Code_agence)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(AGENCE_NOT_FOUND,Code_agence)));
		
		return agence;
    } 
	
	/*
	 * Fonction de sauvegarde d'une agence
	 */
	@Transactional(rollbackOn = Exception.class)
	public Agence saveAgence(AgenceCreateModel agenceToCreate)  throws FunctionnalProcessException
	{
		//Création de l'entité
		Agence agence = Agence.builder()
				.codeAgence(agenceToCreate.getCodeAgence())
				.adresse(agenceToCreate.getAdresse())
				.build();
		
		return this.agenceRepository.save(agence); //sauvegarde
	}
	
	/*
	 * Fonction de suppression d'une agence
	 */
	public void deleteAgence(long id) {
        this.agenceRepository.deleteById(id);
    }
}
