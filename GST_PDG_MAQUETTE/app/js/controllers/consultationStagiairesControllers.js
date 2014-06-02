'use strict';

controllers.controller('consultationStagiairesCtrl', function($scope, $http, $location, stagiaireData, $modal) {

    /*Variable contenant la sélection des données des tableaux de recherche*/
    var promotionSelected = [];
    $scope.stagiaireSelected = [];

    /*Liste de tous les stagiaires utilisée pour effectuer le filtre*/
    var allStagiaire = [];

    $scope.filterOptions = {
        filterText: "",
        useExternalFilter: true
    }; 
    $scope.totalServerItems = 0;
    $scope.pagingOptions = {
        pageSizes: [5, 10, 15, 25],
        pageSize: 10,
        currentPage: 1
    };  

    $scope.promotions = [
        {code:'AL3', nbStagiaire:12, annee:2009},
        {code:'MS2I', nbStagiaire:36, annee:2012},
        {code:'CDI', nbStagiaire:14, annee:2013},
        {code:'Micro', nbStagiaire:26, annee:2010}
    ];

    /*Options des tableaux ng-grid*/
    $scope.gridOptionsStagiaire = {
        data: 'stagiaires',
        rowHeight: 80,
        selectedItems: $scope.stagiaireSelected,
        multiSelect: false,
        // showColumnMenu:true,
        // showFilter:true,
        columnDefs : [
                {field:'photo', displayName:'Photo', cellTemplate: 'partials/templates/ng-grid_photo.html'},
                {field:'nom', displayName:'Nom'},
                {field:'prenom', displayName:'Prénom'},
                {field:'age', displayName:'Âge'},
                {field:'promotion', displayName:'Promotion', cellTemplate: 'partials/templates/ng-grid_detailsPromotion.html'},
                {field:'details', displayName:'Details', cellTemplate: 'partials/templates/ng-grid_details.html'}
        ],
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions
    };

    $scope.gridOptionsPromotion = { 
        data: 'promotions',
        selectedItems: $scope.promotionSelected,
        // showColumnMenu:true,
        // showFilter:true,
        afterSelectionChange : function(promotion) {
            selectChangePromotion(promotion);
        },
        multiSelect: false,
        columnDefs : [
                {field:'code', displayName:'Code'},
                {field:'nbStagiaire', displayName:'Nombre de stagiaires'},
                {field:'annee', displayName:'Année'}
        ],
        showFooter: true,
        enablePaging: true   
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

    /*Fonction permettant de filtrer la liste des stagiaires en fonction de la promotion sélectionnée*/
    $scope.selectChangePromotion = function(promotion) {
        /*On exécute le traitement seulement lors de la sélection de la données et non lors de la désélection*/
        if (promotion.selected == true) {
            if (allStagiaire.length == 0) {
                //Initialisation de la variable permettant de toujours avoir la totalité de stagiaires
                allStagiaire = $scope.stagiaires;
            };
            
            /*Réinitialisation des données afin de les réaffecter en fonction de la promotion*/
            $scope.stagiaires = [];

            for (var i = 0; i < allStagiaire.length; i++) {
                if (allStagiaire[i].promotion == promotion.entity.code) {
                    $scope.stagiaires.push(allStagiaire[i]); 
                };
            };
        };
    };


    /*Gestion de la pagination*/

    $scope.setPagingData = function(data, page, pageSize){  
        var pagedData = data.slice((page - 1) * pageSize, page * pageSize);
        $scope.stagiaires = pagedData;
        $scope.totalServerItems = data.length;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
    };

    $scope.getPagedDataAsync = function (pageSize, page, searchText) {
        setTimeout(function () {
            var data;
            if (searchText) {
                var ft = searchText.toLowerCase();
                /*$http.get('json/stagiairesLoad.json').success(function (largeLoad) {        
                    data = largeLoad.filter(function(item) {
                        return JSON.stringify(item).toLowerCase().indexOf(ft) != -1;
                    });
                    $scope.setPagingData(data,page,pageSize);
                });*/            
            } else {
                $http.get('json/stagiairesLoad.json').success(function (largeLoad) {
                    $scope.setPagingData(largeLoad,page,pageSize);
                });   
            }
        }, 100);
    };
    
    $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
    
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
        }
    }, true);
    $scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
          $scope.getPagedDataAsync($scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.filterOptions.filterText);
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

    // Les avis concernant le stagiaire
    $scope.avis = [
        {formateur:'Jean', date:'20/12/2012', avis:'Bon élément, attentif'},
        {formateur:'Eric', date:'09/10/2016', avis:'Agité, non intéressé'},
        {formateur:'Patrick', date:'12/08/2011', avis:'S\'intéresse et pose les bonnes questions'}
    ];

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
                {field:'date', displayName:'Date'}
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