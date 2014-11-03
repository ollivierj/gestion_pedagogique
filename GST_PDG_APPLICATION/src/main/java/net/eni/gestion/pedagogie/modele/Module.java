/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.outil.DateHelper;
import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.MODULE_TABLE_NAME)
@XmlRootElement
public class Module extends AModele<Integer> implements Serializable {
	
	public Module() {
		super();
	}

	public Module(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "IdModule";
	public final static String LIBELLE_FIELD_NAME 				= "Libelle";
	public final static String DUREE_EN_HEURES_FIELD_NAME		= "DureeEnHeures";
	public final static String DATE_CREATION_FIELD_NAME 		= "DateCreation";
	public final static String DUREE_EN_SEMAINES_FIELD_NAME 	= "DureeEnSemaines";
	public final static String PRIX_PUBLIC_EN_COURS_FIELD_NAME 	= "PrixPublicEnCours";
	public final static String LIBELLE_COURT_FIELD_NAME 		= "LibelleCourt";
	public final static String DATE_MODIF_FIELD_NAME 			= "DateModif";
	public final static String ARCHIVER_FIELD_NAME 				= "Archiver";
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= {LIBELLE_FIELD_NAME,LIBELLE_COURT_FIELD_NAME};
	
	@JsonIgnore
	@Override
	public String[] getFullTextSearchFieldNames() {
		return FULL_TEXT_SEARCH_FIELDS;
	}
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		useGetSet = true)
	private Integer id = null;
	
	@Attributes(title = "Libellé", required = true, maxLength = 200)
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;

	@Attributes(title = "Durée en heures", required = true)
	@DatabaseField(
		columnName = DUREE_EN_HEURES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnHeures = null;

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateCreation = null;
	
	@Attributes(title = "Date de création", required = true, format = "date")
	private String formatedDateCreation;
	
	@Attributes(title = "Durée en semaines", required = true)
	@DatabaseField(
		columnName = DUREE_EN_SEMAINES_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short dureeEnSemaines = null;
	
	@Attributes(title = "Prix public", required = true)
	@DatabaseField(
		columnName = PRIX_PUBLIC_EN_COURS_FIELD_NAME,
		dataType = DataType.FLOAT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Float prixPublicEnCours = null;
	
	@Attributes(title = "Libellé court", required = true, maxLength = 20)
	@DatabaseField(
		columnName = LIBELLE_COURT_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelleCourt = null;
	
	@Attributes(title = "Archiver", required = true)
	@DatabaseField(
		columnName = ARCHIVER_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean archiver = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Short getDureeEnHeures() {
		return dureeEnHeures;
	}

	public void setDureeEnHeures(Short dureeEnHeures) {
		this.dureeEnHeures = dureeEnHeures;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.formatedDateCreation=DateHelper.stringifyDate(dateCreation, "yyyy-MM-dd");
		this.dateCreation = dateCreation;
	}
	
	public String getFormatedDateCreation() {
		return formatedDateCreation;
	}

	public void setFormatedDateCreation(String formatedDateCreation) throws ParseException {
		this.dateCreation= DateHelper.datifyString(formatedDateCreation, "yyyy-MM-dd");
		this.formatedDateCreation = formatedDateCreation;
	}
	

	public Short getDureeEnSemaines() {
		return dureeEnSemaines;
	}

	public void setDureeEnSemaines(Short dureeEnSemaines) {
		this.dureeEnSemaines = dureeEnSemaines;
	}

	public Float getPrixPublicEnCours() {
		return prixPublicEnCours;
	}

	public void setPrixPublicEnCours(Float prixPublicEnCours) {
		this.prixPublicEnCours = prixPublicEnCours;
	}

	public String getLibelleCourt() {
		return libelleCourt;
	}

	public void setLibelleCourt(String libelleCourt) {
		this.libelleCourt = libelleCourt;
	}

	public Boolean getArchiver() {
		return archiver;
	}

	public void setArchiver(Boolean archiver) {
		this.archiver = archiver;
	}

}
