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
	//Init de la variable de répertoire, permettant l'appel aux fonctions de cette classe
	@Autowired
	private ClientRepository clientRepository;
	
	//Init de la variable de service
	@Autowired
	private AgenceService agenceService;
	
	//Init du message d'erreur si l'netité n'existe pas
	private static final String CLIENT_NOT_FOUND="Client non trouvée avec l'id : %s";
	
	/*
	 * Fonction de récupération d'un client précis
	 */
	public Client getClientById(Integer Id) throws FunctionnalProcessException{
		Client client=clientRepository
					.findById(Id)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(CLIENT_NOT_FOUND,Id)));
        return client;
    }
	
	/*
	 * Fonction de récupération de tous les clients d'une liste d'id de client,
	 * utilisé pour récupéré le liste de client d'un compte
	 */
	public List<Client> getAllClientById(List<Integer> Id) throws FunctionnalProcessException{
		return clientRepository.findAllById(Id);
    }
	
	/*
	 * Fonction de sauvegarde d'un client
	 */
	@Transactional(rollbackOn = Exception.class)
	public Client saveClient( ClientCreateModel  clientToCreate)  throws FunctionnalProcessException
	{
		// Stockage de l'agence attaché a ce client
		Agence agence=agenceService.getAgenceById(clientToCreate.getAgenceId());
		
		//Création de l'entité
		Client client = Client.builder()
				.id(1)
				.nom(clientToCreate.getNom())
				.prenom(clientToCreate.getPrenom())
				.telephone(clientToCreate.getTelephone())
				.age(clientToCreate.getAge())
				.adresse(clientToCreate.getAdresse())
				.agence(agence)
				.build();
		
		return this.clientRepository.save(client); //sauvegarde
	}
	
	/*
	 * Fonction de suppression d'un client
	 */
	public void deleteClient(int id) {
        this.clientRepository.deleteById(id);
    }
}
