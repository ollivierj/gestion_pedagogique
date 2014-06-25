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
@DatabaseTable(tableName = ModeleMetier.EVALUATION_TABLE_NAME)
@XmlRootElement
public class Evaluation extends AModele<Integer> implements Serializable {
	
	public Evaluation() {
		super();
	}

	public Evaluation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "EVAL_ID";
	public final static String MODULE_FIELD_NAME				= "EVAL_MODULE";
	public final static String LIEN_SUJET_FIELD_NAME			= "EVAL_LIEN_SUJET";
	public final static String LIEN_MODELE_CORRECTION_FIELD_NAME= "EVAL_LIEN_MODELE_CORRECTION";
	public final static String LIEN_GRILLE_CORRECTION_FIELD_NAME= "EVAL_LIEN_GRILLE_CORRECTION";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = MODULE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Module module = null;

	@DatabaseField(
		columnName = LIEN_SUJET_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienSujet = null;

	
	@DatabaseField(
		columnName = LIEN_MODELE_CORRECTION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienModeleCorrection = null;

	@DatabaseField(
		columnName = LIEN_GRILLE_CORRECTION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienGrilleCorrection = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getLienSujet() {
		return lienSujet;
	}

	public void setLienSujet(String lienSujet) {
		this.lienSujet = lienSujet;
	}

	public String getLienModeleCorrection() {
		return lienModeleCorrection;
	}

	public void setLienModeleCorrection(String lienModeleCorrection) {
		this.lienModeleCorrection = lienModeleCorrection;
	}

	public String getLienGrilleCorrection() {
		return lienGrilleCorrection;
	}

	public void setLienGrilleCorrection(String lienGrilleCorrection) {
		this.lienGrilleCorrection = lienGrilleCorrection;
	}

}
