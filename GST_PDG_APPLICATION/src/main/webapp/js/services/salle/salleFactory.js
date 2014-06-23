services.factory('salleFactory', function ($resource) {
    //return $resource('/ng_gst_pdg/web/salles/:id', {}, { Bouchonnage
	return $resource('json/salleLoad.json', {}, {
	      
	show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});