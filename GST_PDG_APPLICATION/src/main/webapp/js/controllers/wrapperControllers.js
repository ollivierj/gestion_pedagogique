'use strict';

controllers.controller('wrapperCtrl', function($rootScope, $http, $scope, $modal, modalService, $log) {
	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
		$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
	}
	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.titleSelected = "";

	$scope.menuTitles=[];
	
	$rootScope.getMenuTitles = function(){
		$scope.menuTitles = [];
		var stagiairesMenu = 		{"mainTitle" : "Stagiaires", "iconTitle":"fa fa-search", "url":"stagiaires", "type":"single"};
		var evaluationsMenu = 		{"mainTitle" : "Evaluations", "iconTitle":"fa fa-file-text", "url":"evaluations", "type":"single"};
		var sessionsValidationMenu ={"mainTitle" : "Sessions de validation", "iconTitle":"fa fa-file-o", "url":"sessionValidations", "type":"single"};
		var reservationSallesMenu  ={"mainTitle" : "Salles", "iconTitle":"fa fa-building-o", "url":"salles", "type":"single"};
		var absencesMenu =			{"mainTitle" : "Absences", "iconTitle":"fa fa-calendar", "url":"absences", "type":"single"};	
		if ($rootScope.utilisateurConnecte){
			if ($rootScope.utilisateurConnecte.profil.droits[0]!='STG_A'){$scope.menuTitles.push(stagiairesMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[1]!='EVAL_A'){$scope.menuTitles.push(evaluationsMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[2]!='SES_VAL_A'){$scope.menuTitles.push(sessionsValidationMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[3]!='RES_SALLE_A'){$scope.menuTitles.push(reservationSallesMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[4]!='ABS_A'){$scope.menuTitles.push(absencesMenu);};
		};
	};
	
	$scope.menuParametres = [];
	$rootScope.getMenuParametres = function(){
		$scope.menuParametres = [];
		var utilisateursMenu = {sref: "utilisateurs", item : "Utilisateurs"};
		var profilsMenu = {sref: "profils", item : "Profils"};
		var sujetsEvaluationMenu = {sref: "sujetsEvaluations", item : "Sujets d'évaluation"};
		var titresProfessionnels = {sref: "titreProfessionnels", item : "Titres professionnels"};
		var professionnelsHomologues = {sref: "professionnelHomologues", item : "Professionnels homologués"};
		if ($rootScope.utilisateurConnecte){
			if ($rootScope.utilisateurConnecte.profil.droits[7]!='UTIL_A'){$scope.menuParametres.push(utilisateursMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[8]!='PRF_A'){$scope.menuParametres.push(profilsMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[9]!='SUJ_EVAL_A'){$scope.menuParametres.push(sujetsEvaluationMenu);};
			if ($rootScope.utilisateurConnecte.profil.droits[11]!='RES_SALLE_A'){$scope.menuParametres.push(titresProfessionnels);};
			if ($rootScope.utilisateurConnecte.profil.droits[10]!='PRF_HMG_A'){$scope.menuParametres.push(professionnelsHomologues);};
		};
	};
	
	$scope.menuParametres=$scope.getMenuParametres();
	
	$scope.setActive = function(title) {
		$scope.titleSelected = title;
	};
	
});