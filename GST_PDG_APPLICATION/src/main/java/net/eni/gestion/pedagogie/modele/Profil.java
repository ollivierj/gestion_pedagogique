/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
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
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@Attributes(title = "Code", required = true, maxLength = 10)
	@DatabaseField(
		columnName = CODE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String code = null;

	@Attributes(title = "Libelle", required = true, maxLength = 50)
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String libelle = null;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	private ArrayList<String> droits;
	

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

	public void setDroits(ArrayList<String> droits) {
		this.droits = droits;
	}

	public ArrayList<String> getDroits() {
		return droits;
	}	
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getCode())?getCode():"");
		lStrStringBuilder.append(" ");
		lStrStringBuilder.append((null!=getLibelle())?getLibelle():"");
		lStrStringBuilder.append(" ");
		return lStrStringBuilder.toString();
	}
	
}
