/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
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
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= {ID_FIELD_NAME};
	
	@JsonIgnore
	@Override
	public String[] getFullTextSearchFieldNames() {
		return FULL_TEXT_SEARCH_FIELDS;
	}
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
	@DatabaseField(
		columnName = DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date debut = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
	@DatabaseField(
		columnName = FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date fin = null;

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

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateCreation = null;

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
	
	private ArrayList<StagiairePromotion> stagiaires = new ArrayList<StagiairePromotion>();
	
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

	public Date getDebut() {
		return debut;
	}
	
	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}
	
	public void setFin(Date fin) {
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

	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
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
	
	public ArrayList<StagiairePromotion> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(ArrayList<StagiairePromotion> stagiaires) {
		this.stagiaires = stagiaires;
	}

	


	


}
