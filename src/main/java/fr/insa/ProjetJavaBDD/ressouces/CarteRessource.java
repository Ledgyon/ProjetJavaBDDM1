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
import fr.insa.ProjetJavaBDD.models.Carte;
import fr.insa.ProjetJavaBDD.ressouces.dto.CarteCreateModel;
import fr.insa.ProjetJavaBDD.services.CarteService;

@RestController
@RequestMapping("cartes")
public class CarteRessource extends CommonRessource {
	/*
	@Autowired
	CarteService carteService;
	
	@GetMapping("{id}")
    public Carte getCarte(@PathVariable("id") int id) throws Exception {
        return carteService.getCarteById(id);
    }

    @PostMapping
    public Carte createCarte(@RequestBody CarteCreateModel carteToCreate) throws FunctionnalProcessException {
        validateCarteModel(carteToCreate);
        return this.carteService.saveCarte(carteToCreate);
    }
    
    private void validateCarteModel(CarteCreateModel carteToCreate) throws ModelNotValidException {
        ModelNotValidException ex = new ModelNotValidException();

        if(carteToCreate == null) {
            ex.getMessages().add("AgenceCreateModel : null");
        }

        if(carteToCreate.getCode_agence() == 0 ) {
            ex.getMessages().add("Code_agence est vide");
        }
        
        if(carteToCreate.getAdresse() == null || carteToCreate.getAdresse().isBlank()) {
            ex.getMessages().add("Adresse est vide");
        }
        
        if(!ex.getMessages().isEmpty()) {
            throw ex;
        }
    }
    */
}
