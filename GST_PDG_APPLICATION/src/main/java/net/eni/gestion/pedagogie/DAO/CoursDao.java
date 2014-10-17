package net.eni.gestion.pedagogie.DAO;

import java.util.UUID;

import net.eni.gestion.pedagogie.modele.Cours;

/**
 * @author jollivier
 * Description des requêtes d'accès (écriture, lecture) en base de données 
 * pour la gestion des cours
 */
public interface CoursDao extends ADao<Cours, UUID> {

}
