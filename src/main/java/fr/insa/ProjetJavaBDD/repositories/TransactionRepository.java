package fr.insa.ProjetJavaBDD.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insa.ProjetJavaBDD.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    //public Optional<Transaction> findById/*OrderByDate_Transac*/(Integer ID);
	public List<Transaction> findByMontantTransacOrderByDateTransac(Integer montantTransac);
}
