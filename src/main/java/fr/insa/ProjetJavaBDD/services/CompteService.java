package fr.insa.ProjetJavaBDD.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.repositories.CompteRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.CompteCreateModel;

@Service
public class CompteService {
	@Autowired
	private CompteRepository compteRepository;
	
	private AgenceService agenceService;
	
	private static final String COMPTE_NOT_FOUND="Compte non trouvée avec le num_Compte : %s";
	
	
	
	public Compte saveCompte(CompteCreateModel compteToCreate)  throws FunctionnalProcessException
	{
		Agence agence=agenceService.getAgenceById(compteToCreate.getAgenceCode());
		
		int code_agence = agence.getCode_agence();
		int num_Compte = compteToCreate.getNum_Compte();
		
		int rib=97-((89*59300+15*code_agence+3*num_Compte)%97);
		String IBAN= "FR76 59300 "+code_agence +" "+ num_Compte+" " + rib;
		
		Compte compte = Compte.builder()
				.Solde(compteToCreate.getSolde())
				.num_Compte(num_Compte)
				.IBAN(IBAN)
				.agence(agence)
				.build();
		
		
		
		return this.compteRepository.save(compte);
	}
	
	public Compte getCompteById(Integer num_Compte) throws FunctionnalProcessException{
		Compte compte=compteRepository
					.findById(num_Compte)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(COMPTE_NOT_FOUND,num_Compte)));
        return compte;
    } 
}
