'use strict';

controllers.controller('wrapperCtrl', function($scope, $modal, modalService, $log,
		wrapMenu) {

	$scope.items = [ 'item1', 'item2', 'item3' ];

	$scope.titleSelected = "";

	wrapMenu.getData().then(function(data) {
		$scope.menuTitles = data;
	});

	$scope.setActive = function(title) {
		$scope.titleSelected = title;
	};
	
});