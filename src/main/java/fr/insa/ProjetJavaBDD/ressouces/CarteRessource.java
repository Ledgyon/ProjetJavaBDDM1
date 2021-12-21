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
import fr.insa.ProjetJavaBDD.models.Carte;
import fr.insa.ProjetJavaBDD.ressouces.dto.CarteCreateModel;
import fr.insa.ProjetJavaBDD.services.CarteService;

@RestController
@RequestMapping("cartes")
public class CarteRessource extends CommonRessource {
	
	@Autowired
	CarteService carteService; //Init de la variable de service, permettant l'appel aux fonctions de cette classe
	
	/*
	 * Fonction pour recuperer une carte grâce à son id -> son numeroCarte
	 */
	@GetMapping("{id}")
    public Carte getCarte(@PathVariable("id") long id) throws Exception {
        return carteService.getCarteById(id);
    }

	/*
	 * Fonction pour creer une carte
	 */
    @PostMapping
    public Carte createCarte(@RequestBody CarteCreateModel carteToCreate) throws FunctionnalProcessException {
    	// Appel à la fonction de verification d'initialisation des variables d'entrée
        validateCarteModel(carteToCreate);
        return this.carteService.saveCarte(carteToCreate); //Return et appel à la fonction de sauvegarde de l'entité
    }
    
    /*
     * Fonction de verification d'initialisation des variables d'entrée pour une carte
     */
    private void validateCarteModel(CarteCreateModel carteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

     // Verification Init du model
        if(carteToCreate == null) {
            ex.getMessages().add("AgenceCreateModel : null");
        }

     // Serie de boucle if envoyant un message d'erreur si l'attribut est null ou égale à 0
        if(carteToCreate.getPlafond() == 0 ) {
            ex.getMessages().add("Plafond est vide");
        }
        
        if(carteToCreate.getNumeroCarte() == 0 ) {
            ex.getMessages().add("NumeroCarte est vide");
        }
        
        if(carteToCreate.getMotDePasse() == null || carteToCreate.getMotDePasse().isBlank()) {
            ex.getMessages().add("MotDePasse est vide");
        }
        
        if(carteToCreate.getNumCompte() == 0) {
            ex.getMessages().add("NumCompte est vide");
        }
        
      //Envoie les messages d'erreurs s'il y en a 
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
    
    /*
     * Fonction de suppression d'une carte grâce à son id
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteCarte(@PathVariable("id") long id) {
    	// Appel à la fonction de suppression de l'entité voulu
        carteService.deleteCarte(id);
        return ResponseEntity.ok().build();
    }
    
}
