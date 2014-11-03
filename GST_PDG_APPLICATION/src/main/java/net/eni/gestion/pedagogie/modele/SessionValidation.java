/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.outil.DateHelper;
import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

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
@DatabaseTable(tableName = ModeleMetier.SESSION_VALIDATION_TABLE_NAME)
@XmlRootElement
public class SessionValidation extends AModele<Integer> implements Serializable {
	
	public SessionValidation() {
		super();
	}

	public SessionValidation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "SES_VAL_ID";
	public final static String AUTEUR_FIELD_NAME 					= "SES_VAL_AUTEUR";
	public final static String TITRE_PROFESSIONNEL_FIELD_NAME		= "SES_VAL_TITRE_PROFESSIONNEL";
	public final static String DATE_DEBUT_FIELD_NAME				= "SES_VAL_DATE_DEBUT";
	public final static String DATE_FIN_FIELD_NAME					= "SES_VAL_DATE_FIN";
	
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
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateDebut = null;

	@Attributes(title = "Date de début", required = true, format = "date")
	private String formatedDateDebut;
	
	@JsonIgnore
	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateFin = null;

	@Attributes(title = "Date de fin", required = true, format = "date")
	private String formatedDateFin;
	
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur auteur = null;

	@DatabaseField(
		columnName = TITRE_PROFESSIONNEL_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private TitreProfessionnel titreProfessionnel = null;

	
	@SchemaIgnore
	@JsonIgnore
	@ForeignCollectionField(eager = true, columnName = SessionValidationStagiaire.STAGIAIRE_FIELD_NAME)
	private transient Collection<SessionValidationStagiaire> transientSessionValidationStagiaires = null;
	
	@SchemaIgnore
	@JsonManagedReference("SessionValidationStagiaire-SessionValidation")
	public Collection<SessionValidationStagiaire> getTransientSessionValidationStagiaire() {
		return transientSessionValidationStagiaires;
	}

	@JsonIgnore
	@SchemaIgnore
	public void setTransientSessionValidationStagiaire(
			Collection<SessionValidationStagiaire> transientSessionValidationStagiaires) {
		this.transientSessionValidationStagiaires = transientSessionValidationStagiaires;
	}
	
	@SchemaIgnore
	@Attributes(id = "sessionValidationStagiaires")
	private ArrayList<SessionValidationStagiaire> sessionValidationStagiaires= new ArrayList<SessionValidationStagiaire>();
	
	@SchemaIgnore
	public ArrayList<SessionValidationStagiaire> getSessionValidationStagiaires() {
		if (null != transientSessionValidationStagiaires) {
			sessionValidationStagiaires.clear();
			sessionValidationStagiaires.addAll(transientSessionValidationStagiaires);
			transientSessionValidationStagiaires = null;
		}
		return sessionValidationStagiaires;
	}

	@SchemaIgnore
	public void setSessionValidationStagiaires(ArrayList<SessionValidationStagiaire> sessionValidationStagiaires) {
		this.sessionValidationStagiaires = sessionValidationStagiaires;
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

	public TitreProfessionnel getTitreProfessionnel() {
		return titreProfessionnel;
	}

	public void setTitreProfessionnel(TitreProfessionnel titreProfessionnel) {
		this.titreProfessionnel = titreProfessionnel;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.formatedDateDebut=DateHelper.stringifyDate(dateDebut, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateDebut = dateDebut;
	}
	
	public String getFormatedDateDebut() {
		return formatedDateDebut;
	}

	public void setFormatedDateDebut(String formatedDateDebut) throws ParseException {
		this.dateDebut=DateHelper.datifyString(formatedDateDebut, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateDebut = formatedDateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.formatedDateFin=DateHelper.stringifyDate(dateFin, "yyyy-MM-dd'T'HH:mm:ss");
		this.dateFin = dateFin;
	}
	
	public String getFormatedDateFin() {
		return formatedDateFin;
	}

	public void setFormatedDateFin(String formatedDateFin) throws ParseException {
		this.dateFin=DateHelper.datifyString(formatedDateFin, "yyyy-MM-dd'T'HH:mm:ss");
		this.formatedDateFin = formatedDateFin;
	}
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getTitreProfessionnel())?(null!=getTitreProfessionnel().getCode())?getTitreProfessionnel().getCode():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getFormatedDateDebut())?getFormatedDateDebut():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getFormatedDateFin())?getFormatedDateFin():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getPrenom())?getAuteur().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getNom())?getAuteur().getNom():"":"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}
}
