'use strict';

services.factory('SAbsenceFactory', function ($resource) {
		
	var stagiaire = {};
	
	//Absences ************************	
	var pagingOptions = {
			pageSizes : [5,10,15,20,25,30],
			pageSize : 10,
			currentPage : 1
	};
	
	var sortOptions = {
			fields: ["date"],
			directions: ["ASC"]
	};
	
	var filterOptions = {
			filterText: "",
			useExternalFilter: false
	};
	
	var createAbsenceRess = $resource('/ng_gst_pdg/web/stagiaires/absences/creation', {}, {
		create : {method:'POST'}
	})
	
	var detailAbsences = $resource('/ng_gst_pdg/web/stagiaires/absences', {}, {
		load : {method:'POST'}
	});
	
	// Méthode de récupération des absences d'un stagiaire	
	var getAbsences = function (idStagiaire, pagingOptionsIn, sortOptionsIn, filterOptionsIn) {
		//Si aucune donnée du scope n'a été modifiée, onconserve les données initiales du service
		if (pagingOptionsIn != null && sortOptionsIn != null && filterOptionsIn != null) {
			//Enregistrement des données
			pagingOptions = pagingOptionsIn;
			sortOptions = sortOptionsIn;
			filterOptions = filterOptionsIn;
		}
		
		//Création d'une promise qui sera utilisée dans le controller
		//Utilisation de la variable qui contient l'appel au service
		var promise = detailAbsences.load (
				{
					pagingOptions 	: pagingOptions, 
					sortOptions 	: sortOptions, 
					filterOptions 	: filterOptions,
					id				: idStagiaire
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
	
	var getAbsencesInit = function() {
		return getAbsences(stagiaire.id, pagingOptions, sortOptions, filterOptions);
	};
	
	var create = function (absence) {
		var promise = createAbsenceRess.create(absence).$promise.then(
				function (sucess) {
					return success;
				},
				
				function (error) {
					return error;
				}
		);
		
		return promise;
	}
	
	//Enregsitrement du stagiaire sélectionné
	var keepStagiaire = function (stagiaireSelected)  {
		stagiaire = stagiaireSelected;
	};
	
	//Retour de la factory avec ses variables
	return {
		pagingOptions	: pagingOptions,
		sortOptions 	: sortOptions,
		filterOptions	: filterOptions,
		stagiaire		: stagiaire,
		keepStagiaire : keepStagiaire,
		getAbsences 	: getAbsences,
		getAbsencesInit : getAbsencesInit,
		create : create
	};
	
});
