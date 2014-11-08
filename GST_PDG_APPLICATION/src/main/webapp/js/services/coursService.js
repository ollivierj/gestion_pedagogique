'use strict';

services.factory('CoursFactory', function ($resource) {
	
	var cours = {};
	
	cours.detail =  $resource('/ng_gst_pdg/web/courss/data/:id', {}, {
		getData : { method: 'GET', params: {id: '@id'}, isArray: false }
	});
	
	cours.instance =  $resource('/ng_gst_pdg/web/courss/instance', {}, {
		saveData : { method : 'POST' }
	});
	
	cours.instanceRefs = $resource('/ng_gst_pdg/web/instanceCours', {}, {
		getAll : { method : 'POST', isArray: true }
	});
	
	return cours;
});





