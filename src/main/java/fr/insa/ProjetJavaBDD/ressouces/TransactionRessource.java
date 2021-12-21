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
	TransactionService transactionService;
	
	@PostMapping
    public Transaction createTransaction(@RequestBody TransactionCreateModel transactionToCreate) throws FunctionnalProcessException {
        validateTransactionModel(transactionToCreate);
        return this.transactionService.saveTransaction(transactionToCreate);
    }
    
    private void validateTransactionModel(TransactionCreateModel transactionToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(transactionToCreate == null) {
            ex.getMessages().add("TransactionCreateModel : null");
        }

        if(transactionToCreate.getMontantTransac() == 0 ) {
            ex.getMessages().add("MontantTransac est vide");
        }
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity deleteTransaction(@PathVariable("id") int id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}
