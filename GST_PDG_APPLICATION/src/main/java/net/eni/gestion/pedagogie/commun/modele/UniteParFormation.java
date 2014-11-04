/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
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
	
	@ForeignCollectionField(eager = true, columnName = ModuleParUnite.ID_UNITE_FIELD_NAME)
	private transient Collection<ModuleParUnite> transientModuleParUnites = null;

	private ArrayList<ModuleParUnite> moduleParUnites = new ArrayList<ModuleParUnite>();

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public Short getPosition() {
		return position;
	}

	public void setPosition(Short position) {
		this.position = position;
	}

	public UniteFormation getUniteFormation() {
		return uniteFormation;
	}

	public void setUniteFormation(UniteFormation uniteFormation) {
		this.uniteFormation = uniteFormation;
	}
	
	private ArrayList<ModuleParUnite> getModuleParUnites() {
		if (null != transientModuleParUnites) {
			moduleParUnites.clear();
			moduleParUnites.addAll(transientModuleParUnites);
			transientModuleParUnites = null;
		}
		return moduleParUnites;
	}
	
	public ArrayList<Module> getModules(){
		ArrayList<Module> modules = new ArrayList<Module>();
		if (0 < getModuleParUnites().size()) {
			Iterator<ModuleParUnite> iterator = getModuleParUnites().iterator();
			while (iterator.hasNext()) {
				modules.add(iterator.next().getModule());
			}
		}
		return modules;
	}

}
