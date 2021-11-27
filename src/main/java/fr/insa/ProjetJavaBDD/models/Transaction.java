package fr.insa.ProjetJavaBDD.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	@Temporal(TemporalType.DATE)
	private Date dateTransac;
	private int montantTransac;
	@ManyToOne
	private Compte compte;
}
