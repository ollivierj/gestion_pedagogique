'use strict';

services.factory('CoursFactory', function ($resource) {
	
	var cours = {};
	
	cours.detail =  $resource('/ng_gst_pdg/web/courss/detail/:id', {}, {
		getData : { method: 'GET', params: {id: '@id'}, isArray: false }
	});
	
	return cours;
});





