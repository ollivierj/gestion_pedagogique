/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.commun.outil.DateHelper;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.reinert.jjschema.Attributes;
import com.github.reinert.jjschema.SchemaIgnore;
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
	public final static String DATE_SAISIE_FIELD_NAME 				= "ABS_DATE_SAISIE";
	public final static String AUTEUR_FIELD_NAME 					= "ABS_AUTEUR";
	public final static String MOTIF_FIELD_NAME 					= "ABS_MOTIF";

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
	
	@SchemaIgnore
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;
   
	@JsonIgnore
	@DatabaseField(
		columnName = DATE_SAISIE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateSaisie = null;
	
	@SchemaIgnore
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Utilisateur auteur = null;
	
	@Attributes(title="Motif", required=false)
	@DatabaseField(
			columnName = MOTIF_FIELD_NAME,
			useGetSet = true,
			canBeNull = true)
		private String motif = null;

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
		this.formatedDate=DateHelper.stringifyDate(date, "yyyy-MM-dd HH:mm:ss");
		this.date = date;
	}
	
	public String getFormatedDate() {
		return formatedDate;
	}

	public void setFormatedDate(String formatedDate) throws ParseException {
		this.date= DateHelper.datifyString(formatedDate, "yyyy-MM-dd HH:mm:ss");
		this.formatedDate = formatedDate;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Date getDateSaisie() {
		return dateSaisie;
	}
	
	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}
	
}
