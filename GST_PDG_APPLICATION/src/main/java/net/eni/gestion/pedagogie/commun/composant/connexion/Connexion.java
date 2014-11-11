package net.eni.gestion.pedagogie.commun.composant.connexion;

import com.j256.ormlite.support.ConnectionSource;

public interface Connexion {
	ConnectionSource getConnection();
}
