'use strict';

services.factory('SAbsenceFactory', function ($resource) {
		
	var factory = {};
	
	factory.stagiaire = {};
	
	//Absences ************************	
	factory.pagingOptions = {
			pageSizes : [5,10,15,20,25,30],
			pageSize : 10,
			currentPage : 1
	};
	
	factory.sortOptions = {
			fields: ["date"],
			directions: ["ASC"]
	};
	
	factory.filterOptions = {
			filterText: "",
			useExternalFilter: false
	};
	
	factory.createAbsenceRess = $resource('/ng_gst_pdg/web/absences/addOrUpdate', {}, {
		create : {method:'POST'}
	})
	
	factory.detailAbsences = $resource('/ng_gst_pdg/web/stagiaires/absences', {}, {
		load : {method:'POST'}
	});
	
	factory.deleteLine = $resource('/ng_gst_pdg/web/absences/addOrUpdate/:id', {}, {
		delete : {method:'DELETE', params : {id:'@idS'}}
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
					id				: factory.idStagiaire
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
		return factory.getAbsences(factory.stagiaire.id, factory.pagingOptions, factory.sortOptions, factory.filterOptions);
	}
	
	factory.create = function (absence) {
		var promise = factory.createAbsenceRess.create(absence).$promise.then(
				function (success) {
					return success;
				},
				
				function (error) {
					return error;
				}
		);
		
		return promise;
	}
	
	//Enregsitrement du stagiaire sélectionné
	factory.keepStagiaire = function (stagiaireSelected)  {
		factory.stagiaire = stagiaireSelected;
	}
	
	//Retour de la factory avec ses variables
	return factory;
	
});
