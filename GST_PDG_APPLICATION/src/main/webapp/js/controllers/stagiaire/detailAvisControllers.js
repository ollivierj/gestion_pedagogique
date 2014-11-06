/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailAvisCtrl', function($scope, $rootScope, $http, avis, SAvisFactory, $filter, toaster) {
    $scope.avis = avis.data;
    $scope.canEdit=SAvisFactory.canEdit;
	$scope.canView=SAvisFactory.canView;
    // Les avis ou retards du stagiaire
    $scope.gridOptionsAvis = {
        data: 'avis',
        columnDefs : [
                {field:'formatedDate', displayName:'Date', enableCellEdit: true,
          			cellFilter: 'date : \'dd/MM/yyyy\'',
                	editableCellTemplate: 'partials/stagiaire/template/datepicker.html'},
            	{field:'formatedTime', displayName:'Heure', enableCellEdit: true,
            		editableCellTemplate: 'partials/stagiaire/template/timepicker.html',
                	cellTemplate: 'partials/stagiaire/template/timepickerText.html'},	
                {field:'auteur.nom', displayName:'Auteur', enableCellEdit: false},
                {field:'commentaire', displayName:'Raison', enableCellEdit: true,
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
    	SAvisFactory.createLine.create(entity,
    			function (success) {
    				entity.editMode = false;
			    	SAvisFactory.getAvis.load(SAvisFactory.pager, function(success) {
			    		$scope.avis = success.data;
			    	});
			    	toaster.pop('success', null, "Absence enregistrée");
				},
				
				function (error) {
					toaster.pop('error', null, error.data.message);
				}
		);
    };
    
    $scope.createAvis = function() {
    	$scope.avis.push({isAvis:true, stagiaire: SAvisFactory.stagiaire});
    };
    
    $scope.removeRow = function(entity) {
    	SAvisFactory.deleteLine.delete({id: entity.id},
    			function(success) {
		    		SAvisFactory.getAvis.load(SAvisFactory.pager, function(success) {
		    			$scope.avis = success.data;
		    		});
		    		toaster.pop('success', null, "Absence supprimée");
		    	},
    			function(error) {
		    		toaster.pop('error', null, error.data.message);
		    	}
    	);
    };
    
});