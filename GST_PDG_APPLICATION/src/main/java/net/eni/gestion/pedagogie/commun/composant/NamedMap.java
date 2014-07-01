/**
 * 
 */
package net.eni.gestion.pedagogie.commun.composant;

import java.util.HashMap;


/**
 * @author jollivier
 *
 */
public class NamedMap<V> extends HashMap<String, V> {

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public <T> T getTyped(String key, Class<T> type) {
		V value = get(key);
		try {
			return (T)value;
		}
		catch(Exception pException) {
			return null;
		}
	}	
}
