'use strict';

services.factory('SAbsenceFactory', function ($resource, StagiaireFactory, $rootScope) {
		
	var factory = {};
		
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
	
	factory.getAbsences = $resource('/ng_gst_pdg/web/absences/page', {}, {
		load : {method:'POST'}
	});
	
	factory.deleteLine = $resource('/ng_gst_pdg/web/absences/suppression/:id', {}, {
		delete : {method:'DELETE', params : {id:'@id'}}
	});
	
	factory.getAbsencesInit = function(pagerIn) {
		
		return factory.getAbsences.load(
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
	factory.canEdit = false;
	factory.canView = false;
	if ($rootScope.utilisateurConnecte){
	factory.canEdit = ($rootScope.utilisateurConnecte.profil.droits[4]=='ABS_E');
	factory.canView = ($rootScope.utilisateurConnecte.profil.droits[4]=='ABS_L'||factory.canEdit);
	}
	//Retour de la factory avec ses variables
	return factory;
	
});
