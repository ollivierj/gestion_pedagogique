'use strict';

controllers.controller('consultationPersonnesHomologueesCtrl', function($scope, $modal, $log, PersonnelHomologuesFactory, homologationData) {

	$scope.personneHomologueeSelected = [];

	$scope.totalServerItems = 0;
    $scope.pagingOptions = {
            pageSizes: [5, 10, 15, 25],
            pageSize: 10,
            currentPage: 1
        };  

	$scope.gridOptionsPersonnesHomologuees = {
        data: 'personnelHomologues',
        selectedItems: $scope.personneHomologueeSelected,
        multiSelect: false,
        columnDefs : [
                {field:'nom', displayName:'Nom'},
                {field:'prenom', displayName:'Prénom'},
                {field:'civilite', displayName:'Civilité'},
                {field:'dateNaissance', displayName:'Date de naissance'},
                {field:'adresse', displayName:'Adresse'},
                {field:'codePostal', displayName:'Code postal'},
                {field:'ville', displayName:'Ville'},
                {field:'email', displayName:'E-mail'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ],
        enablePaging: true,
        showFooter: true,
        totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions
    };
	
    $scope.setPagingData = function(data, page, pageSize, totalItems){  
        $scope.personnelHomologues = data;
        $scope.totalServerItems = totalItems;
        if (!$scope.$$phase) {
            $scope.$apply();
        }
        $scope.pagingOptions.pageSize = pageSize;
        $scope.pagingOptions.currentPage = page;
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
               /*$http.get('json/stagiairesLoad.json').success(function (largeLoad) {
                    $scope.setPagingData(largeLoad,page,pageSize);
                });  */
            	/*
            	 * Teste coté base OK
            	 */
            	 var unepromesse = PersonnelHomologuesFactory.query({page : $scope.pagingOptions.currentPage, pageSize : $scope.pagingOptions.pageSize, totalItems : $scope.totalServerItems , sortColumnBy : "PRF_HMG_ID", sortDirectionBy : "DESC"});
            	 unepromesse.$promise.then(
            	        function(response){
            	        	$scope.setPagingData(response.data,response.pager.page,response.pager.pageSize, response.pager.totalItems);
            	        	}
            	        ,function(reason){alert('Failed: ' + reason);}
            	     );
            	
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