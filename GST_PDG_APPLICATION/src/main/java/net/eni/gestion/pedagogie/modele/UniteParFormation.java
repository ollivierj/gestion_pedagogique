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
@DatabaseTable(tableName = ModeleMetier.UNITE_PAR_FORMATION_TABLE_NAME)
@XmlRootElement
public class UniteParFormation extends AModele<Integer> implements Serializable {
	
	public UniteParFormation() {
		super();
	}

	public UniteParFormation(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 				= "Id";
	public final static String CODE_FORMATION_FIELD_NAME	= "CodeFormation";
	public final static String POSITION_FIELD_NAME			= "Position";
	public final static String ID_UNITE_FORMATION_FIELD_NAME= "IdUniteFormation";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CODE_FORMATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Formation formation = null;

	@DatabaseField(
		columnName = POSITION_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short position = null;

	@DatabaseField(
		columnName = ID_UNITE_FORMATION_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private UniteFormation uniteFormation = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}


}
