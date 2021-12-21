package fr.insa.ProjetJavaBDD.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.exceptions.FunctionnalProcessException;
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
	public Transaction saveTransaction(TransactionCreateModel transactionToCreate)  throws FunctionnalProcessException
	{
		// Stockage du compte attaché a cette transaction
		Compte compte=compteService.getCompteById(transactionToCreate.getNumCompte());
		
		//Builder de la transaction
		Transaction transaction = Transaction.builder()
				.dateTransac(new Date())
				.montantTransac(transactionToCreate.getMontantTransac())
				.compte(compte)
				.build();
		
		return this.transactionRepository.save(transaction); // sauvegarder
	}
	
	/*
	 * Fonction de suppression d'une transaction
	 */
	public void deleteTransaction(int id) {
        this.transactionRepository.deleteById(id);
    }
}
