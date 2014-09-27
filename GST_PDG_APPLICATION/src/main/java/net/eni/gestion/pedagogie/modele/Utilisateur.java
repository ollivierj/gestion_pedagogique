/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	
	/*@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = Echange.AUTEUR_FIELD_NAME)
	private transient Collection<Echange> transientEchanges = null;

	private ArrayList<Echange> echanges = new ArrayList<Echange>();*/

	/*@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = Avis.AUTEUR_FIELD_NAME)
	private transient Collection<Avis> transientAvis = null;*/

	/*@SchemaIgnore
	private ArrayList<Avis> avis = new ArrayList<Avis>();*/
	
	/*
	@SchemaIgnore
	@JsonManagedReference(value="absence-utilisateur")
	@ForeignCollectionField(eager = false, columnName = Absence.AUTEUR_FIELD_NAME)
	private transient Collection<Absence> transientAbsences = null;

	@SchemaIgnore
	private ArrayList<Absence> absences = new ArrayList<Absence>();
	*/
	
	/*@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = InstanceEvaluation.CORRECTEUR_FIELD_NAME)
	private transient Collection<InstanceEvaluation> transientInstanceEvaluationCorrections = null;*/

	/*@SchemaIgnore
	private ArrayList<InstanceEvaluation> instanceEvaluationCorrections = new ArrayList<InstanceEvaluation>();

	@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = InstanceEvaluation.SURVEILLANT_FIELD_NAME)
	private transient Collection<InstanceEvaluation> transientInstanceEvaluationSurveillances = null;

	@SchemaIgnore
	private ArrayList<InstanceEvaluation> instanceEvaluationSurveillances = new ArrayList<InstanceEvaluation>();

	@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = InstanceCours.ANIMATEUR_FIELD_NAME)
	private transient Collection<InstanceCours> transientInstanceCours = null;

	@SchemaIgnore
	private ArrayList<InstanceCours> instanceCours = new ArrayList<InstanceCours>();*/
	
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

	/*public ArrayList<Echange> getEchanges() {
		if (null != transientEchanges) {
			echanges.clear();
			echanges.addAll(transientEchanges);
			transientEchanges = null;
		}
		return echanges;
	}
	
	public ArrayList<Avis> getAvis() {
		if (null != transientAvis) {
			avis.clear();
			avis.addAll(transientAvis);
			transientAvis = null;
		}
		return avis;
	}
	
	public ArrayList<InstanceEvaluation> getInstanceEvaluationCorrections() {
		if (null != transientInstanceEvaluationCorrections) {
			instanceEvaluationCorrections.clear();
			instanceEvaluationCorrections.addAll(transientInstanceEvaluationCorrections);
			transientInstanceEvaluationCorrections = null;
		}
		return instanceEvaluationCorrections;
	}
	
	public ArrayList<InstanceEvaluation> getInstanceEvaluationSurveillances() {
		if (null != transientInstanceEvaluationSurveillances) {
			instanceEvaluationSurveillances.clear();
			instanceEvaluationSurveillances.addAll(transientInstanceEvaluationSurveillances);
			transientInstanceEvaluationSurveillances = null;
		}
		return instanceEvaluationSurveillances;
	}*/
	
//	public ArrayList<InstanceCours> getInstanceCours() {
//		if (null != transientInstanceCours) {
//			instanceCours.clear();
//			instanceCours.addAll(transientInstanceCours);
//			transientInstanceCours = null;
//		}
//		return instanceCours;
//	}
	
	/*
	public ArrayList<Absence> getAbsences() {
		if (null != transientAbsences) {
			absences.clear();
			absences.addAll(transientAbsences);
			transientAbsences = null;
		}
		return absences;
	}*/

}
