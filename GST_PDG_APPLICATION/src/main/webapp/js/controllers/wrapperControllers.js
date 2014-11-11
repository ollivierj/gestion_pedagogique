'use strict';

controllers
		.controller(
				'wrapperCtrl',
				function($rootScope, $http, $scope, $modal, modalService, $log) {

					$scope.showInformation = function() {
						var modalInfo = $modal
								.open({
									templateUrl : 'partials/information.html',
									controller : modalUtilisateurInformationCtrl,
									resolve : {
										title : function() {
											return "Informations de l'utilisateur connecté";
										},
										utilisateur : function() {
											return $rootScope.utilisateurConnecte;
										}
									}
								});
						modalInfo.result.then(function(selectedItem) {
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					$scope.titleSelected = "";

					$scope.menuTitles = [];

					$rootScope.getMenuTitles = function() {
						$scope.menuTitles = [];

						var sujetsEvaluationMenu = {
							"mainTitle" : "Sujets d'évaluation",
							"iconTitle" : "fa fa-file-text-o",
							"url" : "sujetsEvaluations",
							"type" : "single"
						};
						var typesSessionMenu = {
							"mainTitle" : "Types de session de validation",
							"iconTitle" : "fa fa-list",
							"url" : "typeSessions",
							"type" : "single"
						};
						var professionnelsHomologuesMenu = {
							"mainTitle" : "Professionnels homologués",
							"iconTitle" : "fa fa-users",
							"url" : "professionnelHomologues",
							"type" : "single"
						};
						var stagiairesMenu = {
							"mainTitle" : "Stagiaires",
							"iconTitle" : "fa fa-search",
							"url" : "stagiaires",
							"type" : "single"
						};
						var evaluationsMenu = {
							"mainTitle" : "Evaluations",
							"iconTitle" : "fa fa-file-text",
							"url" : "evaluations",
							"type" : "single"
						};
						var sessionsValidationMenu = {
							"mainTitle" : "Sessions de validation",
							"iconTitle" : "fa fa-graduation-cap",
							"url" : "sessionValidations",
							"type" : "single"
						};
						var sallesRefMenu = {
							"mainTitle" : "Salles",
							"iconTitle" : "fa fa-key",
							"url" : "refsalles",
							"type" : "single"	
						};
						var reservationSallesMenu = {
							"mainTitle" : "Réservation de salles",
							"iconTitle" : "fa fa-calendar",
							"url" : "salles",
							"type" : "single"
						};
						var absencesMenu = {
							"mainTitle" : "Absences",
							"iconTitle" : "fa fa-times-circle",
							"url" : "absences",
							"type" : "single"
						};
						var avisMenu = {
							"mainTitle" : "Avis",
							"iconTitle" : "fa fa-comment-o",
							"url" : "instanceCours",
							"type" : "single"
						};
						if ($rootScope.utilisateurConnecte) {
							if ($rootScope.utilisateurConnecte.profil.droits[0] != 'STG_A') {
								$scope.menuTitles.push(stagiairesMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[4] != 'ABS_A') {
								$scope.menuTitles.push(absencesMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[4] != 'AVIS_A') {
								$scope.menuTitles.push(avisMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[9] != 'SUJ_EVAL_A') {
								$scope.menuTitles.push(sujetsEvaluationMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[1] != 'EVAL_A') {
								$scope.menuTitles.push(evaluationsMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[11] != 'TR_PRF_A') {
								$scope.menuTitles.push(typesSessionMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[10] != 'PRF_HMG_A') {
								$scope.menuTitles
										.push(professionnelsHomologuesMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[2] != 'SES_VAL_A') {
								$scope.menuTitles.push(sessionsValidationMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[3] != 'RES_SALLE_A') {
								$scope.menuTitles.push(sallesRefMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[3] != 'RES_SALLE_A') {
								$scope.menuTitles.push(reservationSallesMenu);
							}
							;
						}
						;
					};

					$scope.menuParametres = [];
					$rootScope.getMenuParametres = function() {
						$scope.menuParametres = [];
						var utilisateursMenu = {
							sref : "utilisateurs",
							item : " Utilisateurs"
						};
						var profilsMenu = {
							sref : "profils",
							item : " Profils"
						};
						if ($rootScope.utilisateurConnecte) {
							if ($rootScope.utilisateurConnecte.profil.droits[7] != 'UTIL_A') {
								$scope.menuParametres.push(utilisateursMenu);
							}
							;
							if ($rootScope.utilisateurConnecte.profil.droits[8] != 'PRF_A') {
								$scope.menuParametres.push(profilsMenu);
							}
							;
						}
						;
					};

					$scope.menuParametres = $scope.getMenuParametres();

					$scope.setActive = function(title) {
						$scope.titleSelected = (title)? (' - '+title):'';
					};

				});

var modalUtilisateurInformationCtrl = function($scope, $modalInstance,
		title, utilisateur, $filter) {
	$scope.title = title;
	$scope.utilisateur = utilisateur;
	$scope.utilisateur.dateExpiration =  $filter('date')($scope.utilisateur.dateExpiration, 'le dd/MM/yyyy à HH:mm:ss'),
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};