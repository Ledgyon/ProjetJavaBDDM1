package fr.insa.ProjetJavaBDD.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int id; //ID auto-généré des transactions
	@Temporal(TemporalType.DATE)
	private Date dateTransac; // Date d'une transaction
	private int montantTransac; // Montant d'une transaction
	@JsonIgnore
    @ManyToOne
    private Compte compteBenef; // Compte vers où va la transaction
    @JsonIgnore
    @ManyToOne
    private Compte compteEmetteur; // Compte d'où provient la transaction
}
