/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.github.reinert.jjschema.SchemaIgnore;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.EVALUATION_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class EvaluationStagiaire extends AModele<Integer> implements Serializable {
	
	public EvaluationStagiaire() {
		super();
	}

	public EvaluationStagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "EVAL_STG_ID";
	public final static String EVALUATION_FIELD_NAME 			= "EVAL_STG_EVALUATION";
	public final static String STAGIAIRE_FIELD_NAME 			= "EVAL_STG_STAGIAIRE";
	public final static String INSTANCE_EVALUATION_FIELD_NAME	= "EVAL_STG_INSTANCE_EVALUATION";
	
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@SchemaIgnore
	
	@JsonBackReference("EvaluationStagiaire-Evaluation")
	@DatabaseField(
		columnName = EVALUATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Evaluation evaluation = null;
	
	@SchemaIgnore
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private StagiairePromotion stagiaire = null;

	@SchemaIgnore
	@DatabaseField(
			columnName = INSTANCE_EVALUATION_FIELD_NAME,
			foreign = true,
			useGetSet = true,
			canBeNull = true)
		private InstanceEvaluation instanceEvaluation = null;
	
	@SuppressWarnings("unused")
	private boolean hasInstance = false;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id=pId;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public StagiairePromotion getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(StagiairePromotion stagiaire) {
		this.stagiaire = stagiaire;
	}

	public InstanceEvaluation getInstanceEvaluation() {
		return instanceEvaluation;
	}

	public void setInstanceEvaluation(InstanceEvaluation instanceEvaluation) {
		this.instanceEvaluation = instanceEvaluation;
	}

	public boolean getHasInstance() {
		return (null!=instanceEvaluation);
	}

	

}
