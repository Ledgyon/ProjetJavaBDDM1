package fr.insa.ProjetJavaBDD.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	private static final String CLIENT_NOT_FOUND="Client non trouvée avec l'id : %s";
	
	public Client getClientById(Integer Id) throws FunctionnalProcessException{
		Client client=clientRepository
					.findById(Id)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(CLIENT_NOT_FOUND,Id)));
        return client;
    }
}
