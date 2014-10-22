package net.eni.gestion.pedagogie.service;

import net.eni.gestion.pedagogie.modele.Utilisateur;

public interface Authentification
{
    public Utilisateur isAuthenticated(String authorization);
}
