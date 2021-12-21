package fr.insa.ProjetJavaBDD.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.repositories.ClientRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.ClientCreateModel;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	private AgenceService agenceService;
	
	private static final String CLIENT_NOT_FOUND="Client non trouvée avec l'id : %s";
	
	public Client getClientById(Integer Id) throws FunctionnalProcessException{
		Client client=clientRepository
					.findById(Id)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(CLIENT_NOT_FOUND,Id)));
        return client;
    }
	
	public List<Client> getAllClientById(List<Integer> Id) throws FunctionnalProcessException{
		return clientRepository.findAllById(Id);
    }
	
	@Transactional(rollbackOn = Exception.class)
	public Client saveClient( ClientCreateModel  clientToCreate)  throws FunctionnalProcessException
	{
		Agence agence=agenceService.getAgenceById(clientToCreate.getAgenceId());
		
		Client client = Client.builder()
				.nom(clientToCreate.getNom())
				.prenom(clientToCreate.getPrenom())
				.telephone(clientToCreate.getTelephone())
				.age(clientToCreate.getAge())
				.adresse(clientToCreate.getAdresse())
				.agence(agence)
				.build();
		
		
		
		return this.clientRepository.save(client);
	}
	
	public void deleteClient(int id) {
        this.clientRepository.deleteById(id);
    }
}
