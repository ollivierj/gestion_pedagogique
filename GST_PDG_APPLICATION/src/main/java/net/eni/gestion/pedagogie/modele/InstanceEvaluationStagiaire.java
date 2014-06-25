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
@DatabaseTable(tableName = ModeleMetier.INSTANCE_EVALUATION_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class InstanceEvaluationStagiaire extends AModele<String> implements Serializable {
	
	public InstanceEvaluationStagiaire() {
		super();
	}

	public InstanceEvaluationStagiaire(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID1_FIELD_NAME 		= "INST_EVAL_ID";
	public final static String ID2_FIELD_NAME 		= "CodeStagiaire";
	
	@DatabaseField(
		columnName = ID1_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private InstanceEvaluation instanceEvaluation = null;
	
	@DatabaseField(
		columnName = ID2_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Stagiaire stagiaire = null;

	public String getId() {
		return String.valueOf(instanceEvaluation.getId())+'/'+String.valueOf(stagiaire.getId());
	}

	@Override
	public void setId(String pId) {
		String[] ids = pId.split("/");
		instanceEvaluation.setId(Integer.valueOf(ids[0]));
		stagiaire.setId(Integer.valueOf(ids[1]));
	}


}
