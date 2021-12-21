package fr.insa.ProjetJavaBDD.ressouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.models.Transaction;
import fr.insa.ProjetJavaBDD.ressouces.dto.CompteCreateModel;
import fr.insa.ProjetJavaBDD.services.CompteService;

@RestController
@RequestMapping("comptes")
public class CompteRessource extends CommonRessource {

	@Autowired
    CompteService compteService; //Init de la variable de service, permettant l'appel aux fonctions de cette classe
	
	/*
	 * Fonction pour recuperer un compte grâce à son id -> son numCompte
	 */
	@GetMapping("{id}")
    public Compte getCompte(@PathVariable("id") long id) throws Exception {
        return compteService.getCompteById(id);
    }
	
	/* 
	 * Fonction de création d'un compte
	 */
	@PostMapping
    public Compte createCompte(@RequestBody CompteCreateModel compteToCreate) throws FunctionnalProcessException {
		// Appel à la fonction de verification d'initialisation des variables d'entrée
        validateCompteModel(compteToCreate);
        return this.compteService.saveCompte(compteToCreate); //Return et appel à la fonction de sauvegarde de l'entité
    }
	
	/*
     * Fonction de verification d'initialisation des variables d'entrée pour un compte
     */
	private void validateCompteModel(CompteCreateModel compteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

     // Verification Init du model
        if(compteToCreate == null) {
            ex.getMessages().add("TransactionCreateModel : null");
        }
        
     // Serie de boucle if envoyant un message d'erreur si l'attribut est null ou égale à 0
        if(compteToCreate.getNumCompte() == 0) {
            ex.getMessages().add("Num_Compte : null");
        }

        if(compteToCreate.getSolde() == 0 ) {
            ex.getMessages().add("Solde est vide");
        }
        
        if(compteToCreate.getAgenceCode() == 0 ) {
            ex.getMessages().add("Code Agence est vide");
        }
        
        if(compteToCreate.getIdClient() == null ) {
            ex.getMessages().add("ID client est vide");
        }        
        
      //Envoie les messages d'erreurs s'il y en a 
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
	
	/*
	 * Fonction de récupération de toutes les transactions d'un compte
	 */
	@GetMapping("{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable("id") long id) throws Exception {
        return compteService.getCompteById(id).getTransactions();
    }
	
	/*
	 * Fonction de récupération de toutes les cartes d'un compte
	 */
	@GetMapping("{id}/cartes")
    public List<Carte> getCartes(@PathVariable("id") long id) throws Exception {
        return compteService.getCompteById(id).getCartes();
    }
	
	/*
     * Fonction de suppression d'un compte grâce à son id
     */
	@DeleteMapping("{id}")
    public ResponseEntity deleteCompte(@PathVariable("id") long id) {
		// Appel à la fonction de suppression de l'entité voulu
        compteService.deleteCompte(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
	
}
