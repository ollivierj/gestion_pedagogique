/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import net.sourceforge.jtds.jdbc.DateTime;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.PROMOTION_TABLE_NAME)
@XmlRootElement
public class Promotion extends AModele<String> implements Serializable {
	
	public Promotion() {
		super();
	}

	public Promotion(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodePromotion";
	public final static String LIBELLE_FIELD_NAME				= "Libelle";
	public final static String DEBUT_FIELD_NAME					= "Debut";
	public final static String FIN_FIELD_NAME					= "Fin";
	public final static String CODE_FORMATION_FIELD_NAME		= "CodeFormation";
	public final static String PRIX_PUBLIC_AFFECTE_FIELD_NAME	= "PrixPublicAffecte";
	public final static String DATE_MODIF_FIELD_NAME			= "DateModif";
	public final static String DATE_CREATION_FIELD_NAME			= "DateCreation";
	public final static String PRIX_PEC_AFFECTE_FIELD_NAME		= "PrixPECAffecte";
	public final static String PRIX_FINANCE_AFFECTE_FIELD_NAME	= "PrixFinanceAffecte";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		generatedId = false,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;

	@DatabaseField(
		columnName = DEBUT_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime debut = null;

	@DatabaseField(
		columnName = FIN_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime fin = null;

	@DatabaseField(
		columnName = CODE_FORMATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Formation formation = null;

	@DatabaseField(
		columnName = PRIX_PUBLIC_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicAffecte = null;

	@DatabaseField(
		columnName = DATE_MODIF_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateModif = null;
	
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime dateCreation = null;

	@DatabaseField(
		columnName = PRIX_PEC_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPecAffecte = null;
	
	@DatabaseField(
		columnName = PRIX_FINANCE_AFFECTE_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixFinanceAffecte = null;
	
	@ForeignCollectionField(eager = true, columnName = Cours.CODE_PROMOTION_FIELD_NAME)
	private transient Collection<Cours> transientCours = null;

	private ArrayList<Cours> cours = new ArrayList<Cours>();
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String pId) {
		id = pId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public DateTime getDebut() {
		return debut;
	}

	public void setDebut(DateTime debut) {
		this.debut = debut;
	}

	public DateTime getFin() {
		return fin;
	}

	public void setFin(DateTime fin) {
		this.fin = fin;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Float getPrixPublicAffecte() {
		return prixPublicAffecte;
	}

	public void setPrixPublicAffecte(Float prixPublicAffecte) {
		this.prixPublicAffecte = prixPublicAffecte;
	}

	public Date getDateModif() {
		return dateModif;
	}

	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}

	public DateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(DateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Float getPrixPecAffecte() {
		return prixPecAffecte;
	}

	public void setPrixPecAffecte(Float prixPecAffecte) {
		this.prixPecAffecte = prixPecAffecte;
	}

	public Float getPrixFinanceAffecte() {
		return prixFinanceAffecte;
	}

	public void setPrixFinanceAffecte(Float prixFinanceAffecte) {
		this.prixFinanceAffecte = prixFinanceAffecte;
	}
	
	public ArrayList<Cours> getCours() {
		if (null != transientCours) {
			cours.clear();
			cours.addAll(transientCours);
			transientCours = null;
		}
		return cours;
	}

}
