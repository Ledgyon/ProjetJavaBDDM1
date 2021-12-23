package fr.insa.ProjetJavaBDD.services;


import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.repositories.CompteRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.CompteCreateModel;

@Service
public class CompteService {
	//Init de la variable de répertoire, permettant l'appel aux fonctions de cette classe
	@Autowired
	private CompteRepository compteRepository;
	
	//Init des variables de service
	@Autowired
	private AgenceService agenceService;
	@Autowired
	private ClientService clientService;
	
	//Init du message d'erreur si l'netité n'existe pas
	private static final String COMPTE_NOT_FOUND="Compte non trouvée avec le num_Compte : %s";
	
	/*
	 * Fonction de sauvegarde d'un compte
	 */
	@Transactional(rollbackOn = Exception.class)
	public Compte saveCompte(CompteCreateModel compteToCreate)  throws FunctionnalProcessException
	{
		// Stockage de l'agence attaché a ce compte
		Agence agence=agenceService.getAgenceById(compteToCreate.getAgenceCode());
		// Stockage des clients attachés a ce compte
		List<Client> client=clientService.getAllClientById(compteToCreate.getIdClient());
		
		//Init des variables pour la création de l'IBAN
		long code_agence = agence.getCodeAgence();
		long num_Compte = compteToCreate.getNumCompte();
		long rib=97-((89*59300+15*code_agence+3*num_Compte)%97);
		
		//Création de l'IBAN
		String iban= "FR76 59300 "+ code_agence +" "+ num_Compte +" " + rib;
		
		//Création de l'entité
		Compte compte = Compte.builder()
				.solde(compteToCreate.getSolde())
				.numCompte(compteToCreate.getNumCompte())
				.iban(iban)
				.agence(agence)
				.clients(client)
				.build();
		
		return this.compteRepository.save(compte); //sauvegarde
	}
	
	/*
	 * Fonction de récupération d'un compte précis
	 */
	public Compte getCompteById(Long num_Compte) throws FunctionnalProcessException{
		Compte compte=compteRepository
					.findById(num_Compte)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(COMPTE_NOT_FOUND,num_Compte)));
        return compte;
    } 
	
	/*
	 * Fonction de suppression d'un compte
	 */
	public void deleteCompte(long id) {
        this.compteRepository.deleteById(id);
    }
}
