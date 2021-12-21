package fr.insa.ProjetJavaBDD.ressouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;
import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.ressouces.dto.ClientCreateModel;
import fr.insa.ProjetJavaBDD.services.ClientService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("clients")
public class ClientRessource extends CommonRessource {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("{id}/comptes")
    public List<Compte> getComptes(@PathVariable("id") int id) throws Exception {
        return clientService.getClientById(id).getComptes();
    }
	
	@GetMapping("{id}")
	public Client getClient(@PathVariable("id") int id) throws Exception {
		Client client = clientService.getClientById(id);
		/*ClientReponseModel response = new ClientReponseModel();
		response.setAdresse(agence.getAdresse());
		response.setCode_agence(agence.getCode_agence());*/
        return client;
	}

	@PostMapping
	public Client createClient(@RequestBody ClientCreateModel clientToCreate) throws FunctionnalProcessException {
	    validateClientModel(clientToCreate);
	    return this.clientService.saveClient(clientToCreate);
	}

	private void validateClientModel(ClientCreateModel clientToCreate) throws ModelNotValidException {
	    ModelNotValidException ex = new ModelNotValidException();

	    if(clientToCreate == null) {
	        ex.getMessages().add("ClientCreateModel : null");
	    }

	    if(clientToCreate.getAge() == 0 ) {
	        ex.getMessages().add("Age est vide");
	    }

	    if(clientToCreate.getTelephone() == 0 ) {
	        ex.getMessages().add("Telephone est vide");
	    }

	    if(clientToCreate.getNom() == null || clientToCreate.getNom().isBlank()) {
	        ex.getMessages().add("Nom est vide");
	    }
	  
	    if(clientToCreate.getPrenom() == null || clientToCreate.getPrenom().isBlank()) {
	        ex.getMessages().add("Prenom est vide");
	    } 

	    if(clientToCreate.getAdresse() == null || clientToCreate.getAdresse().isBlank()) {
	        ex.getMessages().add("Adresse est vide");
	    }    
	    
	    if(clientToCreate.getAgenceId() == 0 ) {
	        ex.getMessages().add("Agence_id est vide");
	    }
	    
	    if(!ex.getMessages().isEmpty()) {
	        throw ex;
	    }
	}
	
	@DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable("id") int id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
	
}
