/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailAbsenceCtrl', function($scope, absences, SAbsenceFactory, $filter, toaster) {
    
    $scope.absences = absences.data;
    
    console.log($scope.absences);
    
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
		showColumnMenu : true
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
    
    $scope.removeRow = function(entity) {
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