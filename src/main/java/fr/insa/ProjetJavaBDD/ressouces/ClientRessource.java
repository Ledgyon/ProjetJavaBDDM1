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
	ClientService clientService; //Init de la variable de service, permettant l'appel aux fonctions de cette classe
	
	/*
	 * Fonction pour recuperer un client grâce à son id 
	 */
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
		// Appel à la fonction de verification d'initialisation des variables d'entrée
	    validateClientModel(clientToCreate);
	    return this.clientService.saveClient(clientToCreate); //Return et appel à la fonction de sauvegarde de l'entité
	}

	/*
     * Fonction de verification d'initialisation des variables d'entrée pour un client
     */
	private void validateClientModel(ClientCreateModel clientToCreate) throws ModelNotValidException {
	    ModelNotValidException ex = new ModelNotValidException();

	 // Verification Init du model
	    if(clientToCreate == null) {
	        ex.getMessages().add("ClientCreateModel : null");
	    }

	 // Serie de boucle if envoyant un message d'erreur si l'attribut est null ou égale à 0
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
	    
	  //Envoie les messages d'erreurs s'il y en a 
	    if(!ex.getMessages().isEmpty()) {
	        throw ex;
	    }
	}
	
	/*
     * Fonction de suppression d'un client grâce à son id
     */
	@DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable("id") int id) {
		// Appel à la fonction de suppression de l'entité voulu
        clientService.deleteClient(id);
        return ResponseEntity.ok().build();
    }
	
}
