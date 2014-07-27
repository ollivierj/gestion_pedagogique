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
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = FONCTION_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Fonction fonction = null;

	@DatabaseField(
		columnName = CIVILITE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String civilite = null;

	@DatabaseField(
		columnName = NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String nom = null;

	@DatabaseField(
		columnName = PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String prenom = null;

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
		useGetSet = true,
		canBeNull = false)
	private String email = null;

	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;
	
	@DatabaseField(
		columnName = MOT_PASSE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String motPasse = null;

	@DatabaseField(
		columnName = PROFIL_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer profil = null;
	
	@ForeignCollectionField(eager = true, columnName = Echange.AUTEUR_FIELD_NAME)
	private transient Collection<Echange> transientEchanges = null;

	private ArrayList<Echange> echanges = new ArrayList<Echange>();

	@ForeignCollectionField(eager = true, columnName = Avis.AUTEUR_FIELD_NAME)
	private transient Collection<Avis> transientAvis = null;

	private ArrayList<Avis> avis = new ArrayList<Avis>();
	
	@ForeignCollectionField(eager = true, columnName = InstanceEvaluation.CORRECTEUR_FIELD_NAME)
	private transient Collection<InstanceEvaluation> transientInstanceEvaluationCorrections = null;

	private ArrayList<InstanceEvaluation> instanceEvaluationCorrections = new ArrayList<InstanceEvaluation>();

	@ForeignCollectionField(eager = true, columnName = InstanceEvaluation.SURVEILLANT_FIELD_NAME)
	private transient Collection<InstanceEvaluation> transientInstanceEvaluationSurveillances = null;

	private ArrayList<InstanceEvaluation> instanceEvaluationSurveillances = new ArrayList<InstanceEvaluation>();

	@ForeignCollectionField(eager = true, columnName = InstanceCours.ANIMATEUR_FIELD_NAME)
	private transient Collection<InstanceCours> transientInstanceCours = null;

	private ArrayList<InstanceCours> instanceCours = new ArrayList<InstanceCours>();
	
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

	public Integer getProfil() {
		return profil;
	}

	public void setProfil(Integer profil) {
		this.profil = profil;
	}

	public ArrayList<Echange> getEchanges() {
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
	}
	
	public ArrayList<InstanceCours> getInstanceCours() {
		if (null != transientInstanceCours) {
			instanceCours.clear();
			instanceCours.addAll(transientInstanceCours);
			transientInstanceCours = null;
		}
		return instanceCours;
	}

}
