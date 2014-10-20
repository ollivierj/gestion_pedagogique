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
@DatabaseTable(tableName = ModeleMetier.MODULE_PAR_UNITE_TABLE_NAME)
@XmlRootElement
public class ModuleParUnite extends AModele<Integer> implements Serializable {
	
	public ModuleParUnite() {
		super();
	}

	public ModuleParUnite(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "Id";
	public final static String POSITION_FIELD_NAME 	= "Position";
	public final static String ID_UNITE_FIELD_NAME	= "IdUnite";
	public final static String ID_MODULE_FIELD_NAME = "IdModule";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = POSITION_FIELD_NAME,
		dataType = DataType.SHORT_OBJ,
		useGetSet = true,
		canBeNull = false)
	private Short position = null;

	@DatabaseField(
		columnName = ID_UNITE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private UniteParFormation uniteParFormation = null;

	@DatabaseField(
		columnName = ID_MODULE_FIELD_NAME,
		foreign = true,
		useGetSet = true,
		canBeNull = false)
	private Module module = null;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		this.id = pId;	
	}

	public Short getPosition() {
		return position;
	}

	public void setPosition(Short position) {
		this.position = position;
	}

	public UniteParFormation getUniteParFormation() {
		return uniteParFormation;
	}

	public void setUniteParFormation(UniteParFormation uniteParFormation) {
		this.uniteParFormation = uniteParFormation;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
