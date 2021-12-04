package fr.insa.ProjetJavaBDD.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.models.Agence;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.repositories.CompteRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.CompteCreateModel;

@Service
public class CompteService {
	@Autowired
	private CompteRepository compteRepository;
	
	private AgenceService agenceService;
	
	public List<Compte> getListCompteByIBAN(String IBAN) {		
        return this.compteRepository.findByIBAN(IBAN);
    } 
	
	/*public Student saveStudent(StudentCreateModel studentToCreate) throws FonctionnalProcessException {

        University university = universityService.getUniversityById(studentToCreate.getUniversityId());

        Student s = Student.builder()
                .email(studentToCreate.getEmail())
                .firstName(studentToCreate.getFirstName())
                .name(studentToCreate.getName())
                .dateOfBirth(studentToCreate.getDateOfBirth())
                .registrationDate(new Date())
                .university(university)
                .adresse(studentToCreate.getAdresse())
                .build();

        return this.studentRepository.save(s);
    }*/
	
	public Compte saveCompte(CompteCreateModel compteToCreate)
	{
		Agence agence=agenceService.getAgenceById(compteToCreate.getAgenceCode());
		
		Compte compte = new Compte(agence);
		
		return this.compteRepository.save(compte);
	}
	
	int rib=97-((89*59300+15*code_agence+3*num_Compte)%97);
	String IBAN= "FR76 59300 "+code_agence +" "+ num_Compte+" " + rib;
}
