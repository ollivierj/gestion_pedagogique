'use strict';

controllers.controller('consultationStagiairesCtrl', function($scope, $http, $location, stagiaireData, StagiairesFactory, $modal) {

    /*Variable contenant la sélection des données des tableaux de recherche*/
    var promotionSelected = [];
    $scope.stagiaireSelected = [];

    /*Liste de tous les stagiaires utilisée pour effectuer le filtre*/
    var allStagiaire = [];
    
    $scope.totalServerItems = 0;
    $scope.pagingOptions = StagiairesFactory.pagingOptions;
    $scope.filterOptions = StagiairesFactory.filterOptions;
    $scope.sortOptions = StagiairesFactory.sortOptions;
    
    /*Options des tableaux ng-grid*/
    $scope.gridOptionsStagiaire = {
        data: 'stagiaires',
        rowHeight: 80,
        selectedItems: $scope.stagiaireSelected,
        columnDefs : [
                {field:'photo', displayName:'Photo', cellTemplate: 'partials/templates/ng-grid_photo.html'},
                {field:'nom', displayName:'Nom'},
                {field:'prenom', displayName:'Prénom'},
                {field:'formatedDateNaissance', displayName:'Date de naissance'},
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
    
    $scope.viewDetails = function() {
        stagiaireData.set($scope.stagiaireSelected[0]);
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
    	StagiairesFactory.getData($scope.pagingOptions, $scope.sortOptions, $scope.filterOptions).then(
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

controllers.controller('detailsStagiairesCtrl', function($scope, stagiaireData) {

    $scope.planningSelected = [];

    // $scope.stagiaireSelected = $routeParams.stagiaire;
    $scope.stagiaireSelected = stagiaireData.get();

    // $scope.stagiaireSelected = {"nom":"Tata","prenom":"Tata","age":50,"promotion":"Micro"};

    // Les absences ou retards du stagiaire
    $scope.absences = [
        {date:'20/12/2014', heure:'10:20:30', motif:'pas de réveil'},
        {date:'13/01/2014', heure:'', motif:'malade'},
        {date:'02/06/2011', heure:'23:39:30', motif:'Accident sur la route'}
    ];

    $scope.gridOptionsAbsences = {
        data: 'absences',
        // selectedItems: $scope.planningSelected,
        multiSelect: false,
        columnDefs : [
                {field:'date', displayName:'Date'},
                {field:'heure', displayName:'Heure'},
                {displayName:'Auteur'},
                {field:'motif', displayName:'Motif'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ]
    };

    // Les avis concernant le stagiaire
    $scope.avis = [
        {formateur:'Jean', date:'20/12/2012', avis:'Bon élément, attentif'},
        {formateur:'Eric', date:'09/10/2016', avis:'Agité, non intéressé'},
        {formateur:'Patrick', date:'12/08/2011', avis:'S\'intéresse et pose les bonnes questions'}
    ];

    $scope.gridOptionsAvis = {
        data: 'avis',
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
    $scope.echanges = [
        {auteur:'Jean', date:'20/12/2013', echange:'Entretien validé'},
        {auteur:'Eric', date:'08/12/2013', echange:'En attente des réponses d\'entretien'},
        {auteur:'Patrick', date:'28/06/2013', echange:'En recherche d\'entreprise'}
    ];

    $scope.gridOptionsEchanges = {
        data: 'echanges',
        // selectedItems: $scope.planningSelected,
        multiSelect: false,
        columnDefs : [
                {field:'date', displayName:'Date'},
                {field:"auteur", displayName:'Auteur'},
                {field:'echange', displayName:'Echange'},
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