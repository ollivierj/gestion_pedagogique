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
@DatabaseTable(tableName = ModeleMetier.FONCTION_TABLE_NAME)
@XmlRootElement
public class Fonction extends AModele<String> implements Serializable {
	
	public Fonction() {
		super();
	}

	public Fonction(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 		= "CodeFonction";
	public final static String LIBELLE_FIELD_NAME 	= "Libelle";

	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		generatedId = false,
		useGetSet = true)
	private String id = null;
	
	@DatabaseField(
		columnName = LIBELLE_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true)
	private String libelle = null;

	@Override
	public void setId(String pId) {
		id = pId;
	}

	@Override
	public String getId() {
		return id;
	}


}
