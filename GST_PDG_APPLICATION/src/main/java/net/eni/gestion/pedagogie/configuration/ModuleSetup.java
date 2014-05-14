package net.eni.gestion.pedagogie.configuration;

import net.eni.gestion.pedagogie.DAO.base.StagiaireBase;
import net.eni.gestion.pedagogie.DAO.base.implementation.StagiaireBaseImpl;
import net.eni.gestion.pedagogie.service.StagiaireService;
import net.eni.gestion.pedagogie.service.implementation.StagiaireServiceImpl;

import com.google.inject.AbstractModule;

/**
 * @author jollivier
 * Gestion des dépendances pour le module stagiaire
 */
public class ModuleSetup extends AbstractModule {
    @Override
    protected void configure() {
    	bind(StagiaireBase.class).to(StagiaireBaseImpl.class);
    	bind(StagiaireService.class).to(StagiaireServiceImpl.class);
    }
}
