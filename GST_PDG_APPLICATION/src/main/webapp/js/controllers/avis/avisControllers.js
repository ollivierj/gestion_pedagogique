'use strict';

/**
 * Controller de la page de l'affichage des avis
 */
controllers.controller('avisCtrl', function($scope, $rootScope, $http, $location, AvisFactory, StagiaireAvisFactory, $modal, $state, $timeout, toaster, FichiersFactory) {
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
    
    $scope.saveItem = function(item){
    	if (!item.avisTexte){
    		return "Vous devez saisir un avis";
    	}else if (item.avisTexte.length>500){
    		return "Le texte saisi ne doit pas dépasser 500 caractères";
    	}
    	
    	var avis = {
    			id :  item.avisId,
    			stagiaire : item.id,
    	    	instanceCours : item.avisInstanceCoursId || StagiaireAvisFactory.instanceCours.id,
    	    	texte : item.avisTexte,
    	    	auteur : $rootScope.utilisateurConnecte.id,
    	    	date : new Date()
    	};
    	if (!item.avisId){
    		return AvisFactory.create.doAction(
    				avis,
					function(success) {
						toaster.pop('success', null, "Avis enregistré");
						StagiaireAvisFactory.refreshData($scope);
					},
					function(error) {
						toaster.pop('error', null, error.data.message);
						StagiaireAvisFactory.refreshData($scope);
					}		
				);
    	}else{
    		return AvisFactory.modify.doAction(
    				avis,
					function(success) {
						toaster.pop('success', null, "Avis enregistré");
						StagiaireAvisFactory.refreshData($scope);
					},
					function(error) {
						toaster.pop('error', null, error.data.message);
						StagiaireAvisFactory.refreshData($scope);
					}		
				);
    	}
    	
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