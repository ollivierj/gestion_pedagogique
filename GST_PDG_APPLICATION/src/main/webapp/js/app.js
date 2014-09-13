'use strict';
angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate', 'ui.router',
//                              'angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker'])

.config(function($stateProvider, $urlRouterProvider) {
	
	//	Url par défaut
	$urlRouterProvider.otherwise("/accueil");
	
	//	Mapping des url
	$stateProvider.
		state('accueil', {
			url: '/accueil',
			templateUrl: 'partials/accueil.html',
		}).
		state('stagiaire', {
			url: '/stagiaire',
			templateUrl: 'partials/stagiaire/stagiaire.html',
			controller: 'stagiaireCtrl'
		}).	
		state('detailStagiaire', {
			url: '/detailStagiaire',
			templateUrl: 'partials/stagiaire/detailStagiaire.html',
			controller: 'detailStagiaireCtrl',
			resolve: {
				detail: function (StagiaireFactory) {
					return StagiaireFactory.getDetail();
				},
				absences: function(SAbsenceFactory) {
					return SAbsenceFactory.getAbsencesInit();
				}
			}
		}).
		state('gestionFichesSynthese', {
			url: '/gestionFichesSynthese',
			templateUrl: 'partials/gestionFichesSynthese/gestionFichesSynthese.html',
			controller: 'gestionFichesSyntheseCtrl'
		}).
		state('versionECF', {
			url: '/versionECF',
			templateUrl: 'partials/gestionEvaluations/versionECF.html',
			controller: 'versionECFCtrl'
		}).
		state('listeECF', {
			url: '/listeECF',
			templateUrl: 'partials/gestionEvaluations/listeECF.html',
			controller: 'listeECFCtrl'
		}).
		state('corrigerECF', {
			url: '/corrigerECF',
			templateUrl: 'partials/gestionEvaluations/corrigerECF.html',
			controller: 'corrigerECFCtrl'
		}).
		state('editECF', {
			url: '/editECF',
			templateUrl: 'partials/gestionEvaluations/editECF.html',
			controller: 'editECFCtrl'
		}).
		state('editCorrection', {
			url: '/editCorrection',
			templateUrl: 'partials/gestionEvaluations/editCorrection.html',
			controller: 'editCorrectionCtrl'
		}).
		state('editVersion', {
			url: '/editVersion',
			templateUrl: 'partials/gestionEvaluations/editVersion.html',
			controller: 'editVersionCtrl'
		}).
		state('salle', {
			url: '/salle',
			templateUrl: 'partials/salle/planningReservationSalle.html',
			controller: 'planningReservationSalleCtrl'
		}).
		state('editionSalle', {
			url: '/salle/editer/:id',
			templateUrl: 'partials/salle/formulaireReservationSalle.html',
			controller: 'formulaireReservationSalleCtrl'
		}).
		state('gestionSessionsValidation', {
			url: '/gestionSessionsValidation',
			templateUrl: 'partials/gestionSessionsValidation/gestionSessionsValidation.html',
			controller: 'gestionSessionsValidationCtrl',
			resolve : {
				initializeData : function(professionnelHomologuesFactory){
					professionnelHomologuesFactory.initializeData();
				}
			}
		}).
		state('professionnelHomologues', {
			url: '/professionnelHomologues',
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		state('titreProfessionnels', {
			url: '/titreProfessionnels',
			templateUrl: 'partials/templates/list.html',
			controller: 'titreProfessionnelsCtrl'
		}).
		state('sujetsEvaluation', {
			url: '/sujetsEvaluation',
			templateUrl: 'partials/templates/list.html',
			controller: 'sujetEvaluationsCtrl'
		}).
		state('fichiers', {
			url: '/fichiers',
			templateUrl: 'partials/fichiers.html',
			controller: 'fichiersCtrl'
		}).
		state('gestionProfils', {
			url: '/gestionProfils',
			templateUrl: 'partials/droit/gestionDroit.html',
			controller: 'gestionDroitCtrl'
		}).
		state('gestionUtilisateurs', {
			url: '/gestionUtilisateurs',
			templateUrl: 'partials/templates/list.html',
			controller: 'gestionUtilisateursCtrl'
		});
});

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);