package fr.insa.ProjetJavaBDD.ressouces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;
import fr.insa.ProjetJavaBDD.models.Transaction;
import fr.insa.ProjetJavaBDD.ressouces.dto.TransactionCreateModel;
import fr.insa.ProjetJavaBDD.services.TransactionService;

@RestController
@RequestMapping("transactions")
public class TransactionRessource extends CommonRessource {
	@Autowired
	TransactionService transactionService; //Init de la variable de service, permettant l'appel aux fonctions de cette classe
	
	/*
	 * Fonction de création d'une transaction
	 */
	@PostMapping
    public Transaction createTransaction(@RequestBody TransactionCreateModel transactionToCreate) throws FunctionnalProcessException {
		// Appel à la fonction de verification d'initialisation des variables d'entrée
		validateTransactionModel(transactionToCreate);
        return this.transactionService.saveTransaction(transactionToCreate); //Return et appel à la fonction de sauvegarde de l'entité
    }
    
	/*
     * Fonction de verification d'initialisation des variables d'entrée pour une transaction
     */
    private void validateTransactionModel(TransactionCreateModel transactionToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

     // Verification Init du model
        if(transactionToCreate == null) {
            ex.getMessages().add("TransactionCreateModel : null");
        }

     // Boucle if envoyant un message d'erreur si l'attribut est égale à 0
        if(transactionToCreate.getMontantTransac() == 0 ) {
            ex.getMessages().add("MontantTransac est vide");
        }
        
      //Envoie les messages d'erreurs s'il y en a 
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
    
    /*
     * Fonction de suppression d'une transaction grâce à son id
     */
    @DeleteMapping("{id}")
    public ResponseEntity deleteTransaction(@PathVariable("id") int id) {
    	// Appel à la fonction de suppression de l'entité voulu
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}
