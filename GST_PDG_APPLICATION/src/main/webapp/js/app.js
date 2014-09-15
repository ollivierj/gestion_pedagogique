'use strict';

angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate', 'ui.router', 'angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker', 'toaster'])

.config(function($stateProvider, $urlRouterProvider) {
	
	//	Url par défaut
	$urlRouterProvider.otherwise("/accueil");
	
	//	Mapping des url
	$stateProvider.
		state('accueil', {
			url: '/accueil',
			templateUrl: 'partials/accueil.html',
		}).
		// STAGIAIRE ************************************************* 
		state('stagiaire', {
			url: '/stagiaire',
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
		//SESSION DE VALIDATION *******************************************
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
		//PROFFESSIONNEL HOMOLOGUES *****************************************
		state('professionnelHomologues', {
			url: '/professionnelHomologues',
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		//TITRE PROFESSIONNEL ***********************************************
		state('titreProfessionnels', {
			url: '/titreProfessionnels',
			templateUrl: 'partials/templates/list.html',
			controller: 'titreProfessionnelsCtrl'
		}).
		//SUJET EVALUATION ***********************************************
		state('sujetsEvaluation', {
			url: '/sujetsEvaluation',
			templateUrl: 'partials/templates/list.html',
			controller: 'sujetEvaluationsCtrl'
		}).
		//EVALUATION ********************************************************
		state('evaluation', {
			url: '/evaluation',
			templateUrl: 'partials/templates/list.html',
			controller: 'evaluationsCtrl'
		}).
		//FICHIER ***********************************************************
		state('fichiers', {
			url: '/fichiers',
			templateUrl: 'partials/fichiers.html',
			controller: 'fichiersCtrl'
		}).
		//PROFIL *************************************************************
		state('gestionProfils', {
			url: '/gestionProfils',
			templateUrl: 'partials/droit/gestionDroit.html',
			controller: 'gestionDroitCtrl'
		}).
		//UTILISATEURS ******************************************************
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