package fr.insa.ProjetJavaBDD.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.insa.ProjetJavaBDD.models.Transaction;
import fr.insa.ProjetJavaBDD.repositories.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<Transaction> getOptionalTransactionMontantTransacOrderByDateTransac(Integer montantTransac) {
        return this.transactionRepository.findByMontantTransacOrderByDateTransac(montantTransac);
    } 
}
