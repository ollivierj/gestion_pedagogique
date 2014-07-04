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
@DatabaseTable(tableName = ModeleMetier.INSTANCE_COURS_STAGIAIRE_TABLE_NAME)
@XmlRootElement
public class InstanceCoursStagiaire extends AModele<Integer> implements Serializable {
	
	public InstanceCoursStagiaire() {
		super();
	}

	public InstanceCoursStagiaire(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "INST_COURS_STG_ID";
	public final static String INSTANCE_COURS_FIELD_NAME 	= "INST_COURS_STG_INSTANCE_COURS";
	public final static String STAGIAIRE_FIELD_NAME 		= "INST_COURS_STG_STAGIAIRE";
	
	@DatabaseField(
			columnName = ID_FIELD_NAME,
			dataType = DataType.INTEGER_OBJ,
			generatedId = true,
			useGetSet = true)
		private Integer id = null;
	
	@DatabaseField(
		columnName = INSTANCE_COURS_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private InstanceCours instanceCours = null;
	
	@DatabaseField(
		columnName = STAGIAIRE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Stagiaire stagiaire = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		this.id = pId;
		
	}

	public InstanceCours getInstanceCours() {
		return instanceCours;
	}

	public void setInstanceCours(InstanceCours instanceCours) {
		this.instanceCours = instanceCours;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

}
