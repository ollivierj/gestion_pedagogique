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
	public final static String SUJET_EVALUATION_FIELD_NAME			= "EVAL_SUJET_EVALUATION";
	public final static String DATE_HEURE_DEBUT_PASSAGE				= "EVAL_DATE_HEURE_DEBUT_PASSAGE";
	public final static String DATE_HEURE_FIN_PASSAGE				= "EVAL_DATE_HEURE_FIN_PASSAGE";
	public final static String CORRECTEUR_FIELD_NAME				= "EVAL_CORRECTEUR";
	public final static String AUTEUR_FIELD_NAME 					= "EVAL_AUTEUR";
	
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

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_HEURE_DEBUT_PASSAGE,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateHeureDebutPassage = null;
	
	@Attributes(title = "Date et heure de début", required = true, format = "time")
	private String formatedDateHeureDebutPassage;

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_HEURE_FIN_PASSAGE,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateHeureFinPassage = null;
	
	@Attributes(title = "Date et heure de fin", required = true, format = "time")
	private String formatedDateHeureFinPassage;

	@DatabaseField(
		columnName = SUJET_EVALUATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private SujetEvaluation sujetEvaluation = null;

	@DatabaseField(
		columnName = CORRECTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = true)
	private Utilisateur correcteur = null;
	
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = true)
	private Utilisateur auteur = null;
	
	@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = EvaluationStagiaire.STAGIAIRE_FIELD_NAME)
	private transient Collection<EvaluationStagiaire> transientEvaluationStagiaires = null;
	
	@SchemaIgnore
	@JsonManagedReference("EvaluationStagiaire-Evaluation")
	public Collection<EvaluationStagiaire> getTransientEvaluationStagiaire() {
		return transientEvaluationStagiaires;
	}

	@JsonIgnore
	@SchemaIgnore
	public void setTransientEvaluationStagiaire(
			Collection<EvaluationStagiaire> transientEvaluationStagiaires) {
		this.transientEvaluationStagiaires = transientEvaluationStagiaires;
	}

	@SchemaIgnore
	@Attributes(id = "evaluationStagiaires")
	private ArrayList<EvaluationStagiaire> evaluationStagiaires= new ArrayList<EvaluationStagiaire>();
	
	@SchemaIgnore
	public ArrayList<EvaluationStagiaire> getEvaluationStagiaires() {
		if (null != transientEvaluationStagiaires) {
			evaluationStagiaires.clear();
			evaluationStagiaires.addAll(transientEvaluationStagiaires);
			transientEvaluationStagiaires = null;
		}
		return evaluationStagiaires;
	}

	@SchemaIgnore
	public void setEvaluationStagiaires(ArrayList<EvaluationStagiaire> evaluationStagiaires) {
		this.evaluationStagiaires = evaluationStagiaires;
	}
	
	public Date getDateHeureDebutPassage() {
		return dateHeureDebutPassage;
	}

	public void setDateHeureDebutPassage(Date dateHeureDebutPassage) {
		this.formatedDateHeureDebutPassage=DateHelper.stringifyDate(dateHeureDebutPassage, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateHeureDebutPassage = dateHeureDebutPassage;
	}
	
	public String getFormatedDateHeureDebutPassage() {
		return formatedDateHeureDebutPassage;
	}

	public void setFormatedDateHeureDebutPassage(String formatedDateHeureDebutPassage) throws ParseException {
		this.dateHeureDebutPassage=DateHelper.datifyString(formatedDateHeureDebutPassage, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateHeureDebutPassage = formatedDateHeureDebutPassage;
	}

	public Date getDateHeureFinPassage() {
		return dateHeureFinPassage;
	}

	public void setDateHeureFinPassage(Date dateHeureFinPassage) {
		this.formatedDateHeureFinPassage=DateHelper.stringifyDate(dateHeureFinPassage, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateHeureFinPassage = dateHeureFinPassage;
	}
	
	public String getFormatedDateHeureFinPassage() {
		return formatedDateHeureFinPassage;
	}

	public void setFormatedDateHeureFinPassage(String formatedDateHeureFinPassage) throws ParseException {
		this.dateHeureFinPassage=DateHelper.datifyString(formatedDateHeureFinPassage, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateHeureFinPassage = formatedDateHeureFinPassage;
	}
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public SujetEvaluation getSujetEvaluation() {
		return sujetEvaluation;
	}

	public void setSujetEvaluation(SujetEvaluation sujetEvaluation) {
		this.sujetEvaluation = sujetEvaluation;
	}

	public Utilisateur getCorrecteur() {
		return correcteur;
	}

	public void setCorrecteur(Utilisateur correcteur) {
		this.correcteur = correcteur;
	}

	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getSujetEvaluation())?(null!=getSujetEvaluation().getModule())?getSujetEvaluation().getModule().getLibelle():"":"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getSujetEvaluation())?getSujetEvaluation().getVersion():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append(getFormatedDateHeureDebutPassage());
		lStrStringBuilder.append(";");
		lStrStringBuilder.append(getFormatedDateHeureFinPassage());
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getAuteur())?getAuteur().getPrenom():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAuteur())?getAuteur().getNom():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCorrecteur())?getCorrecteur().getPrenom():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getCorrecteur())?getCorrecteur().getNom():"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}

}
