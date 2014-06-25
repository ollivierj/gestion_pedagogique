/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.JURY_TABLE_NAME)
@XmlRootElement
public class Jury extends AModele<String> implements Serializable {
	
	public Jury() {
		super();
	}

	public Jury(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID1_FIELD_NAME 		= "PRF_HMG_ID";
	public final static String ID2_FIELD_NAME 		= "INST_SES_VAL_ID";
	
	@DatabaseField(
		columnName = ID1_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private ProfessionnelHomologue professionnelHomologue = null;
	
	@DatabaseField(
		columnName = ID2_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private InstanceSessionValidation instanceSessionValidation = null;

	public String getId() {
		return String.valueOf(professionnelHomologue.getId())+'/'+String.valueOf(instanceSessionValidation.getId());
	}

	@Override
	public void setId(String pId) {
		String[] ids = pId.split("/");
		professionnelHomologue.setId(Integer.valueOf(ids[0]));
		instanceSessionValidation.setId(Integer.valueOf(ids[1]));
	}


}
