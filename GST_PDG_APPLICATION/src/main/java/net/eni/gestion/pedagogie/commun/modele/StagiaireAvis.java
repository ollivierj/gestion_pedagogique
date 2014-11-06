/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.STAGIAIRE_AVIS_TABLE_NAME)
@XmlRootElement
public class StagiaireAvis extends AModele<Integer> implements Serializable {
	
	public StagiaireAvis() {
		super();
	}

	public StagiaireAvis(Integer pId) {
		
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "STG_AVIS_STAGIAIRE_ID";
	public final static String STAGIAIRE_PRENOM_FIELD_NAME 			= "STG_AVIS_STAGIAIRE_PRENOM";
	public final static String STAGIAIRE_NOM_FIELD_NAME 			= "STG_AVIS_STAGIAIRE_NOM";
	public final static String STAGIAIRE_PHOTO_FIELD_NAME			= "STG_AVIS_STAGIAIRE_PHOTO";
	public final static String PROMOTION_CODE_FIELD_NAME			= "STG_AVIS_PROMOTION_CODE";
	public final static String PROMOTION_LIBELLE_FIELD_NAME 		= "STG_AVIS_PROMOTION_LIBELLE";
	public final static String AVIS_ID_FIELD_NAME 					= "STG_AVIS_AVIS_ID";
	public final static String AVIS_TEXTE_FIELD_NAME 				= "STG_AVIS_AVIS_TEXTE";
	public final static String AVIS_DATE_FIELD_NAME 				= "STG_AVIS_AVIS_DATE";
	public final static String AVIS_AUTEUR_FIELD_NAME 				= "STG_AVIS_AVIS_AUTEUR_ID";
	public final static String AVIS_INSTANCE_COURS_FIELD_NAME 		= "STG_AVIS_INSTANCE_COURS_ID";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = STAGIAIRE_PRENOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = true)
	private String stagiairePrenom = null;
	
	@DatabaseField(
		columnName = STAGIAIRE_NOM_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = true)
	private String stagiaireNom = null;

	@DatabaseField(
		columnName = STAGIAIRE_PHOTO_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = true)
	private String stagiairePhoto = null;

	@DatabaseField(
		columnName = PROMOTION_CODE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = true)
	private String promotionCode = null;

	@DatabaseField(
		columnName = PROMOTION_LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = true)
	private String promotionLibelle = null;
	
	@DatabaseField(
		columnName = AVIS_ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = true)
	private Integer avisId = null;

	@DatabaseField(
		columnName = AVIS_TEXTE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = true)
	private String avisTexte = null;

	@DatabaseField(
		columnName = AVIS_DATE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = true)
	private Date avisDate = null;

	@DatabaseField(
		columnName = AVIS_AUTEUR_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = true)
	private Integer avisAuteurId = null;

	@DatabaseField(
		columnName = AVIS_INSTANCE_COURS_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = true)
	private Integer avisInstanceCoursId = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}
	
	@Override
	public String toString() {
		return null;
	}

	public String getStagiairePrenom() {
		return stagiairePrenom;
	}

	public void setStagiairePrenom(String stagiairePrenom) {
		this.stagiairePrenom = stagiairePrenom;
	}

	public String getStagiaireNom() {
		return stagiaireNom;
	}

	public void setStagiaireNom(String stagiaireNom) {
		this.stagiaireNom = stagiaireNom;
	}

	public String getStagiairePhoto() {
		return stagiairePhoto;
	}

	public void setStagiairePhoto(String stagiairePhoto) {
		this.stagiairePhoto = stagiairePhoto;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPromotionLibelle() {
		return promotionLibelle;
	}

	public void setPromotionLibelle(String promotionLibelle) {
		this.promotionLibelle = promotionLibelle;
	}

	public Integer getAvisId() {
		return avisId;
	}

	public void setAvisId(Integer avisId) {
		this.avisId = avisId;
	}

	public String getAvisTexte() {
		return avisTexte;
	}

	public void setAvisTexte(String avisTexte) {
		this.avisTexte = avisTexte;
	}

	public Date getAvisDate() {
		return avisDate;
	}

	public void setAvisDate(Date avisDate) {
		this.avisDate = avisDate;
	}

	public Integer getAvisAuteurId() {
		return avisAuteurId;
	}

	public void setAvisAuteurId(Integer avisAuteurId) {
		this.avisAuteurId = avisAuteurId;
	}

	public Integer getAvisInstanceCoursId() {
		return avisInstanceCoursId;
	}

	public void setAvisInstanceCoursId(Integer avisInstanceCoursId) {
		this.avisInstanceCoursId = avisInstanceCoursId;
	}
}
