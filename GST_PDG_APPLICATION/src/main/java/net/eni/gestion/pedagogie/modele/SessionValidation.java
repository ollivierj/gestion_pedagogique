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
import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
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
	public final static String LIEN_MODELES_PUBLIPOSTAGE_FIELD_NAME	= "SES_VAL_LIEN_MODELES_PUBLIPOSTAGE";
	public final static String LIEN_DOCS_GENERES_FIELD_NAME			= "SES_VAL_LIEN_DOCS_GENERES";
	public final static String LIEN_DOCS_COLLECTES_FIELD_NAME		= "SES_VAL_LIEN_DOCS_COLLECTES";
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= null;
	
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

	@Attributes(title = "Lien vers les modèles de document pour le publipostage", required = false, maxLength = 250, format = "url")
	@DatabaseField(
		columnName = LIEN_MODELES_PUBLIPOSTAGE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienModelesPublipostage = null;
	
	@Attributes(title = "Lien vers les documents générés (publipostage)", required = false, maxLength = 250, format = "url")
	@DatabaseField(
			columnName = LIEN_DOCS_GENERES_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true,
			canBeNull = false)
		private String lienDocsGeneres = null;
	
	@Attributes(title = "Lien vers les documents remis par les jurys", required = false, maxLength = 250, format = "url")
	@DatabaseField(
			columnName = LIEN_DOCS_COLLECTES_FIELD_NAME,
			dataType = DataType.STRING,
			useGetSet = true,
			canBeNull = false)
		private String lienDocsCollectes = null;
	
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

	public String getLienDocsGeneres() {
		return lienDocsGeneres;
	}

	public void setLienDocsGeneres(String lienDocsGeneres) {
		this.lienDocsGeneres = lienDocsGeneres;
	}

	public String getLienDocsCollectes() {
		return lienDocsCollectes;
	}

	public void setLienDocsCollectes(String lienDocsCollectes) {
		this.lienDocsCollectes = lienDocsCollectes;
	}

	public TitreProfessionnel getTitreProfessionnel() {
		return titreProfessionnel;
	}

	public void setTitreProfessionnel(TitreProfessionnel titreProfessionnel) {
		this.titreProfessionnel = titreProfessionnel;
	}

	public String getLienModelesPublipostage() {
		return lienModelesPublipostage;
	}

	public void setLienModelesPublipostage(String lienModelesPublipostage) {
		this.lienModelesPublipostage = lienModelesPublipostage;
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
}
