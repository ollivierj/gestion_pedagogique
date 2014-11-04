package net.eni.gestion.pedagogie.commun.outil;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import net.eni.gestion.pedagogie.commun.modele.generique.AModele;

public interface  SearchCallable<M extends AModele<ID>,ID> extends Callable<M> {
	void  setSearchItem (M pSearchItem);
	void setItemList(ArrayList<M> pItemList);
}
