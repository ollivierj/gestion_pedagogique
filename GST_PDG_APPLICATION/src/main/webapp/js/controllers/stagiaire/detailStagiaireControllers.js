/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailStagiaireCtrl', function($scope, detail, $modal, $log, SEchangeFactory, $filter, toaster) {
		
    //Initialisation de la variable stagiaire du scope avec la variable detail (resolve de la route)
    $scope.stagiaire = detail;
        
    // Les échanges concernant le stagiaire
    $scope.gridOptionsEchanges = {
        data: 'stagiaire.echanges',
        columnDefs : [
                {field:'dateSaisie', displayName:'Date'},
                {field:"auteur", displayName:'Auteur'},
                {field:'commentaire', displayName:'Commentaire'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false
    };
     
 // Les avis concernant le stagiaire
    $scope.gridOptionsAvis = {
        data: 'stagiaire.avis',
        columnDefs : [
                {field:'date', displayName:'Date'},
                {field:"formateur", displayName:'Auteur'},
                {field:'avis', displayName:'Avis'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false
    };
    
    
    $scope.createEchange = function() {
		var modalAdd = $modal.open({
					templateUrl : 'partials/templates/form.html',
					controller : modalEditionEchangeCtrl,
					resolve : {
						title : function() {return "Ajout d'un échange";},
						readonly : function() {return false;},
						echange : function(){ return {}},
						schema : function(SEchangeFactory) {
							return SEchangeFactory.jsonschema.get().$promise;
						},
						okTitle : function() {return "Enregistrer";},
						ok : function() { return function(item){ return SEchangeFactory.createEchange.create(item);}}
					}
				});

		modalAdd.result.then(function(selectedItem) {
			
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

    
});