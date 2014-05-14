package net.eni.gestion.pedagogie.modele.generique;

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
}
