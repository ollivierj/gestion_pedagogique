/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
	public final static String CODE_FIELD_NAME					= "TR_PRF_CODE";
	public final static String LIEN_DOC_REFERENCES_FIELD_NAME	= "TR_PRF_LIEN_DOC_REFERENCES";
	
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
		columnName = LIEN_DOC_REFERENCES_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String lienDocReferences = null;
	
	@ForeignCollectionField(eager = true, columnName = Homologation.TITRE_PROFESSIONNEL_FIELD_NAME)
	private transient Collection<Homologation> transientHomologations = null;

	private ArrayList<Homologation> homologations = new ArrayList<Homologation>();
		
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

	public String getLienDocReferences() {
		return lienDocReferences;
	}

	public void setLienDocReferences(String lienDocReferences) {
		this.lienDocReferences = lienDocReferences;
	}
	
	public ArrayList<Homologation> getHomologations() {
		if (null != transientHomologations) {
			homologations.clear();
			homologations.addAll(transientHomologations);
			transientHomologations = null;
		}
		return homologations;
	}

}
