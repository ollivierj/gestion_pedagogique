'use strict';

// Declare app level module which depends on filters, and services
angular.module('ng_gst_pdg', ['ngRoute','ng_gst_pdg.filters', 'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'ng_gst_pdg.controllers']).
    config(function ($routeProvider, $locationProvider) {
        $routeProvider.when('/dummy', {templateUrl: 'partials/dummy.html', controller: 'DummyCtrl'});
        $routeProvider.when('/stagiaire-list', {templateUrl: 'partials/stagiaire-list.html', controller: 'StagiaireListCtrl'});
        $routeProvider.when('/stagiaire-detail/:id', {templateUrl: 'partials/stagiaire-detail.html', controller: 'StagiaireDetailCtrl'});
        $routeProvider.when('/stagiaire-creation', {templateUrl: 'partials/stagiaire-creation.html', controller: 'StagiaireCreationCtrl'});
        $routeProvider.otherwise({redirectTo: '/partial/dummy.html'});
    });
