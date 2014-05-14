'use strict';

/* Services */

/*
 http://docs.angularjs.org/api/ngResource.$resource

 Default ngResources are defined as

 'get':    {method:'GET'},
 'save':   {method:'POST'},
 'query':  {method:'GET', isArray:true},
 'remove': {method:'DELETE'},
 'delete': {method:'DELETE'}

 */

var services = angular.module('ng_gst_pdg.services', ['ngResource']);

services.factory('DummyFactory', function ($resource) {
    return $resource('/ng_gst_pdg/web/dummy', {}, {
        query: { method: 'GET', params: {}, isArray: false }
    })
});

services.factory('StagiairesFactory', function ($resource) {
    return $resource('/ng_gst_pdg/web/stagiaires', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('StagiaireFactory', function ($resource) {
    return $resource('/ng_gst_pdg/web/stagiaires/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
