/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.PROFESSIONNEL_HOMOLOGUE_TABLE_NAME)
@XmlRootElement
public class ProfessionnelHomologue extends AModele<Integer> implements Serializable {
	
	public ProfessionnelHomologue() {
		super();
	}

	public ProfessionnelHomologue(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "PRF_HMG_ID";
	public final static String CIVILITE_FIELD_NAME 				= "PRF_HMG_CIVILITE";
	public final static String NOM_FIELD_NAME					= "PRF_HMG_NOM";
	public final static String PRENOM_FIELD_NAME 				= "PRF_HMG_PRENOM";
	public final static String ADRESSE1_FIELD_NAME	 			= "PRF_HMG_ADRESSE1";
	public final static String ADRESSE2_FIELD_NAME	 			= "PRF_HMG_ADRESSE2";
	public final static String ADRESSE3_FIELD_NAME	 			= "PRF_HMG_ADRESSE3";
	public final static String CODE_POSTAL_FIELD_NAME	 		= "PRF_HMG_CODE_POSTAL";
	public final static String VILLE_FIELD_NAME	 				= "PRF_HMG_VILLE";
	public final static String TELEPHONE_FIXE_FIELD_NAME	 	= "PRF_HMG_TELEPHONE_FIXE";
	public final static String TELEPHONE_PORTABLE_FIELD_NAME	= "PRF_HMG_TELEPHONE_PORTABLE";
	public final static String EMAIL_FIELD_NAME	 				= "PRF_HMG_EMAIL";
	public final static String DATE_NAISSANCE_FIELD_NAME	 	= "PRF_HMG_DATE_NAISSANCE";
	public final static String CODE_REGION_FIELD_NAME	 		= "PRF_HMG_CODE_REGION_NAISSANCE";
	public final static String CODE_NATIONALITE_FIELD_NAME	 	= "PRF_HMG_NATIONALITE";
	public final static String PERMIS_FIELD_NAME	 			= "PRF_HMG_PERMIS";
	public final static String PHOTO_FIELD_NAME	 				= "PRF_HMG_PHOTO";
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= {NOM_FIELD_NAME, PRENOM_FIELD_NAME, CODE_POSTAL_FIELD_NAME, VILLE_FIELD_NAME};
	
	@Override
	public String[] getFullTextSearchFieldNames() {
		return FULL_TEXT_SEARCH_FIELDS;
	}
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CIVILITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String civilite = null;

	@DatabaseField(
		columnName = NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String nom = null;

	@DatabaseField(
		columnName = PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String prenom = null;

	@DatabaseField(
		columnName = ADRESSE1_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse1 = null;

	@DatabaseField(
		columnName = ADRESSE2_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse2 = null;

	@DatabaseField(
		columnName = ADRESSE3_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse3 = null;

	@DatabaseField(
		columnName = CODE_POSTAL_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codePostal = null;

	@DatabaseField(
		columnName = VILLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String ville = null;

	@DatabaseField(
		columnName = TELEPHONE_FIXE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String telephoneFixe = null;

	@DatabaseField(
		columnName = TELEPHONE_PORTABLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String telephonePortable = null;

	@DatabaseField(
		columnName = EMAIL_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String email = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="CET")   
	@DatabaseField(
		columnName = DATE_NAISSANCE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = false)
	private Date dateNaissance = null;

	@DatabaseField(
		columnName = CODE_REGION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeRegion = null;

	@DatabaseField(
		columnName = CODE_NATIONALITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeNationalite = null;

	@DatabaseField(
		columnName = PERMIS_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true)
	private Boolean permis = null;
	
	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;
		
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getAdresse3() {
		return adresse3;
	}

	public void setAdresse3(String adresse3) {
		this.adresse3 = adresse3;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTelephoneFixe() {
		return telephoneFixe;
	}

	public void setTelephoneFixe(String telephoneFixe) {
		this.telephoneFixe = telephoneFixe;
	}

	public String getTelephonePortable() {
		return telephonePortable;
	}

	public void setTelephonePortable(String telephonePortable) {
		this.telephonePortable = telephonePortable;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}
	
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	public String getCodeNationalite() {
		return codeNationalite;
	}

	public void setCodeNationalite(String codeNationalite) {
		this.codeNationalite = codeNationalite;
	}

	public Boolean getPermis() {
		return permis;
	}

	public void setPermis(Boolean permis) {
		this.permis = permis;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
