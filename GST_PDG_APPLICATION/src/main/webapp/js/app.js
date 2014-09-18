'use strict';

angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate', 'ui.router', 'angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker', 'toaster'])

.config(function($stateProvider, $urlRouterProvider, $datepickerProvider, $timepickerProvider) {
	
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
				}
			}
		}).
		//FICHE DE SYNTHESE ********************************************
		state('gestionFichesSynthese', {
			url: '/gestionFichesSynthese',
			templateUrl: 'partials/gestionFichesSynthese/gestionFichesSynthese.html',
			controller: 'gestionFichesSyntheseCtrl'
		}).
		//EVALUATION ****************************************************
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
		//SESSIONS DE VALIDATION *******************************************
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
			templateUrl: 'partials/droit/gestionDroit.html',
			controller: 'gestionDroitCtrl'
		}).
		//UTILISATEURS ******************************************************
		state('utilisateurs', {
			url: '/utilisateurs',
			templateUrl: 'partials/templates/list.html',
			controller: 'gestionUtilisateursCtrl'
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