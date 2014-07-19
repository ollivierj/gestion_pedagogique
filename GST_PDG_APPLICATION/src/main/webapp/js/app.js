'use strict';
angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers','ng_gst_pdg.services', 'ng_gst_pdg.directives'])

.config(['$routeProvider', function($routeProvider) {
	$routeProvider.
		when('/accueil', {
			templateUrl: 'partials/accueil.html',
			controller: 'consultationStagiairesCtrl'
		}).
		when('/consultationStagiaires', {
			templateUrl: 'partials/consultationStagiaires/consultationStagiaires.html',
			controller: 'consultationStagiairesCtrl'
		}).	
		when('/detailsStagiaire', {
			templateUrl: 'partials/consultationStagiaires/detailsStagiaire.html',
			controller: 'detailsStagiairesCtrl'
		}).
		when('/gestionFichesSynthese', {
			templateUrl: 'partials/gestionFichesSynthese/gestionFichesSynthese.html',
			controller: 'gestionFichesSyntheseCtrl'
		}).
		when('/versionECF', {
			templateUrl: 'partials/gestionEvaluations/versionECF.html',
			controller: 'versionECFCtrl'
		}).
		when('/listeECF', {
			templateUrl: 'partials/gestionEvaluations/listeECF.html',
			controller: 'listeECFCtrl'
		}).
		when('/corrigerECF', {
			templateUrl: 'partials/gestionEvaluations/corrigerECF.html',
			controller: 'corrigerECFCtrl'
		}).
		when('/editECF', {
			templateUrl: 'partials/gestionEvaluations/editECF.html',
			controller: 'editECFCtrl'
		}).
		when('/editCorrection', {
			templateUrl: 'partials/gestionEvaluations/editCorrection.html',
			controller: 'editCorrectionCtrl'
		}).
		when('/editVersion', {
			templateUrl: 'partials/gestionEvaluations/editVersion.html',
			controller: 'editVersionCtrl'
		}).
		when('/salle', {
			templateUrl: 'partials/salle/planningReservationSalle.html',
			controller: 'planningReservationSalleCtrl'
		}).
		when('/salle/editer/:id', {
			templateUrl: 'partials/salle/formulaireReservationSalle.html',
			controller: 'formulaireReservationSalleCtrl'
		}).
		when('/gestionSessionsValidation', {
			templateUrl: 'partials/gestionSessionsValidation/gestionSessionsValidation.html',
			controller: 'gestionSessionsValidationCtrl',
			resolve : {
				initializeData : function(professionnelHomologuesFactory){
					professionnelHomologuesFactory.initializeData();
				}
			}
		}).
		when('/professionnelHomologues', {
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		when('/titreProfessionnels', {
			templateUrl: 'partials/templates/list.html',
			controller: 'titreProfessionnelsCtrl'
		}).
		when('/sujetsEvaluation', {
			templateUrl: 'partials/templates/list.html',
			controller: 'sujetEvaluationsCtrl'
		}).
		when('/gestionDroit', {
			templateUrl: 'partials/droit/gestionDroit.html',
			controller: 'gestionDroitCtrl'
		}).
		otherwise({
			redirectTo: '/accueil'
		});
}]);

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);