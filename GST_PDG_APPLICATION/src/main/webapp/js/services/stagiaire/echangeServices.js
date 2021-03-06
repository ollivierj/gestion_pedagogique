'use strict';

services.factory('SEchangeFactory', function ($resource, StagiaireFactory, $rootScope) {
		
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
	
	factory.createLine = $resource('/ng_gst_pdg/web/echanges/addOrUpdate', {}, {
		create : {method:'POST'}
	});
	
	factory.getEchanges = $resource('/ng_gst_pdg/web/echanges/page', {}, {
		load : {method:'POST'}
	});
	
	factory.deleteLine = $resource('/ng_gst_pdg/web/echanges/suppression/:id', {}, {
		delete : {method:'DELETE', params : {id:'@id'}}
	});
	
	
	factory.getEchangesInit = function() {
		return factory.getEchanges.load (
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
	factory.canEdit = ($rootScope.utilisateurConnecte.profil.droits[5]=='ECH_E');
	factory.canView = ($rootScope.utilisateurConnecte.profil.droits[5]=='ECH_L'||factory.canEdit);
	}
	
	//Retour de la factory avec ses variables
	return factory;
});
