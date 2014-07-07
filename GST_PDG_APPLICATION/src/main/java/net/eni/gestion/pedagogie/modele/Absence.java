/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	public final static String STAGIAIRE_FIELD_NAME 				= "ABS_STAGIAIRE";
	public final static String DATE_ARRIVEE_MATIN_FIELD_NAME		= "ABS_DATE_ARRIVEE_MATIN";
	public final static String DATE_ARRIVEE_APRES_MIDI_FIELD_NAME	= "ABS_DATE_ARRIVEE_APRES_MIDI";
	public final static String DATE_SAISIE_FIELD_NAME 				= "ABS_DATE_SAISIE";
	public final static String AUTEUR_FIELD_NAME 					= "ABS_AUTEUR";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_ARRIVEE_MATIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateArriveeMatin = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_ARRIVEE_APRES_MIDI_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateArriveeApresMidi = null;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy H:mm:ss", timezone="CET")   
	@DatabaseField(
		columnName = DATE_SAISIE_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateSaisie = null;
	
	@DatabaseField(
		columnName = AUTEUR_FIELD_NAME,
		foreign = true,
		useGetSet = true)
	private Utilisateur auteur = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Date getDateArriveeMatin() {
		return dateArriveeMatin;
	}
	
	public void setDateArriveeMatin(Date dateArriveeMatin) {
		this.dateArriveeMatin = dateArriveeMatin;
	}

	public Date getDateArriveeApresMidi() {
		return dateArriveeApresMidi;
	}
	
	public void setDateArriveeApresMidi(Date dateArriveeApresMidi) {
		this.dateArriveeApresMidi = dateArriveeApresMidi;
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
	
}
