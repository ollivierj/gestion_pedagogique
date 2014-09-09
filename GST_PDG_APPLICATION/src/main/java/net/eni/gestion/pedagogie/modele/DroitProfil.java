/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.DROIT_PROFIL_TABLE_NAME)
@XmlRootElement
public class DroitProfil extends AModele<Integer> implements Serializable {
	
	public DroitProfil() {
		super();
	}

	public DroitProfil(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "DRT_PRF_ID";
	public final static String PROFIL_FIELD_NAME 	= "DRT_PRF_PROFIL";
	public final static String DROIT_FIELD_NAME 	= "DRT_PRF_DROIT";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@JsonBackReference("profil-droitprofil")
	@DatabaseField(
		columnName = PROFIL_FIELD_NAME,
		foreign = true,
		useGetSet = true, 
		canBeNull = false)
	private Profil profil = null;
	
	@DatabaseField(
		columnName = DROIT_FIELD_NAME,
		foreign = true,
		useGetSet = true, 
		canBeNull = false)
	private Droit droit = null;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		this.id  = pId;
		
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Droit getDroit() {
		return droit;
	}

	public void setDroit(Droit droit) {
		this.droit = droit;
	}

}
