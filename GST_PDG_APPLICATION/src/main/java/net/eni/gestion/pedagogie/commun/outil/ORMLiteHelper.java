package net.eni.gestion.pedagogie.commun.outil;

import java.util.ArrayList;

import net.eni.gestion.pedagogie.modele.generique.AModele;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.TableInfo;

/**
 * @author jollivier
 * @param AModele
 * @param DAO
 */
public class ORMLiteHelper {
	
	public static <M extends AModele<ID>, ID> ArrayList<String> getProjectionFields(TableInfo<M, ID> tableInfo){
		ArrayList<String> lFields = new ArrayList<String>();
		for (FieldType lField : tableInfo.getFieldTypes()) {
			if (!lField.isForeignCollection()){
				lFields.add(lField.getColumnName());
			}	
		} 
		return lFields;
	}
	
	
}
