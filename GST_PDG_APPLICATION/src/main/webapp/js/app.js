'use strict';

angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate', 'ui.router', 'angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker', 'toaster', 'angular-loading-bar'])

.config(function($stateProvider, $urlRouterProvider, $datepickerProvider, $timepickerProvider, cfpLoadingBarProvider) {
	
	cfpLoadingBarProvider.includeSpinner = false;
	
	angular.extend($datepickerProvider.defaults, {
        dateType: "string",
        modelDateFormat: "yyyy-MM-ddTHH:mm:ss",
    });
	
    angular.extend($timepickerProvider.defaults, {
        timeType: "string",
        modelTimeFormat: "yyyy-MM-ddTHH:mm:ss",
    });
	
	//	Url par défaut
	$urlRouterProvider.otherwise("/accueil");
	
	//	Mapping des url
	$stateProvider.
		state('accueil', {
			url: '/accueil',
			templateUrl: 'partials/accueil.html',
		}).
		// LOGIN ************************************************* 
		state('login', {
			url: '/login',
			templateUrl: 'partials/authentification/authentification.html',
			controller: 'authentificationCtrl'
		}).	
		// STAGIAIRE ************************************************* 
		state('stagiaires', {
			url: '/stagiaires',
			templateUrl: 'partials/stagiaire/stagiaire.html',
			controller: 'stagiaireCtrl'
		}).	
		state('detailStagiaire', {
			url: '/detailStagiaire',
			views : {
				'': {
					templateUrl: 'partials/stagiaire/detailStagiaire.html',
					controller: 'detailStagiaireCtrl',
					resolve: {
						detail: function (StagiaireFactory) {
							return StagiaireFactory.getDetail();
						}
					}
				},
				'absences@detailStagiaire': {
					templateUrl: 'partials/stagiaire/detailAbsence.html',
					controller: 'detailAbsenceCtrl',
					resolve: {
						absences: function(SAbsenceFactory) {
							return SAbsenceFactory.getAbsencesInit();
						}
					}
				},
				'echanges@detailStagiaire': {
					templateUrl: 'partials/stagiaire/detailEchange.html',
					controller: 'detailEchangeCtrl',
					resolve: {
						echanges: function(SEchangeFactory) {
							return SEchangeFactory.getEchangesInit();
						}
					}
				},
				'avis@detailStagiaire': {
					templateUrl: 'partials/stagiaire/detailAvis.html',
					controller: 'detailAvisCtrl',
					resolve: {
						avis: function(SAvisFactory) {
							return SAvisFactory.getAvisInit();
						}
					}
				}
			}
		}).
		//FICHE DE SYNTHESE ********************************************
		state('gestionFichesSynthese', {
			url: '/gestionFichesSynthese',
			templateUrl: 'partials/gestionFichesSynthese/gestionFichesSynthese.html',
			controller: 'gestionFichesSyntheseCtrl'
		}).
		//PLANNING SALLE *************************************************
		state('salles', {
			url: '/salles',
			templateUrl: 'partials/salle/planningReservationSalle.html',
			controller: 'planningReservationSalleCtrl'
		}).
		state('editionSalle', {
			url: '/salle/editer/:id',
			templateUrl: 'partials/salle/formulaireReservationSalle.html',
			controller: 'formulaireReservationSalleCtrl'
		}).
		//PROFFESSIONNELS HOMOLOGUES *****************************************
		state('professionnelHomologues', {
			url: '/professionnelHomologues',
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		//TITRES PROFESSIONNELS ***********************************************
		state('titreProfessionnels', {
			url: '/titreProfessionnels',
			templateUrl: 'partials/templates/list.html',
			controller: 'titreProfessionnelsCtrl'
		}).
		//SUJETS EVALUATIONS ***********************************************
		state('sujetsEvaluations', {
			url: '/sujetsEvaluations',
			templateUrl: 'partials/templates/list.html',
			controller: 'sujetEvaluationsCtrl'
		}).
		//EVALUATIONS ********************************************************
		state('evaluations', {
			url: '/evaluations',
			templateUrl: 'partials/templates/list.html',
			controller: 'evaluationsCtrl'
		}).
		//FICHIERS ***********************************************************
		state('fichiers', {
			url: '/fichiers',
			templateUrl: 'partials/fichiers.html',
			controller: 'fichiersCtrl'
		}).
		//PROFILS *************************************************************
		state('profils', {
			url: '/profils',
			templateUrl: 'partials/templates/list.html',
			controller: 'profilsCtrl'
		}).
		//UTILISATEURS ******************************************************
		state('utilisateurs', {
			url: '/utilisateurs',
			templateUrl: 'partials/templates/list.html',
			controller: 'utilisateursCtrl'
		}).
		//SESSION DE VALIDATION **********************************************
		state('sessionValidations', {
			url: '/sessionValidations',
			templateUrl: 'partials/templates/list.html',
			controller: 'sessionValidationsCtrl'
		});
});

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);