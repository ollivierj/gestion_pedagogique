'use strict';

services.factory('PersonnelHomologuesFactory', function ($resource) {
	return $resource('/ng_gst_pdg/web/professionnelHomologues/page/:page/:pageSize/:totalItems/:sortColumnBy/:sortDirectionBy', {}, {
        query: { method: 'GET', params: {page: '@page', pageSize: '@pageSize', totalItems : '@totalItems', sortColumnBy: '@sortColumnBy', sortDirectionBy : '@sortDirectionBy'} },
        create: { method: 'POST' }
    });
});