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
@DatabaseTable(tableName = ModeleMetier.DROIT_PROFIL_TABLE_NAME)
@XmlRootElement
public class DroitProfil extends AModele<String> implements Serializable {
	
	public DroitProfil() {
		super();
	}

	public DroitProfil(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID1_FIELD_NAME = "PRF_ID";
	public final static String ID2_FIELD_NAME = "DRIT_ID";
	
	@DatabaseField(
		columnName = ID1_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Profil profil = null;
	
	@DatabaseField(
		columnName = ID2_FIELD_NAME,
		foreign = true,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Droit droit = null;

	public String getId() {
		return String.valueOf(profil.getId())+'/'+String.valueOf(droit.getId());
	}

	@Override
	public void setId(String pId) {
		String[] ids = pId.split("/");
		profil.setId(Integer.valueOf(ids[0]));
		droit.setId(Integer.valueOf(ids[1]));
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
