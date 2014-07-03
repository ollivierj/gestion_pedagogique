/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.time.DateFormatUtils;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.INSTANCE_SESSION_VALIDATION_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class InstanceSessionValidationStagiaire extends AModele<Integer> implements Serializable {
	
	public InstanceSessionValidationStagiaire() {
		super();
	}

	public InstanceSessionValidationStagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 							= "INST_SES_VAL_STG_ID";
	public final static String INSTANCE_SESSION_VALIDATION_FIELD_NAME 	= "INST_SES_VAL_STG_INSTANCE_SESSION_VALIDATION";
	public final static String STAGIAIRE_FIELD_NAME						= "INST_SES_VAL_STG_STAGIAIRE";
	public final static String HEURE_DEBUT_FIELD_NAME					= "INST_SES_VAL_STG_HEURE_DEBUT";
	public final static String HEURE_FIN_FIELD_NAME						= "INST_SES_VAL_STG_HEURE_FIN";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true, 
		canBeNull = false)
	private Stagiaire stagiaire = null;

	@DatabaseField(
		columnName = INSTANCE_SESSION_VALIDATION_FIELD_NAME,
		foreign = true,
		useGetSet = true, 
		canBeNull = false)
	private InstanceSessionValidation instanceSessionValidation = null;
	
	@DatabaseField(
		columnName = HEURE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date heureDebut = null;

	@DatabaseField(
		columnName = HEURE_FIN_FIELD_NAME,
		dataType = DataType.DATE,
		useGetSet = true,
		canBeNull = false)
	private Date heureFin = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		this.id = pId;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}
	
	public void setInstanceSessionValidation(InstanceSessionValidation instanceSessionValidation) {
		this.instanceSessionValidation = instanceSessionValidation;
	}
	
	public InstanceSessionValidation getInstanceSessionValidation() {
		return instanceSessionValidation;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public String getFormatedDateHeureDebut(){
		return (null!=heureDebut)? DateFormatUtils.format(heureDebut, "H:mm:ss"): null;
	}
	
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}
	
	public String getFormatedDateHeureFin(){
		return (null!=heureFin)? DateFormatUtils.format(heureFin, "H:mm:ss"): null;
	}

	public void setHeureFin(Date heureFin) {
		this.heureFin = heureFin;
	}

}
