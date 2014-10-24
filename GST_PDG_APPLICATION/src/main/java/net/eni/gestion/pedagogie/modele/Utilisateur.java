/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.UTILISATEUR_TABLE_NAME)
@XmlRootElement
public class Utilisateur extends AModele<Integer> implements Serializable {
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "UTIL_ID";
	public final static String FONCTION_FIELD_NAME			= "UTIL_FONCTION";
	public final static String CIVILITE_FIELD_NAME			= "UTIL_CIVILITE";
	public final static String NOM_FIELD_NAME				= "UTIL_NOM";
	public final static String PRENOM_FIELD_NAME			= "UTIL_PRENOM";
	public final static String TELEPHONE_FIXE_FIELD_NAME	= "UTIL_TELEPHONE_FIXE";
	public final static String TELEPHONE_PORTABLE_FIELD_NAME= "UTIL_TELEPHONE_PORTABLE";
	public final static String EMAIL_FIELD_NAME				= "UTIL_EMAIl";
	public final static String PHOTO_FIELD_NAME				= "UTIL_PHOTO";
	public final static String MOT_PASSE_FIELD_NAME			= "UTIL_MOT_PASSE";
	public final static String PROFIL_FIELD_NAME			= "UTIL_PROFIL";
	public final static String LOGIN_FIELD_NAME				= "UTIL_LOGIN";
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= {NOM_FIELD_NAME, PRENOM_FIELD_NAME, EMAIL_FIELD_NAME};
	
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
	
	@DatabaseField(
		columnName = FONCTION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		foreignAutoRefresh = true)
	private Fonction fonction = null;

	@Attributes(title = "Civilité", required = true, maxLength = 3, enums={"M  ","Mme"})
	@DatabaseField(
		columnName = CIVILITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String civilite = null;

	@Attributes(title = "Nom", required = true, maxLength = 50)
	@DatabaseField(
		columnName = NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String nom = null;

	@Attributes(title = "Prénom", required = true, maxLength = 50)
	@DatabaseField(
		columnName = PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String prenom = null;

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
		useGetSet = true,
		canBeNull = false)
	private String email = null;

	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;
	
	@Attributes(title = "Mot de passe", required = true, maxLength = 30, pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W).{7,30}$", validationMessage="La syntaxe du mot de passe est incorrecte.<br>Les règles suivantes doivent être respectées :<br>&nbsp;Longueur minimale: 7 caractères;<br>&nbsp;Au moins un chiffre;<br>&nbsp;Au moins une lettre;<br>&nbsp;Au moins une majuscule et une minuscule.", format = "password")
	@DatabaseField(
		columnName = MOT_PASSE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String motPasse = null;

	@DatabaseField(
		columnName = PROFIL_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Profil profil = null;
	
	
	@Attributes(title = "Login", required = true, maxLength = 50)
	@DatabaseField(
		columnName = LOGIN_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String login = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMotPasse() {
		return motPasse;
	}

	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getLogin())?getLogin():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getProfil())?(null!=getProfil().getLibelle())?getProfil().getLibelle():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getFonction())?(null!=getFonction().getLibelle())?getFonction().getLibelle():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getCivilite())?getCivilite():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getPrenom())?getPrenom():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getNom())?getNom():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getEmail())?getEmail():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getTelephoneFixe())?getTelephoneFixe():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getTelephonePortable())?getTelephonePortable():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getPhoto())?getPhoto():"");
		lStrStringBuilder.append(" ");
		return lStrStringBuilder.toString();
	}	
	

}
