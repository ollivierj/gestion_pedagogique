/**
 * 
 */
package net.eni.gestion.pedagogie.commun.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.commun.configuration.ModeleMetier;
import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.reinert.jjschema.Attributes;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author jollivier
 */
@DatabaseTable(tableName = ModeleMetier.TITRE_PROFESSIONNEL_TABLE_NAME)
@XmlRootElement
public class TitreProfessionnel extends AModele<Integer> implements Serializable {
	
	public TitreProfessionnel() {
		super();
	}

	public TitreProfessionnel(Integer pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "TR_PRF_ID";
	public final static String INTITULE_FIELD_NAME				= "TR_PRF_INTITULE";
	
	public final static String[] FULL_TEXT_SEARCH_FIELDS		= {INTITULE_FIELD_NAME};
	
	@JsonIgnore
	@Override
	public String[] getFullTextSearchFieldNames() {
		return FULL_TEXT_SEARCH_FIELDS;
	}
	
	@Attributes(title = "Titre professionnel", required = true)
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.INTEGER_OBJ,
		generatedId = true,
		useGetSet = true)
	private Integer id = null;
	
	@Attributes(title = "Intitulé", required = true, maxLength = 50)
	@DatabaseField(
		columnName = INTITULE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String code = null;

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
	
	@Override
	public String toString() {
		StringBuilder lStrStringBuilder = new StringBuilder();
		lStrStringBuilder.append((null!=getCode())?getCode():"");
		lStrStringBuilder.append(";");
		return lStrStringBuilder.toString();
	}

}
