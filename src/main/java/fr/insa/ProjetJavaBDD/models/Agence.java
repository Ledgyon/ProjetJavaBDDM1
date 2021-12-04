package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {
	@Id
	@Size(min=5, max=5)
	private int Code_agence;
	private String Adresse;
	@OneToMany(mappedBy="agence")
	private List<Client> ListeClient;
	@OneToMany(mappedBy="agence")
	private List<Compte> ListeCompte;
}
