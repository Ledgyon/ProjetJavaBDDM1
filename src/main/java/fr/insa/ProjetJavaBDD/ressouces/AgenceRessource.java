package fr.insa.ProjetJavaBDD.ressouces;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.insa.ProjetJavaBDD.services.AgenceService;

@RestController
@RequestMapping("agences")
public class AgenceRessource extends CommonRessource {
	@Autowired
	AgenceService agenceService;
	
	@GetMapping("{id}")
    public Agence getAgence(@PathVariable("id") int id) throws Exception {
        return agenceService.getAgenceById(id);
    }

    @PostMapping
    public Agence createAgence(@RequestBody AgenceCreateModel agenceToCreate) throws FunctionnalProcessException {
        validateAgenceModel(agenceToCreate);
        return this.agenceService.saveAgence(agenceToCreate);
    }
    
    private void validateAgenceModel(AgenceCreateModel agenceToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(agenceToCreate == null) {
            ex.getMessages().add("AgenceCreateModel : null");
        }

        if(agenceToCreate.getCode_agence() == 0 ) {
            ex.getMessages().add("Code_agence est vide");
        }
        
        if(agenceToCreate.getAdresse() == null || agenceToCreate.getAdresse().isBlank()) {
            ex.getMessages().add("Adresse est vide");
        }
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
}
