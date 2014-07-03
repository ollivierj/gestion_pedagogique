/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */

@XmlRootElement
@DatabaseTable(tableName = ModeleMetier.STAGIAIRE_TABLE_NAME)
public class Stagiaire extends AModele<Integer> implements Serializable {
	
	public Stagiaire() {
		super();
	}

	public Stagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CodeStagiaire";
	public final static String CIVILITE_FIELD_NAME 				= "Civilite";
	public final static String NOM_FIELD_NAME					= "Nom";
	public final static String PRENOM_FIELD_NAME 				= "Prenom";
	public final static String ADRESSE1_FIELD_NAME	 			= "Adresse1";
	public final static String ADRESSE2_FIELD_NAME	 			= "Adresse2";
	public final static String ADRESSE3_FIELD_NAME	 			= "Adresse3";
	public final static String CODE_POSTAL_FIELD_NAME	 		= "Codepostal";
	public final static String VILLE_FIELD_NAME	 				= "Ville";
	public final static String TELEPHONE_FIXE_FIELD_NAME	 	= "TelephoneFixe";
	public final static String TELEPHONE_PORTABLE_FIELD_NAME	= "TelephonePortable";
	public final static String EMAIL_FIELD_NAME	 				= "Email";
	public final static String DATE_NAISSANCE_FIELD_NAME	 	= "DateNaissance";
	public final static String CODE_REGION_FIELD_NAME	 		= "CodeRegion";
	public final static String CODE_NATIONALITE_FIELD_NAME	 		= "CodeNationalite";
	public final static String CODE_ORIGINE_MEDIA_FIELD_NAME	= "CodeOrigineMedia";
	public final static String DATE_DERNIER_ENVOI_DOC_FIELD_NAME= "DateDernierEnvoiDoc";
	public final static String DATE_CREATION_FIELD_NAME	 		= "DateCreation";
	public final static String REPERTOIRE_FIELD_NAME	 		= "Repertoire";
	public final static String PERMIS_FIELD_NAME	 			= "Permis";
	public final static String PHOTO_FIELD_NAME	 				= "Photo";
	public final static String ENVOI_DOC_EN_COURS_FIELD_NAME	= "EnvoiDocEnCours";
	public final static String HISTORIQUE_FIELD_NAME			= "Historique";
	
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
		useGetSet = true,
		canBeNull = false)
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

	@DatabaseField(
		columnName = DATE_NAISSANCE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
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
		columnName = CODE_ORIGINE_MEDIA_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codeOrigineMedia = null;
	
	@DatabaseField(
		columnName = DATE_DERNIER_ENVOI_DOC_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date dateDernierEnvoiDoc = null;
	
	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date dateCreation = null;

	@DatabaseField(
		columnName = REPERTOIRE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String repertoire = null;

	@DatabaseField(
		columnName = PERMIS_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean permis = null;
	
	@DatabaseField(
		columnName = PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String photo = null;

	@DatabaseField(
		columnName = ENVOI_DOC_EN_COURS_FIELD_NAME,
		dataType = DataType.BOOLEAN_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Boolean envoiDocEnCours = null;

	@DatabaseField(
		columnName = HISTORIQUE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String historique = null;
	
	@ForeignCollectionField(eager = true, columnName = Echange.STAGIAIRE_FIELD_NAME)
	private transient Collection<Echange> transientEchanges = null;

	private ArrayList<Echange> echanges = new ArrayList<Echange>();

	@ForeignCollectionField(eager = true, columnName = Avis.STAGIAIRE_FIELD_NAME)
	private transient Collection<Avis> transientAvis = null;

	private ArrayList<Avis> avis = new ArrayList<Avis>();
	
	@ForeignCollectionField(eager = true, columnName = Absence.STAGIAIRE_FIELD_NAME)
	private transient Collection<Absence> transientAbsences = null;

	private ArrayList<Absence> absences = new ArrayList<Absence>();
	
	/*@ForeignCollectionField(eager = true, columnName = PlanningIndividuelFormation.CODE_STAGIAIRE_FIELD_NAME)
	private transient Collection<PlanningIndividuelFormation> transientPlanningIndividuelFormations = null;

	private ArrayList<PlanningIndividuelFormation> planningIndividuelFormations = new ArrayList<PlanningIndividuelFormation>();
	 */
	@ForeignCollectionField(eager = true, columnName = InstanceCoursStagiaire.STAGIAIRE_FIELD_NAME)
	private transient Collection<InstanceCoursStagiaire> transientInstanceCoursStagiaires = null;

	private ArrayList<InstanceCoursStagiaire> instanceCoursStagiaires = new ArrayList<InstanceCoursStagiaire>();

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
	
	public String getFormatedDateNaissance(){
		return (null!=dateNaissance)? DateFormatUtils.format(dateNaissance, "dd/MM/yyyy"): null;
	}
	
	public void setFormatedDateNaissance(String formatedDateNaissance){
		try {
			if (null != formatedDateNaissance){
				this.dateNaissance = DateUtils.parseDate(formatedDateNaissance, "dd/MM/yyyy");				
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public String getCodeOrigineMedia() {
		return codeOrigineMedia;
	}

	public void setCodeOrigineMedia(String codeOrigineMedia) {
		this.codeOrigineMedia = codeOrigineMedia;
	}

	public Date getDateDernierEnvoiDoc() {
		return dateDernierEnvoiDoc;
	}

	public String getFormatedDateDernierEnvoiDoc(){
		return (null!=dateDernierEnvoiDoc)? DateFormatUtils.format(dateDernierEnvoiDoc, "dd/MM/yyyy H:mm:ss"): null;
	}
	
	public void setDateDernierEnvoiDoc(Date dateDernierEnvoiDoc) {
		this.dateDernierEnvoiDoc = dateDernierEnvoiDoc;
	}

	public Date getDateCreation() {
		return dateCreation;
	}
	
	public String getFormatedDateCreation(){
		return (null!=dateCreation)? DateFormatUtils.format(dateCreation, "dd/MM/yyyy H:mm:ss"): null;
	}
	

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(String repertoire) {
		this.repertoire = repertoire;
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

	public Boolean getEnvoiDocEnCours() {
		return envoiDocEnCours;
	}

	public void setEnvoiDocEnCours(Boolean envoiDocEnCours) {
		this.envoiDocEnCours = envoiDocEnCours;
	}

	public String getHistorique() {
		return historique;
	}

	public void setHistorique(String historique) {
		this.historique = historique;
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
	
	public ArrayList<Absence> getAbsences() {
		if (null != transientAbsences) {
			absences.clear();
			absences.addAll(transientAbsences);
			transientAbsences = null;
		}
		return absences;
	}
	
	private ArrayList<InstanceCoursStagiaire> getInstanceCoursStagiaires() {
		if (null != transientInstanceCoursStagiaires) {
			instanceCoursStagiaires.clear();
			instanceCoursStagiaires.addAll(transientInstanceCoursStagiaires);
			transientInstanceCoursStagiaires = null;
		}
		return instanceCoursStagiaires;
	}
	
	public ArrayList<InstanceCours> getInstanceCours(){
		ArrayList<InstanceCours> instanceCours = new ArrayList<InstanceCours>();
		if (0 < getInstanceCoursStagiaires().size()) {
			Iterator<InstanceCoursStagiaire> iterator = getInstanceCoursStagiaires().iterator();
			while (iterator.hasNext()) {
				instanceCours.add(iterator.next().getInstanceCours());
			}
		}
		return instanceCours;
	}

}
