package fr.insa.ProjetJavaBDD.ressouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.models.Transaction;
import fr.insa.ProjetJavaBDD.ressouces.dto.CompteCreateModel;
import fr.insa.ProjetJavaBDD.services.CompteService;

@RestController
@RequestMapping("comptes")
public class CompteRessource extends CommonRessource {

	@Autowired
    CompteService compteService;
	
	@GetMapping("{id}")
    public Compte getCompte(@PathVariable("id") int id) throws Exception {
        return compteService.getCompteById(id);
    }
	
	@PostMapping
    public Compte createCompte(@RequestBody CompteCreateModel compteToCreate) throws FunctionnalProcessException {
        validateCompteModel(compteToCreate);
        return this.compteService.saveCompte(compteToCreate);
    }
	
	private void validateCompteModel(CompteCreateModel compteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(compteToCreate == null) {
            ex.getMessages().add("TransactionCreateModel : null");
        }
        
        if(compteToCreate.getNum_Compte() == 0) {
            ex.getMessages().add("Num_Compte : null");
        }

        if(compteToCreate.getSolde() == 0 ) {
            ex.getMessages().add("Solde est vide");
        }
        
        if(compteToCreate.getAgenceCode() == 0 ) {
            ex.getMessages().add("Code Agence est vide");
        }
        
        if(compteToCreate.getIDclient() == null ) {
            ex.getMessages().add("ID client est vide");
        }
        
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
	
	
	@GetMapping("{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable("id") int id) throws Exception {
        return compteService.getCompteById(id).getTransactions();
    }
	
	
}
