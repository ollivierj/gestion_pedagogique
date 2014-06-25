/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;
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
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = PROFESSIONNEL_HOMOLOGUE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private ProfessionnelHomologue professionnelHomologue = null;

	@DatabaseField(
		columnName = TITRE_PROFESSIONNEL_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private TitreProfessionnel titreProfessionnel = null;

	@DatabaseField(
		columnName = DATE_DEBUT_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer dateDebut = null;

	@DatabaseField(
		columnName = DATE_FIN_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Integer dateFin = null;
			
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

	public Integer getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Integer dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Integer getDateFin() {
		return dateFin;
	}

	public void setDateFin(Integer dateFin) {
		this.dateFin = dateFin;
	}

}
