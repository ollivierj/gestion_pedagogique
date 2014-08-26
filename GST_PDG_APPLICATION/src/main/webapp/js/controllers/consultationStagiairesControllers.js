'use strict';

/**
 * Controller de la page de l'affichage des stagiaires
 */
controllers.controller('consultationStagiairesCtrl', function($scope, $http, $location, stagiaireData, StagiaireFactory, $modal) {

    /*Variable contenant la sélection des données des tableaux de recherche*/
    var promotionSelected = [];
    $scope.stagiaireSelected = [];

    /*Liste de tous les stagiaires utilisée pour effectuer le filtre*/
    var allStagiaire = [];
    
    $scope.totalServerItems = 0;
    $scope.pagingOptions = StagiaireFactory.pagingOptions;
    $scope.filterOptions = StagiaireFactory.filterOptions;
    $scope.sortOptions = StagiaireFactory.sortOptions;
    
    /*Options des tableaux ng-grid*/
    $scope.gridOptionsStagiaire = {
        data: 'stagiaires',
        rowHeight: 80,
        selectedItems: $scope.stagiaireSelected,
        columnDefs : [
              	{field:'id', displayName:'Id'},  
                {field:'photo', displayName:'Photo', cellTemplate: 'partials/templates/ng-grid_photo.html'},
                {field:'nom', displayName:'Nom'},
                {field:'prenom', displayName:'Prénom'},
                {field:'dateNaissance', displayName:'Date de naissance'},
                {field:'promotion', displayName:'Promotion', cellTemplate: 'partials/templates/ng-grid_detailsPromotion.html'},
                {field:'details', displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_details.html'}
        ],
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        sortInfo: $scope.sortOptions
    };

    // Affiche une fenêtre modal avec les informations de la promotion
    $scope.displayPromotionDetails = function() {
        var modalEdit = $modal.open({
            templateUrl: 'partials/consultationStagiaires/detailsPromotion.html',
            controller: ModalDisplayPromotionDetails,
            resolve: {
                promotion: function() {
                  return promotionSelected;
                }
            }
        });
    };
    
    $scope.viewRow = function (entity) {
    	StagiaireFactory.changeStagiaire(entity);
        $location.path('/detailsStagiaire');
    };

    //Gestion du mode carte et du mode liste
    $scope.listDisplayModeEnabled = true;
    $scope.DisplayModeEnum = {
        Card: 0,
        List: 1
    };

    $scope.changeDisplayMode = function(displayMode) {
        switch (displayMode) {
            case $scope.DisplayModeEnum.Card:
                $scope.listDisplayModeEnabled = false;
                break;
            case $scope.DisplayModeEnum.List:
                $scope.listDisplayModeEnabled = true;
                break;
        }
    };

    $scope.refreshData = function () {
    	//Utilisation de la méthode du service permettant la récupération des données
    	//Cette méthode retourne une promise donc utilisation de .then()
    	StagiaireFactory.getData($scope.pagingOptions, $scope.sortOptions, $scope.filterOptions).then(
			function (success) {
				$scope.stagiaires = success.data;
		        $scope.totalServerItems = success.totalServerItems;
			},
			function (error) {
				alert('Error : ' + error);
			}
		);
    };
    
    $scope.refreshData();
    
    //Watch equality permet de comparer toutes les données de l'objet aini que ses attributs. Avec la valeur true
    //Surveillance de la variable pagingOptions
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
        	$scope.refreshData();
        }
    }, true);
    
    //Surveillance de la variable filterOptions
    $scope.$watch('filterOptions', function (newVal, oldVal) {
    	if (newVal !== oldVal) {
    		$scope.refreshData();
    	}
    }, true);

});

/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailsStagiairesCtrl', function($scope, stagiaireData, StagiaireFactory, detail) {

    $scope.planningSelected = [];
    
    //Initialisation de la variabe stagiaire du scope avec la variable detail (resolve de la route)
    $scope.stagiaire = detail;

    // Les absences ou retards du stagiaire
    $scope.gridOptionsAbsences = {
        data: 'stagiaire.absences',
        // selectedItems: $scope.planningSelected,
        multiSelect: false,
        columnDefs : [
                {field:'dateArriveeMatin', displayName:'Date arrivée du matin'},
                {field:'dateArriveeApresMidi', displayName:'Date arrivée de l\'après midi'},
                {field:'auteur', displayName:'Auteur'},
                {displayName:'Motif'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ]
    };

    // Les avis concernant le stagiaire
    $scope.gridOptionsAvis = {
        data: 'stagiaire.avis',
        // selectedItems: $scope.planningSelected,
        multiSelect: false,
        columnDefs : [
                {field:'date', displayName:'Date'},
                {field:"formateur", displayName:'Auteur'},
                {field:'avis', displayName:'Avis'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ]
    };

    // Les échanges concernant le stagiaire
    $scope.gridOptionsEchanges = {
        data: 'stagiaire.echanges',
        // selectedItems: $scope.planningSelected,
        multiSelect: false,
        columnDefs : [
                {field:'dateSaisie', displayName:'Date'},
                {field:"auteur", displayName:'Auteur'},
                {field:'commentaire', displayName:'Commentaire'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ]
    };

    $scope.planning = [
        {type:'Cours', libelle:'Java', date:'20/12/2013'},
        {type:'ECF', libelle:'Java', date:'25/12/2013'},
        {type:'Cours', libelle:'SQL', date:'03/02/2014'},
        {type:'Cours', libelle:'C#', date:'12/05/2014'},
        {type:'Cours', libelle:'Tomcat', date:'06/06/2014'},
        {type:'ECF', libelle:'C#', date:'26/09/2014'},
        {type:'Session de validation', libelle:'Examen final', date:'13/10/2014'}
    ];

     $scope.gridOptionsPlanning = {
        data: 'planning',
        selectedItems: $scope.planningSelected,
        multiSelect: false,
        columnDefs : [
                {field:'type', displayName:'Type'},
                {field:'libelle', displayName:'Libellé'},
                {field:'date', displayName:'Date de début'},
                {displayName:'Date de fin'},
                {displayName:'Heure'},
                {displayName:'Salle'}
        ]
    };
});

var ModalDisplayPromotionDetails = function($scope, $modalInstance, promotion) {
    $scope.promotion = promotion;

    $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};