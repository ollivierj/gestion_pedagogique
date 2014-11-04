/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.commun.outil.DateHelper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.SchemaIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
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
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= {NOM_FIELD_NAME, PRENOM_FIELD_NAME, EMAIL_FIELD_NAME, CODE_POSTAL_FIELD_NAME, VILLE_FIELD_NAME};
	
	@JsonIgnore
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
		
	@Attributes(title = "Civilité", required = true, maxLength = 3, enums={"M  ","Mme"})	
	@DatabaseField(
		columnName = CIVILITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String civilite = null;

	@Attributes(title = "Nom", required = true, maxLength = 50)
	@DatabaseField(
		columnName = NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String nom = null;

	@Attributes(title = "Prénom", required = true, maxLength = 50)
	@DatabaseField(
		columnName = PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String prenom = null;

	@Attributes(title = "Adresse 1", required = true, maxLength = 500)
	@DatabaseField(
		columnName = ADRESSE1_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse1 = null;

	@Attributes(title = "Adresse 2", required = false, maxLength = 500)
	@DatabaseField(
		columnName = ADRESSE2_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse2 = null;

	@Attributes(title = "Adresse 3", required = false, maxLength = 500)
	@DatabaseField(
		columnName = ADRESSE3_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String adresse3 = null;

	@Attributes(title = "Code Postal", required = true, maxLength = 5, pattern = "^(([0-8][0-9])|(9[0-5]))[0-9]{3}$", validationMessage="Le code postal respecter la forme suivante : \"XXXXX\" ou \"2A\" ou \"2B\"")
	@DatabaseField(
		columnName = CODE_POSTAL_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codePostal = null;

	@Attributes(title = "Ville", required = true, maxLength = 100)
	@DatabaseField(
		columnName = VILLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String ville = null;

	@Attributes(title = "Téléphone fixe", required = false, maxLength = 14, pattern = "^0[1-68][0-9]{8}$", validationMessage="Le numéro de téléphone respecter la forme suivante : \"XXXXXXXXXX\"")
	@DatabaseField(
		columnName = TELEPHONE_FIXE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String telephoneFixe = null;

	@Attributes(title = "Téléphone portable", required = false, maxLength = 14, pattern = "^0[1-68][0-9]{8}$", validationMessage="Le numéro de téléphone doit respecter la forme suivante : \"XXXXXXXXXX\"")
	@DatabaseField(
		columnName = TELEPHONE_PORTABLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String telephonePortable = null;

	@Attributes(title = "Email", required = true, maxLength = 100, pattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$", validationMessage="L'adresse email doit respecter la forme suivante : \"xxxxx@xxxxx.xxx\"")
	@DatabaseField(
		columnName = EMAIL_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String email = null;

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_NAISSANCE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date dateNaissance = null;
	
	@Attributes(title = "Date de naissance", required = false, format = "date")
	private String formatedDateNaissance;

	@Attributes(title = "Code région de naissance", required = false, maxLength = 2)
	@DatabaseField(
		columnName = CODE_REGION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeRegion = null;

	@Attributes(title = "Code nationalité", required = false, maxLength = 2)
	@DatabaseField(
		columnName = CODE_NATIONALITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeNationalite = null;

	@Attributes(title = "Permis", required = false)
	@DatabaseField(
		columnName = PERMIS_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true)
	private Boolean permis = null;
	
	@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = Homologation.PROFESSIONNEL_HOMOLOGUE_FIELD_NAME)
	private transient Collection<Homologation> transientHomologations = null;
	
	public Collection<Homologation> getTransientHomologations() {
		return transientHomologations;
	}

	public void setTransientHomologations(
			Collection<Homologation> transientHomologations) {
		this.transientHomologations = transientHomologations;
	}

	@JsonManagedReference("ProfessionnelHomologue-Homologations")
	@Attributes(id = "homologations")
	private ArrayList<Homologation> homologations = new ArrayList<Homologation>();
	
	public ArrayList<Homologation> getHomologations() {
		if (null != transientHomologations) {
			homologations.clear();
			homologations.addAll(transientHomologations);
			transientHomologations = null;
		}
		return homologations;
	}

	public void setHomologations(ArrayList<Homologation> homologations) {
		this.homologations = homologations;
	}
	
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
		this.formatedDateNaissance=DateHelper.stringifyDate(dateNaissance, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateNaissance = dateNaissance;
	}
	
	public String getFormatedDateNaissance() {
		return formatedDateNaissance;
	}

	public void setFormatedDateNaissance(String formatedDateNaissance) throws ParseException {
		this.dateNaissance= DateHelper.datifyString(formatedDateNaissance, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateNaissance = formatedDateNaissance;
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
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getCivilite())?getCivilite():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getPrenom())?getPrenom():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getNom())?getNom():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getFormatedDateNaissance())?getFormatedDateNaissance():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCodeNationalite())?getCodeNationalite():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAdresse1())?getAdresse1():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getAdresse2())?getAdresse2():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAdresse3())?getAdresse3():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getVille())?getVille():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCodePostal())?getCodePostal():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCodeRegion())?getCodeRegion():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getEmail())?getEmail():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getPermis())?(true==getPermis())?"Oui":"Non":"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getTelephoneFixe())?getTelephoneFixe():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getTelephonePortable())?getTelephonePortable():"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}



}
