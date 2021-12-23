package fr.insa.ProjetJavaBDD.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
import fr.insa.ProjetJavaBDD.exceptions.ModelNotValidException;
import fr.insa.ProjetJavaBDD.models.Compte;
import fr.insa.ProjetJavaBDD.models.Transaction;
import fr.insa.ProjetJavaBDD.repositories.TransactionRepository;
import fr.insa.ProjetJavaBDD.ressouces.dto.TransactionCreateModel;

@Service
public class TransactionService {
	//Init de la variable de répertoire, permettant l'appel aux fonctions de cette classe
	@Autowired
	private TransactionRepository transactionRepository;
	
	//Init de la variable de service
	@Autowired
	private CompteService compteService; 
	
	/*
	 * Fonction de recuperation des montant de transaction, triée au plus récent
	 */
	public List<Transaction> getOptionalTransactionMontantTransacOrderByDateTransac(Integer montantTransac) {
        return this.transactionRepository.findByMontantTransacOrderByDateTransac(montantTransac);
    } 
	
	/*
	 * Fonction de sauvegarde d'une transaction
	 */
	@Transactional(rollbackOn = Exception.class)
	public Transaction saveTransaction(TransactionCreateModel transactionToCreate)  throws FunctionnalProcessException,ModelNotValidException
	{
		
		// Stockage du compte bénéficiaire et emetteur attaché a cette transaction
		Compte compteBenef=compteService.getCompteById(transactionToCreate.getNumCompteB());
        Compte compteEmetteur=compteService.getCompteById(transactionToCreate.getNumCompteE());
        
        // IMPORTANT : on part du postulat que les comptes ne peuvent pas avoir de découvert
        if(compteEmetteur.getSolde()-transactionToCreate.getMontantTransac() >= 0)
        {
        	// Mise a jour des soldes concernés
            compteBenef.setSolde(compteBenef.getSolde()+transactionToCreate.getMontantTransac());
            compteEmetteur.setSolde(compteEmetteur.getSolde()-transactionToCreate.getMontantTransac());
            
            //Builder de la transaction
            Transaction transaction = Transaction.builder()
                    .dateTransac(new Date())
                    .montantTransac(transactionToCreate.getMontantTransac())
                    .compteBenef(compteBenef)
                    .compteEmetteur(compteEmetteur)
                    .build();
    		
    		return this.transactionRepository.save(transaction); // sauvegarder
        }
        else  // Message d'erreur
        {
        	ModelNotValidException ex = new ModelNotValidException();
        	ex.getMessages().add("Le solde du compte emetteur ne permet pas cette transaction");
        	throw ex;
        }
        
	}
	
	/*
	 * Fonction de suppression d'une transaction
	 */
	public void deleteTransaction(int id) {
        this.transactionRepository.deleteById(id);
    }
}
