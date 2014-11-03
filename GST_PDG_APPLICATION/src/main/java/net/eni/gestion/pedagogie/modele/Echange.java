/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.outil.DateHelper;
import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.reinert.jjschema.SchemaIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.ECHANGE_TABLE_NAME)
@XmlRootElement
public class Echange extends AModele<Integer> implements Serializable {
	
	public Echange() {
		super();
	}

	public Echange(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 			= "ECH_ID";
	public final static String AUTEUR_FIELD_NAME 		= "ECH_AUTEUR";
	public final static String STAGIAIRE_FIELD_NAME 	= "ECH_STAGIAIRE";
	public final static String COMMENTAIRE_FIELD_NAME 	= "ECH_COMMENTAIRE";
	public final static String DATE_FIELD_NAME 			= "ECH_DATE";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		foreignAutoRefresh = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur auteur = null;

	@SchemaIgnore
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@DatabaseField(
		columnName = COMMENTAIRE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String commentaire = null;
	
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

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
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
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getFormatedDate())?getFormatedDate():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getStagiaire())?(null!= getStagiaire().getPrenom())?getStagiaire().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getStagiaire())?(null!= getStagiaire().getNom())?getStagiaire().getNom():"":"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getCommentaire())?getCommentaire():"");
		lStrStringBuilder.append(";");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getPrenom())?getAuteur().getPrenom():"":"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getAuteur())?(null!=getAuteur().getPrenom())?getAuteur().getPrenom():"":"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}


}
