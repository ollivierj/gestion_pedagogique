'use strict';

services.factory('FichiersFactory', function ($resource) {
	var filterOptions = {
        filterText: "",
        useExternalFilter: true
	};
	return {
		filterOptions : filterOptions,
		fichiers : $resource('/ng_gst_pdg/web/fichiers/charger/:entite_type/:entite_id', {}, {
			getData : { method: 'GET', isArray: true, params: {entite_type: '@entite_type', entite_id: '@entite_id'}}
		}),
		telecharger : $resource('/ng_gst_pdg/web/fichiers/telecharger/:entite_type/:entite_id/:filename', {}, {
			getData : { method: 'GET', params: {entite_type: '@entite_type', entite_id: '@entite_id', filename : '@filename'}}
		}),
		delete :  $resource('/ng_gst_pdg/web/fichiers/supprimer/:entite_type/:entite_id/:filename', {}, {
			doAction : { method: 'DELETE', params: {entite_type: '@entite_type', entite_id: '@entite_id', filename : '@filename' }}
		})
	};
});





