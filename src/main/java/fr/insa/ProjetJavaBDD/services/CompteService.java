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
	@Autowired
	private CompteRepository compteRepository;
	
	private AgenceService agenceService;
	private ClientService clientService;
	
	private static final String COMPTE_NOT_FOUND="Compte non trouvée avec le num_Compte : %s";
	
	
	@Transactional(rollbackOn = Exception.class)
	public Compte saveCompte(CompteCreateModel compteToCreate)  throws FunctionnalProcessException
	{
		Agence agence=agenceService.getAgenceById(compteToCreate.getAgenceCode());
		List<Client> client=clientService.getAllClientById(compteToCreate.getIdClient());
		
		long code_agence = agence.getCodeAgence();
		long num_Compte = compteToCreate.getNumCompte();
		
		long rib=97-((89*59300+15*code_agence+3*num_Compte)%97);
		String iban= "FR76 59300 "+code_agence +" "+ num_Compte+" " + rib;
		
		Compte compte = Compte.builder()
				.solde(compteToCreate.getSolde())
				.numCompte(num_Compte)
				.iban(iban)
				.agence(agence)
				.clients(client)
				.build();
		
		return this.compteRepository.save(compte);
	}
	
	public Compte getCompteById(Long num_Compte) throws FunctionnalProcessException{
		Compte compte=compteRepository
					.findById(num_Compte)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(COMPTE_NOT_FOUND,num_Compte)));
        return compte;
    } 
	
	public void deleteCompte(long id) {
        this.compteRepository.deleteById(id);
    }
}
