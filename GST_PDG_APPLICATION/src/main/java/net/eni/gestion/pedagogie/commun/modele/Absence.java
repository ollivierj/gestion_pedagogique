/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;
import net.eni.gestion.pedagogie.commun.outil.DateHelper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.ABSENCE_TABLE_NAME)
@XmlRootElement
public class Absence extends AModele<Integer> implements Serializable {
	
	public Absence() {
		super();
	}

	public Absence(Integer pId) {
		
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "ABS_ID";
	public final static String DATE_FIELD_NAME 						= "ABS_DATE";
	public final static String STAGIAIRE_FIELD_NAME 				= "ABS_STAGIAIRE";
	public final static String DATE_ARRIVEE_MATIN_FIELD_NAME		= "ABS_DATE_ARRIVEE_MATIN";
	public final static String DATE_ARRIVEE_APRES_MIDI_FIELD_NAME	= "ABS_DATE_ARRIVEE_APRES_MIDI";
	public final static String AUTEUR_FIELD_NAME 					= "ABS_AUTEUR";
	public final static String MOTIF_FIELD_NAME 					= "ABS_MOTIF";
	public final static String IS_ABSENCE_FIELD_NAME 				= "ABS_IS_ABSENCE";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@JsonIgnore
	@DatabaseField(
		columnName = DATE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date date = null;
	
	@JsonProperty
	private String formatedDate;
	
	@JsonProperty
	private String formatedTime;
	
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private Stagiaire stagiaire = null;
   
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		foreignAutoRefresh=true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur auteur = null;
	
	@DatabaseField(
			columnName = MOTIF_FIELD_NAME,
			useGetSet = true,
			canBeNull = true)
		private String commentaire = null;
	
	@DatabaseField(
			columnName = IS_ABSENCE_FIELD_NAME,
			useGetSet = true,
			canBeNull = true)
		private Boolean isAbsence = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.formatedDate=DateHelper.stringifyDate(date, "yyyy-MM-dd");
		this.formatedTime=DateHelper.stringifyDate(date, "HH:mm");
		this.date = date;
	}
	
	public String getFormatedDate() {
		return formatedDate;
	}

	public void setFormatedDate(String formatedDate) throws ParseException {
		if (this.getFormatedTime() != null)
			this.date= DateHelper.datifyString(formatedDate + " " + this.getFormatedTime(), "dd/MM/yyyy HH:mm");
		
		this.formatedDate = formatedDate;
	}

	public String getFormatedTime() {
		return formatedTime;
	}

	public void setFormatedTime(String formatedTime) {
		if (this.getFormatedDate() != null)
			this.date= DateHelper.datifyString(this.getFormatedDate() + " " + formatedTime, "dd/MM/yyyy HH:mm");
		
		this.formatedTime = formatedTime;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Boolean getIsAbsence() {
		return isAbsence;
	}

	public void setIsAbsence(Boolean isAbsence) {
		this.isAbsence = isAbsence;
	}
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getFormatedDate())?getFormatedDate():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getStagiaire())?(null!= getStagiaire().getPrenom())?getStagiaire().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getStagiaire())?(null!= getStagiaire().getNom())?getStagiaire().getNom():"":"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getFormatedDate())?getFormatedDate():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getFormatedTime())?getFormatedTime():"");
		lStrStringBuilder.append(";");
		
		
		
		
		lStrStringBuilder.append((null!=getCommentaire())?getCommentaire():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getPrenom())?getAuteur().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getNom())?getAuteur().getNom():"":"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}
}
