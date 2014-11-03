package net.eni.gestion.pedagogie.commun.composant.pagination;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FilterOptions {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private String filterText;
	private boolean useExternalFilter;
	
	/**
	 * Constructeur à vide nécéssaire pour la sérialisation
	 */
	public FilterOptions() {
		super();
	}

	public String getFilterText() {
		return filterText;
	}

	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}

	public boolean isUseExternalFilter() {
		return useExternalFilter;
	}

	public void setUseExternalFilter(boolean useExternalFilter) {
		this.useExternalFilter = useExternalFilter;
	}

	
}
