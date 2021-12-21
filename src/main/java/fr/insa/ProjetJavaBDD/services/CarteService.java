package fr.insa.ProjetJavaBDD.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.models.Carte;
import fr.insa.ProjetJavaBDD.models.Client;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.repositories.CarteRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.CarteCreateModel;

@Service
public class CarteService {
	@Autowired
	private CarteRepository carteRepository;
	private CompteService compteService;
	
	private static final String CARTE_NOT_FOUND="Carte non trouvée avec l'id : %s";

	public Carte getCarteById(Long Id) throws FunctionnalProcessException{
		Carte carte=carteRepository
					.findById(Id)
					.orElseThrow(()-> new FunctionnalProcessException(String.format(CARTE_NOT_FOUND,Id)));
        return carte;
    }
	
	@Transactional(rollbackOn = Exception.class)
	public Carte saveCarte( CarteCreateModel  carteToCreate)  throws FunctionnalProcessException
	{
		Compte compte= compteService.getCompteById(carteToCreate.getNumCompte());
		
		Carte carte = Carte.builder()
				.plafond(carteToCreate.getPlafond())
				.numeroCarte(carteToCreate.getNumeroCarte())
				.motDePasse(carteToCreate.getMotDePasse())
				.compte(compte)
				.build();
		
		return this.carteRepository.save(carte);
	}
	
	public void deleteCarte(long id) {
        this.carteRepository.deleteById(id);
    }
}
