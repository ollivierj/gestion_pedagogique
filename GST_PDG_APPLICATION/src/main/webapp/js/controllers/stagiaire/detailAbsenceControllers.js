/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailAbsenceCtrl', function($scope, absences, SAbsenceFactory, $filter, toaster) {
    
    $scope.absences = absences.data;
    
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
                {displayName:'Actions', cellTemplate: 'partials/stagiaire/template/actionsAbsence.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false,
        enableColumnResize: true,
		enableColumnReordering : true,
		showColumnMenu : true
    };

	//L'édition d'une des colonnes du tableau active le mode edition
    $scope.editRowAbsence = function(entity) {
    	entity.editModeAbsence = true;
    };
    
    //Annule l'édition d'une ligne
    $scope.cancelAbsence = function(entity) {
    	entity.editModeAbsence = false;
    };
    
    $scope.okAbsence = function (entity) {
    	entity.formatedDate = $filter('date')(entity.formatedDate, 'dd/MM/yyyy');
    	entity.formatedTime = $filter('date')(entity.formatedTime, 'HH:mm');
    	//Mock de l'auteur
    	entity.auteur = {};
    	entity.auteur.id = 1;
    	SAbsenceFactory.createLine.create(entity,
    			function (success) {
    				entity.editModeAbsence = false;
			    	SAbsenceFactory.detailAbsences.load(SAbsenceFactory.pager, function(success) {
			    		$scope.absences = success.data;
			    	});
			    	toaster.pop('success', null, "Enregistrement de l'absence effectuée");
				},
				
				function (error) {
					toaster.pop('error', null, error.message);
				}
		);
    };
    
    $scope.createAbsence = function() {
    	$scope.absences.push({isAbsence:true, stagiaire: SAbsenceFactory.stagiaire});
    };
    
    $scope.removeRowAbsence = function(entity) {
    	SAbsenceFactory.deleteLine.delete({id: entity.id},
    			function(success) {
		    		SAbsenceFactory.detailAbsences.load(SAbsenceFactory.pager, function(success) {
		    			$scope.absences = success.data;
		    		});
		    		toaster.pop('success', null, "Suppression de l'absence effectuée");
		    	},
    			function(error) {
		    		toaster.pop('error', null, error.message);
		    	}
    	);
    };
    
});