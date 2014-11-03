/**
 * 
 */
package net.eni.gestion.pedagogie.modele;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.configuration.ModeleMetier;
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

	public final static String ID_FIELD_NAME		= "CONSTANTE";
	public final static String VALEUR_FIELD_NAME 	= "VALEUR";
	
	@DatabaseField(
		columnName = ID_FIELD_NAME,
		dataType = DataType.STRING,
		id = true,
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
		return constante;
	}

	@Override
	public void setId(String pId) {
		constante = pId;
	}

	public String getConstante() {
		return constante;
	}

	public void setConstante(String constante) {
		this.constante = constante;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

}
