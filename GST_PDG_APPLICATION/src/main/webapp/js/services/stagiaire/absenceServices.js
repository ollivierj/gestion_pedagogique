'use strict';

services.factory('SAbsenceFactory', function ($resource, StagiaireFactory) {
		
	var factory = {};
	
	factory.stagiaire = {};
		
	factory.pager = {
			pagingOptions : {
					pageSizes : [5,10,15,20,25,30],
					pageSize : 10,
					currentPage : 1
			},
			sortOptions : {
					fields: ["date"],
					directions: ["ASC"]
			},
			filterOptions : {
					filterText: "",
					useExternalFilter: false
			},
			id : StagiaireFactory.stagiaire.id
	}; 
	
	factory.createLine = $resource('/ng_gst_pdg/web/absences/addOrUpdate', {}, {
		create : {method:'POST'}
	});
	
	factory.detailAbsences = $resource('/ng_gst_pdg/web/stagiaires/absences', {}, {
		load : {method:'POST'}
	});
	
	factory.deleteLine = $resource('/ng_gst_pdg/web/absences/suppression/:id', {}, {
		delete : {method:'DELETE', params : {id:'@id'}}
	});
	
	// Méthode de récupération des absences d'un stagiaire	
	factory.getAbsences = function (idStagiaire, pagingOptionsIn, sortOptionsIn, filterOptionsIn) {
		//Si aucune donnée du scope n'a été modifiée, onconserve les données initiales du service
		if (pagingOptionsIn != null && sortOptionsIn != null && filterOptionsIn != null) {
			//Enregistrement des données
			factory.pagingOptions = pagingOptionsIn;
			factory.sortOptions = sortOptionsIn;
			factory.filterOptions = filterOptionsIn;
		}
		
		//Création d'une promise qui sera utilisée dans le controller
		//Utilisation de la variable qui contient l'appel au service
		var promise = factory.detailAbsences.load (
				{
					pagingOptions 	: factory.pagingOptions, 
					sortOptions 	: factory.sortOptions, 
					filterOptions 	: factory.filterOptions,
					id				: StagiaireFactory.stagiaire.idStagiaire
				}
			).$promise.then (
				//Retour de la méthode dans un cas success
				function (success) {
					return success;
				},
				//Retour de la methode dans un cas error
				function (error) {
					return error;
				}
			);
		
		//On retourne la promise
		return promise;
	}
	
	factory.getAbsencesInit = function() {
		return factory.detailAbsences.load (
				factory.pager,
				//Retour de la méthode dans un cas success
				function (success) {
					return success;
				},
				//Retour de la methode dans un cas error
				function (error) {
					return error;
				}
			).$promise;
	}
	
	//Enregsitrement du stagiaire sélectionné
	factory.keepStagiaire = function (stagiaireSelected)  {
		factory.stagiaire = stagiaireSelected;
	}
	
	//Retour de la factory avec ses variables
	return factory;
	
});
