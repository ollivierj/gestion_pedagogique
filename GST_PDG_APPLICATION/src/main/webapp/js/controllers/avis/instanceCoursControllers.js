'use strict';

controllers.controller('instanceCoursCtrl', function($scope, $modal, $log,
		$timeout, $http, $rootScope, $state, toaster, AvisFactory, StagiaireAvisFactory,
		InstanceCoursFactory, UtilisateursFactory) {
	$scope.pagingOptions = AvisFactory.pagingOptions;
	$scope.sortOptions = AvisFactory.sortOptions;
	$scope.filterOptions = AvisFactory.filterOptions;
	$scope.title = "Avis";
	$scope.canEdit = AvisFactory.canEdit;
	$scope.canView = AvisFactory.canView;
	$scope.viewRow = function(entity) {
		StagiaireAvisFactory.instanceCours = entity;
		AvisFactory.instanceCours = entity;
		$state.go('avis');
		StagiaireAvisFactory.readonly = true;
		AvisFactory.readonly = true;
	};

	$scope.editRow = function(entity) {
		StagiaireAvisFactory.instanceCours = entity;
		AvisFactory.instanceCours = entity;
		$state.go('avis');
		StagiaireAvisFactory.readonly = false;
		AvisFactory.readonly = false;
	};
	$scope.gridOptions = {
		data : 'instanceCours',
		multiSelect : false,
		columnDefs : [ {
			field : 'cours.libelleCours',
			displayName : 'Libellé'
		}, {
			field : 'cours.debut',
			displayName : 'Date de début',
			cellFilter : 'date:\'dd/MM/yyyy\'',
		}, {
			field : 'cours.fin',
			cellFilter : 'date:\'dd/MM/yyyy\'',
			displayName : 'Date de fin'
		}, {
			field : 'cours.promotion.libelle',
			displayName : 'Promotion'
		},

		{
			displayName : 'Actions',
			cellTemplate : 'partials/templates/ng-grid_actions.html'
		} ],
		enablePaging : true,
		showFooter : true,
		keepLastSelected : true,
		enableColumnResize : true,
		enableColumnReordering : true,
		useExternalSorting : true,
		showColumnMenu : true,
		i18n : 'fr',
		totalServerItems : 'totalServerItems',
		filterOptions : $scope.filterOptions,
		sortInfo : $scope.sortOptions,
		pagingOptions : $scope.pagingOptions
	};

	$scope.$watch('pagingOptions', function(newVal, oldVal) {
		if (newVal !== oldVal && newVal.currentPage !== oldVal.currentPage) {
			InstanceCoursFactory.refreshData($scope);
		}
	}, true);

	$scope.$watch('filterOptions', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			if ($scope.timer) {
				$timeout.cancel($scope.timer);
			}
			$scope.timer = $timeout(function() {
				InstanceCoursFactory.refreshData($scope);
			}, 500);
		}
	}, true);

	$scope.$watch('sortOptions', function(newVal, oldVal) {
		if (newVal !== oldVal) {
			InstanceCoursFactory.refreshData($scope);
		}
	}, true);

	InstanceCoursFactory.refreshData($scope);
});
