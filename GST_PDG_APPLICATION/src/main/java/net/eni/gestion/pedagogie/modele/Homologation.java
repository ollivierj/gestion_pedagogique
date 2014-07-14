/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import org.apache.commons.lang3.time.DateUtils;

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
	
	@JsonBackReference
	@DatabaseField(
		columnName = PROFESSIONNEL_HOMOLOGUE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private ProfessionnelHomologue professionnelHomologue = null;

	@Attributes(title="Titre professionnel", required = true)
	@JsonBackReference(value = "titre-homologation")
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
	
	@Attributes(title = "Date de début de validité", required = false, format = "date")
	private String formatedDateDebut;

	public String getFormatedDateDebut() {
		return formatedDateDebut;
	}

	public void setFormatedDateDebut(String formatedDateDebut) throws ParseException {
		this.dateDebut=(null != formatedDateDebut) ? DateUtils.parseDate(formatedDateDebut, "dd/MM/yyyy") : null;
		this.formatedDateDebut = formatedDateDebut;
	}

	@JsonIgnore
	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date dateFin = null;
	
	@Attributes(title = "Date de fin de validité", required = false, format = "date")
	private String formatedDateFin;

	public String getFormatedDateFin() {
		return formatedDateFin;
	}

	public void setFormatedDateFin(String formatedDateFin) throws ParseException {
		this.dateFin=(null != formatedDateFin) ? DateUtils.parseDate(formatedDateFin, "dd/MM/yyyy") : null;
		this.formatedDateFin = formatedDateFin;
	}
			
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
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
