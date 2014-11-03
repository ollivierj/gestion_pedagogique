/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.DROIT_TABLE_NAME)
@XmlRootElement
public class Droit extends AModele<Integer> implements Serializable {
	public final static String GROUPE_SUJET_EVALUATION  	= "SUJET_EVALUATION";
	public final static String GROUPE_EVALUATION			= "EVALUATION";
	public final static String GROUPE_SESSION_VALIDATION	= "SESSION_VALIDATION";
	public final static String GROUPE_UTILISATEUR			= "UTILISATEUR";
	public final static String GROUPE_PROFIL				= "PROFIL";
	public final static String GROUPE_ABSENCE				= "ABSENCE";
	public final static String GROUPE_STAGIAIRE				= "STAGIAIRE";
	public final static String GROUPE_TITRE_PROFESSIONNEL	= "TITRE_PROFESSIONNEL";
	public final static String GROUPE_PROFESSIONNEL_HOMOLOGUE= "PROFESSIONNEL_HOMOLOGUE";
	public final static String GROUPE_RESERVATION_SALLE		= "RESERVATION_SALLE";
	public final static String GROUPE_AVIS					= "AVIS";
	public final static String GROUPE_ECHANGE				= "ECHANGE";
	
	public final static String DROIT_EDITION_SUJET_EVALUATION   = "SUJ_EVAL_E";
	public final static String DROIT_LECTURE_SUJET_EVALUATION   = "SUJ_EVAL_L";
	public final static String AUCUN_DROIT_SUJET_EVALUATION   = "SUJ_EVAL_A";
	public final static String DROIT_EDITION_EVALUATION   = "EVAL_E";
	public final static String DROIT_LECTURE_EVALUATION   = "EVAL_L";
	public final static String AUCUN_DROIT_EVALUATION   = "EVAL_A";
	public final static String DROIT_ÉCRITURE_SESSION_VALIDATION   = "SES_VAL_E";
	public final static String DROIT_LECTURE_SESSION_VALIDATION   = "SES_VAL_L";
	public final static String AUCUN_DROIT_SESSION_VALIDATION   = "SES_VAL_A";
	public final static String DROIT_EDITION_UTILISATEUR   = "UTIL_E";
	public final static String DROIT_LECTURE_UTILISATEUR   = "UTIL_L";
	public final static String AUCUN_DROIT_UTILISATEUR   = "UTIL_A";
	public final static String DROIT_EDITION_PROFIL   = "PRF_E";
	public final static String DROIT_LECTURE_PROFIL   = "PRF_L";
	public final static String AUCUN_DROIT_PROFIL   = "PRF_A";
	public final static String DROIT_EDITION_ABSENCE   = "ABS_E";
	public final static String DROIT_LECTURE_ABSENCE   = "ABS_L";
	public final static String AUCUN_DROIT_ABSENCE   = "ABS_A";
	public final static String DROIT_EDITION_STAGIAIRE   = "STG_E";
	public final static String DROIT_LECTURE_STAGIAIRE   = "STG_L";
	public final static String AUCUN_DROIT_STAGIAIRE   = "STG_A";
	public final static String DROIT_EDITION_TITRE_PROFESSIONNEL   = "TR_PRF_E";
	public final static String DROIT_LECTURE_TITRE_PROFESSIONNEL   = "TR_PRF_L";
	public final static String AUCUN_DROIT_TITRE_PROFESSIONNEL   = "TR_PRF_A";
	public final static String DROIT_EDITION_PROFESSIONNEL_HOMOLOGUE   = "PRF_HMG_E";
	public final static String DROIT_LECTURE_PROFESSIONNEL_HOMOLOGUE   = "PRF_HMG_L";
	public final static String AUCUN_DROIT_PROFESSIONNEL_HOMOLOGUE   = "PRF_HMG_A";
	public final static String DROIT_EDITION_RESERVATION_SALLE   = "RES_SALLE_E";
	public final static String DROIT_LECTURE_RESERVATION_SALLE   = "RES_SALLE_L";
	public final static String AUCUN_DROIT_RESERVATION_SALLE   = "RES_SALLE_A";
	public final static String DROIT_EDITION_AVIS   = "AVIS_E";
	public final static String DROIT_LECTURE_AVIS   = "AVIS_L";
	public final static String AUCUN_DROIT_AVIS   = "AVIS_A";
	public final static String DROIT_EDITION_ECHANGE   = "ECH_E";
	public final static String DROIT_LECTURE_ECHANGE   = "ECH_L";
	public final static String AUCUN_DROIT_ECHANGE   = "ECH_A";
	
	public Droit() {
		super();
	}

	public Droit(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "DRT_ID";
	public final static String CODE_FIELD_NAME 		= "DRT_CODE";
	public final static String LIBELLE_FIELD_NAME 	= "DRT_LIBELLE";
	public final static String GROUPE_FIELD_NAME	= "DRT_GROUPE";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CODE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String code = null;

	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;
	
	@DatabaseField(
			columnName = GROUPE_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true,
			canBeNull = false)
		private String groupe = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getGroupe() {
		return groupe;
	}

	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

}
