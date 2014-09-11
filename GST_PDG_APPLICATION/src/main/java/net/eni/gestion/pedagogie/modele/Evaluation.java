/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.EVALUATION_TABLE_NAME)
@XmlRootElement
public class Evaluation extends AModele<Integer> implements Serializable {
	
	public Evaluation() {
		super();
	}

	public Evaluation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "EVAL_ID";
	public final static String AUTEUR_FIELD_NAME 					= "EVAL_AUTEUR";
	public final static String DATE_CREATION_FIELD_NAME 			= "DATE_CREATION_AUTEUR";
	public final static String SUJET_EVALUATION_FIELD_NAME			= "EVAL_SUJET_EVALUATION";
	public final static String DATE_HEURE_DEBUT_PASSAGE				= "EVAL_DATE_HEURE_DEBUT_PASSAGE";
	public final static String DATE_HEURE_FIN_PASSAGE				= "EVAL_DATE_HEURE_FIN_PASSAGE";
	public final static String LIEN_GRILLE_CORRECTION_FIELD_NAME	= "EVAL_LIEN_GRILLE_CORRECTION";
	public final static String LIEN_COPIES_IMMATERIELLES_FIELD_NAME	= "EVAL_LIEN_COPIES_IMMATERIELLES";
	public final static String CORRECTEUR_FIELD_NAME				= "EVAL_CORRECTEUR";
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= null;
	
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
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur auteur = null;

	@DatabaseField(
		columnName = DATE_CREATION_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private Date dateCreation = null;

	@DatabaseField(
			columnName = DATE_HEURE_DEBUT_PASSAGE,
			dataType = DataType.DATE_TIME,
			useGetSet = true,
			canBeNull = false)
		private Date dateHeureDebutPassage = null;
	
	@DatabaseField(
			columnName = DATE_HEURE_FIN_PASSAGE,
			dataType = DataType.DATE_TIME,
			useGetSet = true,
			canBeNull = false)
		private Date dateHeureFinPassage = null;
	
	@DatabaseField(
		columnName = SUJET_EVALUATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private SujetEvaluation sujetEvaluation = null;

	@DatabaseField(
		columnName = LIEN_GRILLE_CORRECTION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienGrilleCorrection = null;
	
	@DatabaseField(
			columnName = LIEN_COPIES_IMMATERIELLES_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true,
			canBeNull = false)
		private String LienCopiesImmaterielles = null;

	@DatabaseField(
			columnName = CORRECTEUR_FIELD_NAME,
			foreign = true,
			useGetSet = true,
			canBeNull = false)
		private Utilisateur Correcteur = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	
}
