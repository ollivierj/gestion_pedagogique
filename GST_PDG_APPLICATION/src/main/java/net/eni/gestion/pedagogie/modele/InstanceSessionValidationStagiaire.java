/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import net.sourceforge.jtds.jdbc.DateTime;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.INSTANCE_SESSION_VALIDATION_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class InstanceSessionValidationStagiaire extends AModele<String> implements Serializable {
	
	public InstanceSessionValidationStagiaire() {
		super();
	}

	public InstanceSessionValidationStagiaire(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID1_FIELD_NAME 			= "INST_SES_VAL_ID";
	public final static String ID2_FIELD_NAME			= "CodeStagiaire";
	public final static String HEURE_DEBUT_FIELD_NAME	= "INST_SES_VAL_STG_HEURE_DEBUT";
	public final static String HEURE_FIN_FIELD_NAME		= "INST_SES_VAL_STG_HEURE_FIN";
	
	@DatabaseField(
		columnName = ID1_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private InstanceSessionValidation instanceSessionValidation = null;
	
	@DatabaseField(
		columnName = ID2_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Stagiaire stagiaire = null;

	@DatabaseField(
		columnName = HEURE_DEBUT_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime heureDebut = null;

	@DatabaseField(
		columnName = HEURE_FIN_FIELD_NAME,
		dataType = DataType.DATE_TIME,
		useGetSet = true,
		canBeNull = false)
	private DateTime heureFin = null;

	public String getId() {
		return String.valueOf(instanceSessionValidation.getId())+'/'+String.valueOf(stagiaire.getId());
	}

	@Override
	public void setId(String pId) {
		String[] ids = pId.split("/");
		instanceSessionValidation.setId(Integer.valueOf(ids[0]));
		stagiaire.setId(Integer.valueOf(ids[1]));
	}

	public InstanceSessionValidation getInstanceSessionValidation() {
		return instanceSessionValidation;
	}

	public void setInstanceSessionValidation(InstanceSessionValidation instanceSessionValidation) {
		this.instanceSessionValidation = instanceSessionValidation;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public DateTime getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(DateTime heureDebut) {
		this.heureDebut = heureDebut;
	}

	public DateTime getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(DateTime heureFin) {
		this.heureFin = heureFin;
	}

}
