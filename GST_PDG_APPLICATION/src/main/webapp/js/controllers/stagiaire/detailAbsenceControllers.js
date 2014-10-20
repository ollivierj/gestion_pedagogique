/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailAbsenceCtrl', function($scope, absences, SAbsenceFactory, $filter, toaster, StagiaireFactory) {
    
    $scope.absences = absences.data;
    
    $scope.totalServerItems = absences.totalServerItems;
    $scope.pager = SAbsenceFactory.pager;
    
    /*Permet d'alimenter le scope avec le résultat des requetes charger*/
    var initPager = function (pageLoad) {
    	$scope.totalServerItems = pageLoad.totalServerItems;
    	$scope.absences = pageLoad.data;
    }
    
    // Les absences ou retards du stagiaire
    $scope.gridOptionsAbsences = {
        data: 'absences',
        columnDefs : [
              	{displayName:'Absence / Retard', enableCellEdit: true,
              		editableCellTemplate: 'partials/stagiaire/template/absenceRetardButton.html',
          			cellTemplate: 'partials/stagiaire/template/absenceRetardButtonText.html'},
                {field:'formatedDate', displayName:'Date', enableCellEdit: true,
          			cellFilter: 'date : \'dd/MM/yyyy\'',
                	editableCellTemplate: 'partials/stagiaire/template/datepicker.html'},
            	{field:'formatedTime', displayName:'Heure', enableCellEdit: true,
            		editableCellTemplate: 'partials/stagiaire/template/timepicker.html',
                	cellTemplate: 'partials/stagiaire/template/timepickerText.html'},	
                {field:'auteur.nom', displayName:'Auteur', enableCellEdit: false},
                {field:'commentaire', displayName:'Motif', enableCellEdit: true,
                	editableCellTemplate: 'partials/stagiaire/template/textEdit.html'},
                {displayName:'Actions', cellTemplate: 'partials/stagiaire/template/actions.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false,
        enableColumnResize: true,
		enableColumnReordering : true,
		showColumnMenu : true,
		totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pager.pagingOptions,
        filterOptions: $scope.pager.filterOptions,
        sortInfo: $scope.pager.sortOptions
    };

	//L'édition d'une des colonnes du tableau active le mode edition
    $scope.editRow = function(entity) {
    	entity.editMode = true;
    };
    
    //Annule l'édition d'une ligne
    $scope.cancel = function(entity) {
    	entity.editMode = false;
    };
    
    $scope.ok = function (entity) {
    	entity.formatedDate = $filter('date')(entity.formatedDate, 'dd/MM/yyyy');
    	entity.formatedTime = $filter('date')(entity.formatedTime, 'HH:mm');
    	//Mock de l'auteur
    	entity.auteur = {};
    	entity.auteur.id = 1;
    	SAbsenceFactory.createLine.create(entity,
    			function (success) {
    				entity.editMode = false;
			    	SAbsenceFactory.getAbsences.load(SAbsenceFactory.pager, function(success) {
			    		initPager(success);
			    	});
			    	toaster.pop('success', null, "Absence enregistrée");
				},
				
				function (error) {
					toaster.pop('error', null, error.message);
				}
		);
    };
    
    $scope.createAbsence = function() {
    	console.log($scope.absences);
    	console.log(StagiaireFactory.stagiaire);
    	$scope.absences.push({isAbsence:true, stagiaire: StagiaireFactory.stagiaire});
    };
    
    $scope.removeRow = function(entity) {
    	SAbsenceFactory.deleteLine.delete({id: entity.id},
    			function(success) {
		    		SAbsenceFactory.getAbsences.load(SAbsenceFactory.pager, function(success) {
		    			initPager(success);
		    		});
		    		toaster.pop('warning', null, "Absence supprimée");
		    	},
    			function(error) {
		    		toaster.pop('error', null, error.message);
		    	}
    	);
    };
    
  //Watch equality permet de comparer toutes les données de l'objet aini que ses attributs. Avec la valeur true
    //Surveillance de la variable pagingOptions
    $scope.$watch('pager.pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
        	SAbsenceFactory.getAbsences.load(SAbsenceFactory.pager, function(success) {
        		initPager(success);
    		});
        }
    }, true);
    
    //Surveillance de la variable filterOptions
    $scope.$watch('pager.filterOptions', function (newVal, oldVal) {
    	if (newVal !== oldVal) {
    		if ($scope.timer) {
                $timeout.cancel($scope.timer);
            }
        	$scope.timer = $timeout(function () {
        		SAbsenceFactory.getAbsences.load(SAbsenceFactory.pager, function(success) {
            		initPager(success);
        		});
            }, 500);
    	}
    }, true);
    
    $scope.$watch('pager.sortOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
        	SAbsenceFactory.getAbsences.load(SAbsenceFactory.pager, function(success) {
        		initPager(success);
    		});
        }
    }, true);
    
});