package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@XmlRootElement
@DatabaseTable(tableName = ModeleMetier.STAGIAIREPROMO_VIEW_NAME)
public class StagiairePromotion extends AModele<Integer> implements Serializable {

	
	public StagiairePromotion() {
		super();
	}

	public StagiairePromotion(Integer pId) {
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
	public final static String CODE_NATIONALITE_FIELD_NAME	 	= "CodeNationalite";
	public final static String CODE_ORIGINE_MEDIA_FIELD_NAME	= "CodeOrigineMedia";
	public final static String DATE_DERNIER_ENVOI_DOC_FIELD_NAME= "DateDernierEnvoiDoc";
	public final static String DATE_CREATION_FIELD_NAME	 		= "DateCreation";
	public final static String REPERTOIRE_FIELD_NAME	 		= "Repertoire";
	public final static String PERMIS_FIELD_NAME	 			= "Permis";
	public final static String PHOTO_FIELD_NAME	 				= "Photo";
	public final static String ENVOI_DOC_EN_COURS_FIELD_NAME	= "EnvoiDocEnCours";
	public final static String HISTORIQUE_FIELD_NAME			= "Historique";
	public final static String ID_PROMO_NAME 					= "CodePromotion";
	public final static String LIBELLE_FIELD_NAME				= "Libelle";
	public final static String DEBUT_FIELD_NAME					= "Debut";
	public final static String FIN_FIELD_NAME					= "Fin";
		
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
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

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
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
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_DERNIER_ENVOI_DOC_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true)
	private Date dateDernierEnvoiDoc = null;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
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
	
	@DatabaseField(
		columnName = ID_PROMO_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String codePromotion = null;
	
	@DatabaseField(
			columnName = ID_PROMO_NAME,
			foreign = true,
			useGetSet = true,
			canBeNull = false)
		private Promotion promotion = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libellePromotion = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
	@DatabaseField(
		columnName = DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date debutPromotion = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone="CET")   
	@DatabaseField(
		columnName = FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date finPromotion = null;
	
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

	public String getCodeOrigineMedia() {
		return codeOrigineMedia;
	}

	public void setCodeOrigineMedia(String codeOrigineMedia) {
		this.codeOrigineMedia = codeOrigineMedia;
	}

	public Date getDateDernierEnvoiDoc() {
		return dateDernierEnvoiDoc;
	}

	public void setDateDernierEnvoiDoc(Date dateDernierEnvoiDoc) {
		this.dateDernierEnvoiDoc = dateDernierEnvoiDoc;
	}

	public Date getDateCreation() {
		return dateCreation;
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

	public String getCodePromotion() {
		return codePromotion;
	}

	public void setCodePromotion(String codePromotion) {
		this.codePromotion = codePromotion;
	}

	public Date getDebutPromotion() {
		return debutPromotion;
	}

	public void setDebutPromotion(Date debutPromotion) {
		this.debutPromotion = debutPromotion;
	}

	public Date getFinPromotion() {
		return finPromotion;
	}

	public void setFinPromotion(Date finPromotion) {
		this.finPromotion = finPromotion;
	}

	public String getLibellePromotion() {
		return libellePromotion;
	}

	public void setLibellePromotion(String libellePromotion) {
		this.libellePromotion = libellePromotion;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}	
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getPhoto())?getPhoto():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getDateDernierEnvoiDoc())?getDateDernierEnvoiDoc():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCodePromotion())?getCodePromotion():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getLibellePromotion())?getLibellePromotion():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getDebutPromotion())?getDebutPromotion():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getFinPromotion())?getFinPromotion():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getHistorique())?getHistorique():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getRepertoire())?getRepertoire():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCivilite())?getCivilite():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getPrenom())?getPrenom():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getNom())?getNom():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getDateNaissance())?getDateNaissance():"");
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
