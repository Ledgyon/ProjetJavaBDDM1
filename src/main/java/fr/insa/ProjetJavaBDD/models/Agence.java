package fr.insa.ProjetJavaBDD.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

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
public class Agence {
	@Id
	@Size(min=5, max=5)
	private long codeAgence;
	private String adresse;
	@JsonIgnore
	@OneToMany(mappedBy="agence")
	private List<Client> listeClient;
	@JsonIgnore
	@OneToMany(mappedBy="agence")
	private List<Compte> listeCompte;
}
