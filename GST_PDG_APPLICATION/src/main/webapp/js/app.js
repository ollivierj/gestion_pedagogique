'use strict';

var ng_gst_pdg = angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate', 'ui.router', 'angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker', 'toaster', 'angular-loading-bar', 'ngCookies'])

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
	$urlRouterProvider.otherwise("/login");
	
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
			controller: 'authentificationCtrl',
			hideMenus: true
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
			controller: 'planningReservationSalleCtrl',
			resolve: {
				planningElements : function(PlanningFactory) {
					return PlanningFactory.initElements(new Date());
				} 
			}
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
		}).
		// SAISIE DES ABSENCES ***********************************************
		state('absences', {
			url: '/absences',
			templateUrl: 'partials/absence/absence.html',
			controller: 'absencesCtrl',
			resolve: {
				absences: function(AbsencesFactory) {
					var date = new Date();
					return  AbsencesFactory.jour.getData({year:date.getUTCFullYear(), month : date.getUTCMonth(), day : date.getUTCDate()}).$promise;
				}
			}
		});
	
}).run(function ($rootScope, $location, $cookieStore, $http, $state) {
    $rootScope.utilisateurConnecte = $cookieStore.get('utilisateurConnecte') || {};
    $rootScope.authtoken = $cookieStore.get('authtoken') || {};
    if (!$rootScope.utilisateurConnecte.id || !$rootScope.utilisateurConnecte.profil || !$rootScope.utilisateurConnecte.profil.droits || !$rootScope.utilisateurConnecte.profil.droits.length>0){
    	$rootScope.utilisateurConnecte=null;
    }
    if (typeof $rootScope.authtoken != "string"){
    	 $rootScope.authtoken=null;
    }
    if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
    	$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
    }
    $rootScope.$on('$stateChangeStart', 
    		function(event, toState, toParams, fromState, fromParams){ 
    			if (toState.name !== 'login' && !$rootScope.utilisateurConnecte && !$rootScope.authtoken) {
    	        	event.preventDefault(); 
    	        	$state.go('login');
    	        }
    	        $rootScope.getMenuTitles();
    	        $rootScope.getMenuParametres();
				$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
    		});
});

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);
