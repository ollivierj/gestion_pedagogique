/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.bind.annotation.XmlRootElement;
import net.eni.gestion.pedagogie.commun.constante.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.PROFIL_TABLE_NAME)
@XmlRootElement
public class Profil extends AModele<Integer> implements Serializable {
	
	public Profil() {
		super();
	}

	public Profil(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "PRF_ID";
	public final static String CODE_FIELD_NAME		= "PRF_CODE";
	public final static String LIBELLE_FIELD_NAME	= "PRF_LIBELLE";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		id = true,
		generatedId = false,
		useGetSet = true)
	private Integer id = null;
	
	@DatabaseField(
		columnName = CODE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String code = null;

	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;
	
	@ForeignCollectionField(eager = true, columnName = DroitProfil.ID1_FIELD_NAME)
	private transient Collection<DroitProfil> transientDroitProfils = null;

	private ArrayList<DroitProfil> droitProfils = new ArrayList<DroitProfil>();
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer pId) {
		id = pId;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setDroitProfils(ArrayList<DroitProfil> droitProfils) {
		this.droitProfils = droitProfils;
	}

	private ArrayList<DroitProfil> getDroitProfils() {
		if (null != transientDroitProfils) {
			droitProfils.clear();
			droitProfils.addAll(transientDroitProfils);
			transientDroitProfils = null;
		}
		return droitProfils;
	}
	
	public ArrayList<Droit> getDroits() {
		ArrayList<Droit> droits = new ArrayList<Droit>();
		if (0 < getDroitProfils().size()) {
			Iterator<DroitProfil> iterator = getDroitProfils().iterator();
			while (iterator.hasNext()) {
				droits.add(iterator.next().getDroit());
			}
		}
		return droits;
	}	
	
}
