package net.eni.gestion.pedagogie.modele.generique;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jollivier
 * Classe abtraite héritée par les modèles
 * @param <ID>
 */
public abstract class AModele <ID> {

	
	/**
	 * Constructeur
	 */
	public AModele() {
	}
	
	/**
	 * @return Clé unique
	 */
	public abstract ID getId();
	
	/**
	 * @param Clé unique
	 */
	public abstract void setId(ID pId);
	
	@JsonIgnore
	public String[] getFullTextSearchFieldNames() {
		return null;
	}
	
	
}
