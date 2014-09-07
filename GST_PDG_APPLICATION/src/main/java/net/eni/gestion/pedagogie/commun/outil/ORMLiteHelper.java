package net.eni.gestion.pedagogie.commun.outil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import net.eni.gestion.pedagogie.commun.composant.GenericException;
import net.eni.gestion.pedagogie.commun.composant.PropertyPredicate;
import net.eni.gestion.pedagogie.commun.composant.SortOptions;
import net.eni.gestion.pedagogie.modele.generique.AModele;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.table.TableInfo;

/**
 * @author jollivier
 * @param AModele
 * @param DAO
 */
public class ORMLiteHelper {

	public final static String ASC = "ASC";
	public final static String DESC = "DESC";

	public static <M extends AModele<ID>, ID> ArrayList<String> getProjectionFields(
			TableInfo<M, ID> tableInfo) {
		ArrayList<String> lFields = new ArrayList<String>();
		for (FieldType lField : tableInfo.getFieldTypes()) {
			if (!lField.isForeignCollection()) {
				lFields.add(lField.getColumnName());
			}
		}
		return lFields;
	}

	public static <M extends AModele<ID>, ID> String getDataFieldNameFromClassAttributeName(
			TableInfo<M, ID> pTableInfo, String pFieldName) {
		Collection<FieldType> lFields = new ArrayList<FieldType>(
				Arrays.asList(pTableInfo.getFieldTypes()));
		FieldType lField = (FieldType) CollectionUtils.find(lFields,
				new PropertyPredicate("fieldName", pFieldName));
		return (null != lField) ? lField.getColumnName() : null;
	}

	public static String getCompliantOrderDirectionSortString(
			String pSortDirectionBy) {
		return (ASC.equals(pSortDirectionBy.toUpperCase()) || DESC
				.equals(pSortDirectionBy.toUpperCase())) ? pSortDirectionBy
				: null;
	}

	public static <M extends AModele<ID>, ID> String getOrderByClauseFromSortOptions(
			TableInfo<M, ID> tableInfo, SortOptions pSortOptions)
			throws GenericException {
		if (null == pSortOptions) {
			throw new GenericException("Le champ de tri fourni est vide.");
		}
		ArrayList<String> orderByClauseArray = new ArrayList<String>();
		for (int i = 0; i < pSortOptions.getFields().length
				&& i < pSortOptions.getDirections().length; i++) {
			String orderSortTableFieldBy = ORMLiteHelper
					.getDataFieldNameFromClassAttributeName(tableInfo,
							pSortOptions.getFields()[i]);
			String orderSortDirectionBy = ORMLiteHelper
					.getCompliantOrderDirectionSortString(pSortOptions
							.getDirections()[i]);
			if (null != orderSortTableFieldBy && null != orderSortDirectionBy) {
				StringBuilder lStrBuilder = new StringBuilder();
				lStrBuilder.append(orderSortTableFieldBy);
				lStrBuilder.append(" ");
				lStrBuilder.append(orderSortDirectionBy);
				orderByClauseArray.add(lStrBuilder.toString());
			} else {
				throw new GenericException("Le champ de tri fourni " + pSortOptions.getFields()[i] + " est vide.");
			}
		}
		return (!orderByClauseArray.isEmpty()) ? StringUtils.join(
				orderByClauseArray, ", ") : "1";
	}

	public static String getFullTextSearchWhereClause(
			String[] pFullTextSearchFields, String pSearchText)
			throws GenericException {
		if (null == pSearchText || pSearchText.isEmpty() || null == pFullTextSearchFields) {
			return null;
		}
		ArrayList<String> lArrayClauses = new ArrayList<String>();
		String[] lArrayWords = StringHelper.getWordsArray(pSearchText);
		for (String lWord : lArrayWords) {
			StringBuilder lStrBuilder = new StringBuilder();
			lStrBuilder.append("CONTAINS((");
			lStrBuilder.append(StringUtils.join(
					Arrays.asList(pFullTextSearchFields), ","));
			lStrBuilder.append("), ");
			lStrBuilder.append("'\"");
			lStrBuilder.append(lWord);
			lStrBuilder.append("*");
			lStrBuilder.append("\"'");
			lStrBuilder.append(") ");
			lArrayClauses.add(lStrBuilder.toString());
		}
		return StringUtils.join(lArrayClauses, " AND ");
	}

	@SuppressWarnings("unchecked")
	public static <M extends AModele<ID>, ID> M findItemInList(
			final M pSearchItem, ArrayList<M> pItemList) {
		return (M) CollectionUtils.find(pItemList,
				new org.apache.commons.collections.Predicate() {
					public boolean evaluate(Object object) {
						return ((M) object).getId() == pSearchItem.getId();
					}
				});
	}

}
