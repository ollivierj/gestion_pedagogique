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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.HOMOLOGATION_TABLE_NAME)
@XmlRootElement
public class Homologation extends AModele<Integer> implements Serializable {
	
	public Homologation() {
		super();
	}

	public Homologation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 						= "HMG_ID";
	public final static String PROFESSIONNEL_HOMOLOGUE_FIELD_NAME 	= "HMG_PROFESSIONNEL_HOMOLOGUE";
	public final static String TITRE_PROFESSIONNEL_FIELD_NAME 		= "HMG_TITRE_PROFESSIONNEL";
	public final static String DATE_DEBUT_FIELD_NAME 				= "HMG_DATE_DEBUT";
	public final static String DATE_FIN_FIELD_NAME 					= "HMG_DATE_FIN";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	
	@JsonBackReference("ProfessionnelHomologue-Homologations")
	@DatabaseField(
		columnName = PROFESSIONNEL_HOMOLOGUE_FIELD_NAME,
		foreign = true,
		foreignAutoCreate = true,
		foreignAutoRefresh = true,
		useGetSet = true,
		canBeNull = false)
	private ProfessionnelHomologue professionnelHomologue = null;

	@Attributes(title="Titre professionnel", required = true)
	@DatabaseField(
		columnName = TITRE_PROFESSIONNEL_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private TitreProfessionnel titreProfessionnel = null;

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateDebut = null;
	
	@Attributes(title = "Date de début de validité", required = true, format = "date")
	private String formatedDateDebut;

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateFin = null;
	
	@Attributes(title = "Date de fin de validité", required = true, format = "date")
	private String formatedDateFin;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public ProfessionnelHomologue getProfessionnelHomologue() {
		return professionnelHomologue;
	}

	public void setProfessionnelHomologue(
			ProfessionnelHomologue professionnelHomologue) {
		this.professionnelHomologue = professionnelHomologue;
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

}
