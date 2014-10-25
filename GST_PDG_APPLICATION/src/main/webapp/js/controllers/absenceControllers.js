'use strict';
controllers
	.controller('absencesCtrl', function($scope, $log, $filter, $rootScope, $http, absences, toaster, AbsencesFactory, StagiaireFactory) {
	 if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
	   	$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
     }
	 $scope.title = "Absences";
	 $scope.canEdit=AbsencesFactory.canEdit;
	 $scope.canView=AbsencesFactory.canView;
	 $scope.absences = absences;
	 $scope.$watch('date', 
		function (newVal, oldVal) {
	        if (newVal !== oldVal) {
	        	if (null != newVal){
	        		var date = new Date(newVal);
		        	$scope.refreshData(date);
	        	}else{
	        		$scope.absences = [];
	        	}
	        }
	    }, true
	 );
	 $scope.date = new Date();
	 $scope.refreshData= function(date){
		AbsencesFactory.jour.getData({year:date.getUTCFullYear(), month : date.getUTCMonth(), day : date.getUTCDate()}, function(data) {
	    	$scope.absences = data;
	    });
	 };
	 $scope.gridOptionsAbsences = {
		        data: 'absences',
		        columnDefs : [
		              	{displayName:'Absence/Retard', enableCellEdit: false,
		              		cellTemplate: 'partials/absence/absence-bouton-retard-absence.html'},
		            	{field:'formatedTime', displayName:'Heure', enableCellEdit: true,
		            		editableCellTemplate: 'partials/stagiaire/template/timepicker.html',
		                	cellTemplate: 'partials/stagiaire/template/timepickerText.html'},	
		                	 {field:'stagiaire.prenom', displayName:'Prénom', enableCellEdit: false},
		                	 {field:'stagiaire.nom', displayName:'Nom', enableCellEdit: false},
		                {field:'commentaire', displayName:'Motif', enableCellEdit: true,
		                	editableCellTemplate: 'partials/stagiaire/template/textEdit.html'},
		                {displayName:'Actions', cellTemplate: 'partials/absence/absence-actions.html'}
		        ],
		        enablePaging: false,
		        showFooter: false,
		        multiSelect: false,
		        enableColumnResize: true,
				enableColumnReordering : true,
				showColumnMenu : true
		    };

		    $scope.editAbsence = function(absence) {
		    	var test = $filter('filter')($scope.absences, {editMode : true});
				if (0==test.length){
					absence.editMode = true;
				}
				return absence.editMode;
		    };
		    
		    $scope.cancelAbsence = function(absence) {
		    	absence.editMode = false;
		    };
		    
		    $scope.saveAbsence = function (absence) {
		    	absence.formatedDate = $filter('date')(absence.formatedDate, 'dd/MM/yyyy');
		    	absence.formatedTime = $filter('date')(absence.formatedTime, 'HH:mm');
		    	// Mock de l'auteur
		    	absence.auteur = {};
		    	absence.auteur.id = 1;
		    	if (null==absence.id){
		    		AbsencesFactory.create.doAction(absence,
			    			function (success) {
			    				absence.editMode = false;
			    				var date = new Date($scope.date);
			    	        	$scope.refreshData(date);
						    	toaster.pop('success', null, "Absence enregistrée");
							},
							
							function (error) {
								toaster.pop('error', null, error.message);
							}
					);
		    	}else {
		    		AbsencesFactory.modify.doAction(absence,
			    			function (success) {
			    				absence.editMode = false;
			    				var date = new Date($scope.date);
			    	        	$scope.refreshData(date);
						    	toaster.pop('success', null, "Absence enregistrée");
							},
							
							function (error) {
								toaster.pop('error', null, error.message);
							}
					);
		    	}
		    	
		    };
		    
		    $scope.chargerStagiaires = function(search) {
				return StagiaireFactory.stagiaireAutocomplete.getData({search: search}).$promise.then(function(data) {
					var stagiaires = [];
					angular.forEach(data, function(item) {
						stagiaires.push(item);
					});
					return stagiaires;
				});
			};
		    
		    $scope.addAbsence = function(item) {
		    	StagiaireFactory.stagiaireOrPromotion.getData({type: item.type, id : item.id}).$promise.then(function(data) {
					angular.forEach(data, function(stagiaire) {
						var test = $filter('filter')($scope.absences, {stagiaire : {id:stagiaire.id}});
						if (0==test.length){
							$scope.absences.push({isAbsence:true, stagiaire: stagiaire, formatedDate : $scope.date})
						}
					});
				});
		    	;
		    };
		    
		    $scope.removeAbsence = function(absence) {
		    	AbsencesFactory.delete.doAction({id: absence.id},
		    			function(success) {
				    		var date = new Date($scope.date);
		    	        	$scope.refreshData(date);
				    		toaster.pop('warning', null, "Absence supprimée");
				    	},
		    			function(error) {
				    		toaster.pop('error', null, error.message);
				    	}
		    	);
		    };
});
