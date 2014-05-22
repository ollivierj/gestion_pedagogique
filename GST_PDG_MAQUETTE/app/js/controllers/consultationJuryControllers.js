'use strict';

controllers.controller('consultationJuryCtrl', function($scope, $modal, $log) {

	$scope.personneHomologueeSelected = [];

	$scope.personnesHomologuees = [
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""},
		{"nom":"Jamin", "prenom":"Kévin", "civilite":22, "adresse":"AL3", "codePostal":"", "ville":"", "email":""}
	];

	$scope.gridOptionsJury = {
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
                {field:'email', displayName:'E-mail'}
        ]
    };

    $scope.items = ['item1', 'item2', 'item3'];

    $scope.afficherFenetreEdition = function() {
    	var modalEdit = $modal.open({
            templateUrl: 'partials/jury/formulaireEditionPersonneHomologuee.html',
            controller: ModalEditionPersonneHomologueeCtrl,
            resolve: {
                items: function () {
                  return $scope.items;
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