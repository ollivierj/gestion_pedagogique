'use strict';

var ng_gst_pdg = angular.module('ng_gst_pdg', ['ngRoute','ngSanitize', 'ngGrid', 'ngAnimate', 'ui.router', 'angularFileUpload', 
                              'schemaForm','ui.bootstrap','ui.tree','ng_gst_pdg.filters','ng_gst_pdg.controllers',
                              'ng_gst_pdg.services', 'ng_gst_pdg.directives', 'mgcrea.ngStrap.timepicker',
                              'mgcrea.ngStrap.datepicker', 'toaster', 'angular-loading-bar', 'ngCookies', 'xeditable'])

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
							return StagiaireFactory.detail.getData({id : StagiaireFactory.stagiaire.id}).$promise;
						}
					}
				},
				'fichiersStagiaire@detailStagiaire': {
					templateUrl: 'partials/stagiaire/detailFichier.html',
					controller: 'detailFichierCtrl',
					resolve: {
						fichiers : function(FichiersFactory, StagiaireFactory) {
							return FichiersFactory.fichiers.getData({entite_type : "Stagiaire", entite_id : StagiaireFactory.stagiaire.id}).$promise;
						},
						readonly : function() {return true;},
						affFichiers : function() {return true;},
						affTelech : function() {return true;}
					}
				},
				'absencesStagiaire@detailStagiaire': {
					templateUrl: 'partials/stagiaire/detailAbsence.html',
					controller: 'detailAbsenceCtrl',
					resolve: {
						absences: function(SAbsenceFactory) {
							return SAbsenceFactory.getAbsencesInit();
						}
					}
				},
				'echangesStagiaire@detailStagiaire': {
					templateUrl: 'partials/stagiaire/detailEchange.html',
					controller: 'detailEchangeCtrl',
					resolve: {
						echanges: function(SEchangeFactory) {
							return SEchangeFactory.getEchangesInit();
						}
					}
				},
				'avisStagiaire@detailStagiaire': {
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
		state('refsalles', {
			url: '/salles',
			templateUrl: 'partials/templates/list.html',
			controller: 'sallesCtrl'
		}).
		//PROFFESSIONNELS HOMOLOGUES *****************************************
		state('professionnelHomologues', {
			url: '/professionnelHomologues',
			templateUrl: 'partials/templates/list.html',
			controller: 'professionelHomologuesCtrl'
		}).
		//TITRES PROFESSIONNELS ***********************************************
		state('typeSessions', {
			url: '/typeSessions',
			templateUrl: 'partials/templates/list.html',
			controller: 'typeSessionsCtrl'
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
		}).
		// SAISIE DES AVIS ***********************************************
		state('instanceCours', {
			url: '/instanceCours',
			templateUrl: 'partials/avis/instanceCours.html',
			controller: 'instanceCoursCtrl'
		}).
		state('avis', {
			url: '/avis',
			templateUrl: 'partials/avis/avis.html',
			controller: 'avisCtrl'
		});
	
}).run(function ($rootScope, $location, $cookieStore, $http, $state, editableOptions, AuthentificationFactory) {
	$rootScope.authtoken = $cookieStore.get("token");
    if (null==$rootScope.authtoken){
    	$rootScope.utilisateurConnecte=null;
    }
    $rootScope.$on('$stateChangeStart', 
    		function(event, toState, toParams, fromState, fromParams){ 
    			if (!$rootScope.utilisateurConnecte && $rootScope.authtoken){
    				AuthentificationFactory.getUtilisateurFromToken()
                    .success(function (data) {
                    	$rootScope.hideMenus = false;
                        AuthentificationFactory.setCredentials(data);
                        $rootScope.getMenuTitles();
            	        $rootScope.getMenuParametres();
                        $state.go('accueil');
                    })
                    .error(function (data) {
                    	$rootScope.hideMenus = true;
                    	$state.go('login');
                    });
    			}else if (toState.name !== 'login' && !$rootScope.authtoken) {
    	        	event.preventDefault(); 
    	        	$state.go('login');
    	        }else{
    	        	 $rootScope.getMenuTitles();
    	    	     $rootScope.getMenuParametres();
    	        }
    	       
    		});
    editableOptions.theme = 'bs3';
});

var filters = angular.module('ng_gst_pdg.filters', []);
var services = angular.module('ng_gst_pdg.services', ['ngResource']);
var controllers = angular.module('ng_gst_pdg.controllers', []);
var directives = angular.module('ng_gst_pdg.directives', ['ui.calendar']);
