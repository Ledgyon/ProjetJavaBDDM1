package fr.insa.ProjetJavaBDD.ressouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;
import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.ressouces.dto.AgenceCreateModel;
import fr.insa.ProjetJavaBDD.ressouces.dto.AgenceReponseModel;
import fr.insa.ProjetJavaBDD.services.AgenceService;

@RestController
@RequestMapping("agences")
public class AgenceRessource extends CommonRessource {
	@Autowired
	AgenceService agenceService; //Init de la variable de service, permettant l'appel aux fonctions de cette classe
	
	/*
	 * Fonction pour recuperer une agence grâce à son id -> son agenceCode
	 */
	@GetMapping("{id}")
    public AgenceReponseModel getAgence(@PathVariable("id") long id) throws Exception {
		// Stockage de l'agence selon l'id voulu
		Agence agence = agenceService.getAgenceById(id); 
		// Model de l'agence, avec les informations que l'on veut
		AgenceReponseModel response= AgenceReponseModel.builder() 
			.codeAgence(agence.getCodeAgence())
			.adresse(agence.getAdresse())
			.build();
		return response;
    }

	/*
	 * Fonction pour creer une agence
	 */
    @PostMapping
    public Agence createAgence(@RequestBody AgenceCreateModel agenceToCreate) throws FunctionnalProcessException {
    	// Appel à la fonction de verification d'initialisation des variables d'entrée
        validateAgenceModel(agenceToCreate); 
        return this.agenceService.saveAgence(agenceToCreate); //Return et appel à la fonction de sauvegarde de l'entité
    }
    
    /*
     * Fonction de verification d'initialisation des variables d'entrée pour une agence
     */
    private void validateAgenceModel(AgenceCreateModel agenceToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException(); //Init de la variable renvoyant les messages d'erreurs

        // Verification Init du model
        if(agenceToCreate == null) {
            ex.getMessages().add("AgenceCreateModel : null");
        }

        // Serie de boucle if envoyant un message d'erreur si l'attribut est null ou égale à 0
        if(agenceToCreate.getCodeAgence() == 0 ) {
            ex.getMessages().add("Code_agence est vide");
        }
        
        if(agenceToCreate.getAdresse() == null || agenceToCreate.getAdresse().isBlank()) {
            ex.getMessages().add("Adresse est vide");
        }
        
        //Envoie les messages d'erreurs s'il y en a 
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
    
    /*
     * Fonction de suppression d'une agence grâce à son id
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteAgence(@PathVariable("id") long id) {
    	// Appel à la fonction de suppression de l'entité voulu
        agenceService.deleteAgence(id);
        return ResponseEntity.ok().build();
    }
}
