'use strict';

controllers.controller('consultationPersonnesHomologueesCtrl', function($scope, $modal, $log, pHomologuee, homologationData) {

	$scope.personneHomologueeSelected = [];

  pHomologuee.query().$promise.then(function(data) {
    $scope.personnesHomologuees = data;
  });

	$scope.gridOptionsPersonnesHomologuees = {
        data: 'personnesHomologuees',
        selectedItems: $scope.personneHomologueeSelected,
        multiSelect: false,
        columnDefs : [
                {field:'nom', displayName:'Nom'},
                {field:'prenom', displayName:'Prénom'},
                {field:'civilite', displayName:'Civilité'},
                {field:'adresse', displayName:'Adresse'},
                {field:'codePostal', displayName:'Code postal'},
                {field:'ville', displayName:'Ville'},
                {field:'email', displayName:'E-mail'},
                {field:'homologation', displayName:'Homologation'},
                {field:'duree', displayName:'Durée'}
        ]
    };

    $scope.items = ['item1', 'item2', 'item3'];

    $scope.afficherFenetreEdition = function() {
    	var modalEdit = $modal.open({
            templateUrl: 'partials/personnesHomologuees/formulaireEditionPersonneHomologuee.html',
            controller: ModalEditionPersonneHomologueeCtrl,
            resolve: {
                items: function () {
                  return $scope.items;
                },
                homologations: function(homologationData) {
                  return homologationData.getAll().query().$promise;
                }
            }
        });
   
     	modalEdit.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
    };

});

var ModalEditionPersonneHomologueeCtrl = function ($scope, $modalInstance, items, homologations) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.homologations = homologations;

  $scope.ok = function () {
    $modalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};