package fr.insa.ProjetJavaBDD.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.repositories.ClientRepository;

@Service
public class ClientService {
	@Autowired
	private ClientRepository clientRepository;
	
	public Optional<Client> getOptionalClientById(Integer ID) {
        return this.clientRepository.findById(ID);
    } 
}
