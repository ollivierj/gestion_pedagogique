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
@DatabaseTable(tableName = ModeleMetier.PARAMETRE_TABLE_NAME)
@XmlRootElement
public class Parametre extends AModele<String> implements Serializable {
	
	public Parametre() {
		super();
	}

	public Parametre(String pId) {
		super();
		setId(pId);
	}

	private static final long serialVersionUID = 1L;

	public final static String ID_FIELD_NAME 					= "CONSTANTE";
	public final static String VALEUR_FIELD_NAME 				= "VALEUR";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
		generatedId = false,
		useGetSet = true)
	private String constante = null;
	
	@DatabaseField(
		columnName = VALEUR_FIELD_NAME,
		dataType = DataType.STRING,
		useGetSet = true,
		canBeNull = false)
	private String valeur = null;
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return constante;
	}

	@Override
	public void setId(String pId) {
		constante = pId;
	}


}
