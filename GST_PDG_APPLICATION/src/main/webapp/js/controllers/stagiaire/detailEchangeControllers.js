/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailEchangeCtrl', function($scope, $rootScope, $http, echanges, SEchangeFactory, $filter, toaster, StagiaireFactory) {
    $scope.echanges = echanges.data;
    $scope.canEdit=SEchangeFactory.canEdit;
	$scope.canView=SEchangeFactory.canView;
    // Les echanges ou retards du stagiaire
    $scope.gridOptionsEchanges = {
        data: 'echanges',
        columnDefs : [
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
    	
    	SEchangeFactory.createLine.create(entity,
    			function (success) {
    				entity.editMode = false;
			    	SEchangeFactory.getEchanges.load(SEchangeFactory.pager, function(success) {
			    		$scope.echanges = success.data;
			    	});
			    	toaster.pop('success', null, "Echange enregistré");
				},
				
				function (error) {
					toaster.pop('error', null, error.data.message);
				}
		);
    };
    
    $scope.createEchange = function() {
    	$scope.echanges.push({isEchange:true, stagiaire: StagiaireFactory.stagiaire, auteur: $rootScope.utilisateurConnecte});
    };
    
    $scope.removeRow = function(entity) {
    	SEchangeFactory.deleteLine.delete({id: entity.id},
    			function(success) {
		    		SEchangeFactory.getEchanges.load(SEchangeFactory.pager, function(success) {
		    			$scope.echanges = success.data;
		    		});
		    		toaster.pop('warning', null, "Echange supprimé");
		    	},
    			function(error) {
		    		toaster.pop('error', null, error.data.message);
		    	}
    	);
    };
    
});