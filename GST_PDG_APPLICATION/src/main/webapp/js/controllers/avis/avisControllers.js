'use strict';

/**
 * Controller de la page de l'affichage des avis
 */
controllers.controller('avisCtrl', function($scope, $rootScope, $http, $location, AvisFactory, StagiaireAvisFactory, $modal, $state, $timeout, FichiersFactory) {
	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
		$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
	}
    $scope.avisSelected = [];
    $scope.canEdit=StagiaireAvisFactory.canEdit;
	$scope.canView=StagiaireAvisFactory.canView;
    
    $scope.totalServerItems = 0;
    $scope.pagingOptions = StagiaireAvisFactory.pagingOptions;
    $scope.filterOptions = StagiaireAvisFactory.filterOptions;
    $scope.sortOptions = StagiaireAvisFactory.sortOptions;
    $scope.instanceCours = StagiaireAvisFactory.instanceCours;
    /*Options des tableaux ng-grid*/
    $scope.gridOptionsAvis = {
        data: 'avis',
        rowHeight: 80,
        selectedItems: $scope.avisSelected,
        columnDefs : [],
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
            templateUrl: 'partials/avis/detailPromotion.html',
            controller: ModalDisplayPromotionDetails,
            resolve: {
                promotion: function() {
                  return entity;
                }
            }
        });
    };
    
    $scope.exporter = function(){
		FichiersFactory.exporter('/ng_gst_pdg/web/avis/csv', $scope.pagingOptions, $scope.filterOptions, $scope.sortOptions);
	};
    
    $scope.viewRow = function (entity) {
    	/*AvisFactory.avis = entity;
    	$state.go('detailAvis');
    	AvisFactory.readonly=true;*/
    };
    
    $scope.editRow = function (entity) {
    	/*AvisFactory.avis = entity;
    	$state.go('detailAvis');
    	AvisFactory.readonly=false;*/
    };

    $scope.init = function(){
    	$('.ngHeaderContainer').hide();
    	$('.ngViewport').hide();
    	$('.ngTopPanel').hide();
    	$('.ngGrid').removeClass("gridStyle");
    };
	
    

    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
        	StagiaireAvisFactory.refreshData($scope);
        }
    }, true);

	$scope.$watch('filterOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
        	if ($scope.timer) {
                $timeout.cancel($scope.timer);
            }
        	$scope.timer = $timeout(function () {
        		StagiaireAvisFactory.refreshData($scope);
            }, 500);
        }
    }, true);

    $scope.$watch('sortOptions', function (newVal, oldVal) {
        if (newVal !== oldVal) {
        	StagiaireAvisFactory.refreshData($scope);
        }
    }, true);
	
    StagiaireAvisFactory.refreshData($scope);
    $timeout($scope.init);
});

var ModalDisplayPromotionDetails = function($scope, $modalInstance, promotion) {
    $scope.promotion = promotion;

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
};