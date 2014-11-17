'use strict';

/**
 * Controller de la page de l'affichage des stagiaires
 */
controllers.controller('stagiaireCtrl', function($scope, $rootScope, $http, $location, stagiaireData, StagiaireFactory, $modal, $state, $timeout, FichiersFactory) {
    $scope.stagiaireSelected = [];
    $scope.canEdit=StagiaireFactory.canEdit;
	$scope.canView=StagiaireFactory.canView;
    
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
                {field:'photo', displayName:'Photo', cellTemplate: 'partials/templates/ng-grid_photo.html'},
                {field:'nom', displayName:'Nom'},
                {field:'prenom', displayName:'Prénom'},
                {field:'dateNaissance', displayName:'Date de naissance'},
                {field:'libellePromotion', displayName:'Promotion', cellTemplate: 'partials/templates/ng-grid_detailsPromotion.html'},
                {field:'details', displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_details.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false,
        i18n : 'fr',
        useExternalSorting : true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        filterOptions: $scope.filterOptions,
        sortInfo: $scope.sortOptions
    };

    // Affiche une fenêtre modal avec les informations de la promotion
    $scope.displayPromotionDetails = function(entity) {
        var modalEdit = $modal.open({
            templateUrl: 'partials/stagiaire/detailPromotion.html',
            controller: ModalDisplayPromotionDetails,
            resolve: {
                promotion: function() {
                  return entity;
                }
            }
        });
    };
    
    $scope.exporter = function(){
		FichiersFactory.exporter('/ng_gst_pdg/web/stagiairesPromotions/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
	};
    
    $scope.viewRow = function (entity) {
    	StagiaireFactory.stagiaire = entity;
    	$state.go('detailStagiaire');
    	StagiaireFactory.readonly=true;
    };
    
    $scope.editRow = function (entity) {
    	StagiaireFactory.stagiaire = entity;
    	$state.go('detailStagiaire');
    	StagiaireFactory.readonly=false;
    };

    $scope.DisplayModeEnum = {
        Card: 0,
        List: 1
    };
    $scope.displayMode= $scope.DisplayModeEnum.List;
    $scope.ngGridHeight=null;
    $scope.changeMode = function(mode){
    	$scope.displayMode=mode;
    	switch(mode) {
	        case $scope.DisplayModeEnum.Card:
	        	$('.ngHeaderContainer').hide();
	        	$('.ngViewport').hide();
	        	$('.ngTopPanel').hide();
	        	$('.ngGrid').removeClass("gridStyle");
	            break;
	        case $scope.DisplayModeEnum.List:
	        	$('.ngHeaderContainer').show();
	        	$('.ngViewport').show();
	        	$('.ngTopPanel').show();
	        	$('.ngGrid').addClass("gridStyle");
	            break;
	        default:
	        	break;
    	}
    };
    

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
        	StagiaireFactory.refreshData($scope);
        }
    }, true);

	$scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
        	if ($scope.timer) {
                $timeout.cancel($scope.timer);
            }
        	$scope.timer = $timeout(function () {
        		StagiaireFactory.refreshData($scope);
            }, 500);
        }
    }, true);

    $scope.$watch('sortOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
        	StagiaireFactory.refreshData($scope);
        }
    }, true);
	
    StagiaireFactory.refreshData($scope);

});

var ModalDisplayPromotionDetails = function($scope, $modalInstance, promotion) {
    $scope.promotion = promotion;

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};