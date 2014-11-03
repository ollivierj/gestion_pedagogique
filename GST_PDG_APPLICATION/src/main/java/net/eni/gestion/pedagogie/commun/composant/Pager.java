package net.eni.gestion.pedagogie.commun.composant;

import javax.xml.bind.annotation.XmlRootElement;

import net.eni.gestion.pedagogie.modele.Utilisateur;

@XmlRootElement
public class Pager {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private PagingOptions pagingOptions;
	private SortOptions sortOptions;
	private FilterOptions filterOptions;
	private Integer id;
	private Utilisateur connectedUser;
	
	/**
	 * Constructeur à vide nécéssaire pour la sérialisation
	 */
	public Pager() {
		super();
	}

	public PagingOptions getPagingOptions() {
		return pagingOptions;
	}

	public void setPagingOptions(PagingOptions pagingOptions) {
		this.pagingOptions = pagingOptions;
	}

	public SortOptions getSortOptions() {
		return sortOptions;
	}

	public void setSortOptions(SortOptions sortOptions) {
		this.sortOptions = sortOptions;
	}

	public FilterOptions getFilterOptions() {
		return filterOptions;
	}

	public void setFilterOptions(FilterOptions filterOptions) {
		this.filterOptions = filterOptions;
	}

	public Utilisateur getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(Utilisateur connectedUser) {
		this.connectedUser = connectedUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
