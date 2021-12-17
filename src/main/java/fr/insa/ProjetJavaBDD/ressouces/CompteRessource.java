package fr.insa.ProjetJavaBDD.ressouces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ProjetJavaBDD.models.Transaction;
import fr.insa.ProjetJavaBDD.services.CompteService;

@RestController
@RequestMapping("comptes")
public class CompteRessource extends CommonRessource {

	@Autowired
    CompteService compteService;
	
	@GetMapping("{id}")
    public List<Transaction> getTransactions(@PathVariable("id") int id) throws Exception {
        return compteService.getCompteById(id).getTransactions();
    }
	
	
}
