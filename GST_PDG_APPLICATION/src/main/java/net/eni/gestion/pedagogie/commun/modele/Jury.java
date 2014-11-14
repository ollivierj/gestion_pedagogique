/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.JURY_TABLE_NAME)
@XmlRootElement
public class Jury extends AModele<Integer> implements Serializable {
	
	public Jury() {
		super();
	}

	public Jury(Integer pId) {
		super();
		setId(pId);
	}
	

	public Jury(Integer id, ProfessionnelHomologue professionnelHomologue,
			InstanceSessionValidation instanceSessionValidation) {
		super();
		this.id = id;
		this.professionnelHomologue = professionnelHomologue;
		this.instanceSessionValidation = instanceSessionValidation;
	}


	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 							= "JURY_ID";
	public final static String PROFESSIONNEL_HOMOLOGUE_FIELD_NAME 		= "JURY_PROFESSIONNEL_HOMOLOGUE";
	public final static String INSTANCE_SESSION_VALIDATION_FIELD_NAME 	= "JURY_INSTANCE_SESSION_VALIDATION";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = PROFESSIONNEL_HOMOLOGUE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh=true)
	private ProfessionnelHomologue professionnelHomologue = null;
	
	@DatabaseField(
		columnName = INSTANCE_SESSION_VALIDATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private InstanceSessionValidation instanceSessionValidation = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		this.id = pId;
	}
	public ProfessionnelHomologue getProfessionnelHomologue() {
		return professionnelHomologue;
	}

	public void setProfessionnelHomologue(
			ProfessionnelHomologue professionnelHomologue) {
		this.professionnelHomologue = professionnelHomologue;
	}

	public InstanceSessionValidation getInstanceSessionValidation() {
		return instanceSessionValidation;
	}

	public void setInstanceSessionValidation(InstanceSessionValidation instanceSessionValidation) {
		this.instanceSessionValidation = instanceSessionValidation;
	}

}
