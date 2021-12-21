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
	
	private static final String AGENCE_NOT_FOUND="Agence non trouvée avec le code : %s";
	
	@Autowired
	private AgenceRepository agenceRepository;
	
	public Agence getAgenceById(Long Code_agence) throws FunctionnalProcessException{
		Agence agence=agenceRepository
					.findById(Code_agence)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(AGENCE_NOT_FOUND,Code_agence)));
		
		return agence;
    } 
	
	@Transactional(rollbackOn = Exception.class)
	public Agence saveAgence(AgenceCreateModel agenceToCreate)  throws FunctionnalProcessException
	{
		Agence agence = Agence.builder()
				.codeAgence(agenceToCreate.getCodeAgence())
				.adresse(agenceToCreate.getAdresse())
				.build();
		
		
		
		return this.agenceRepository.save(agence);
	}
	
	public void deleteAgence(long id) {
        this.agenceRepository.deleteById(id);
    }
}
