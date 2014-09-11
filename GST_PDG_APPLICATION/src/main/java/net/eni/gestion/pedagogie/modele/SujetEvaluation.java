/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.SUJET_EVALUATION_TABLE_NAME)
@XmlRootElement
public class SujetEvaluation extends AModele<Integer> implements Serializable {
	
	public SujetEvaluation() {
		super();
	}

	public SujetEvaluation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 							= "SUJ_EVAL_ID";
	public final static String MODULE_FIELD_NAME 						= "SUJ_EVAL_MODULE";
	public final static String LIEN_SUJET_FIELD_NAME 					= "SUJ_EVAL_LIEN_SUJET";
	public final static String LIEN_MODELE_CORRECTION_FIELD_NAME		= "SUJ_EVAL_LIEN_MODELE_CORRECTION";
	public final static String LIEN_MODELE_GRILLE_CORRECTION_FIELD_NAME	= "SUJ_EVAL_LIEN_MODELE_GRILLE_CORRECTION";
	public final static String VERSION_FIELD_NAME						= "SUJ_EVAL_VERSION";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = MODULE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false,
		foreignAutoRefresh = true)
	private Module module = null;

	@Attributes(title = "Lien vers les énoncés", required = true, maxLength = 250, format = "url")
	@DatabaseField(
		columnName = LIEN_SUJET_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienSujet = null;

	@Attributes(title = "Lien vers les modèles de correction", required = true, maxLength = 250, format = "url")
	@DatabaseField(
		columnName = LIEN_MODELE_CORRECTION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienModeleCorrection = null;

	@Attributes(title = "Lien vers la grille de correction", required = true, maxLength = 250, format = "url")
	@DatabaseField(
		columnName = LIEN_MODELE_GRILLE_CORRECTION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String lienModeleGrilleCorrection = null;
	
	@Attributes(title = "Version", required = false, maxLength = 10)
	@DatabaseField(
		columnName = VERSION_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String version = null;
		
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

	public String getLienModeleGrilleCorrection() {
		return lienModeleGrilleCorrection;
	}

	public void setLienModeleGrilleCorrection(String lienModeleGrilleCorrection) {
		this.lienModeleGrilleCorrection = lienModeleGrilleCorrection;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
