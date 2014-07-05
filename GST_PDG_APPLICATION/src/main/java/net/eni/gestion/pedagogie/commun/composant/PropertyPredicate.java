package net.eni.gestion.pedagogie.commun.composant;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.Predicate;

public class PropertyPredicate implements Predicate {

	private Object expected;
	private String propertyName;

	public PropertyPredicate(String propertyName, Object expected) {
		super();
		this.propertyName = propertyName;
		this.expected = expected;
	}

	public boolean evaluate(Object object) {
		try {
			return expected.equals(PropertyUtils.getProperty(object,
					propertyName));
		} catch (Exception e) {
			return false;
		}
	}

}