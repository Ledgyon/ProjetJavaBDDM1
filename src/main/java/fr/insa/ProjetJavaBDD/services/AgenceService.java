package fr.insa.ProjetJavaBDD.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.repositories.AgenceRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.AgenceCreateModel;
import fr.insa.ProjetJavaBDD.ressouces.dto.AgenceReponseModel;

@Service
public class AgenceService {
	
	private static final String AGENCE_NOT_FOUND="Agence non trouvée avec le code : %s";
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	public Agence getAgenceById(Integer Code_agence) throws FunctionnalProcessException{
		Agence agence=agenceRepository
					.findById(Code_agence)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(AGENCE_NOT_FOUND,Code_agence)));
		
		return agence;
    } 
	
	public Agence saveAgence(AgenceCreateModel agenceToCreate)  throws FunctionnalProcessException
	{
		Agence agence = Agence.builder()
				.Code_agence(agenceToCreate.getCode_agence())
				.Adresse(agenceToCreate.getAdresse())
				.build();
		
		
		
		return this.agenceRepository.save(agence);
	}
}
