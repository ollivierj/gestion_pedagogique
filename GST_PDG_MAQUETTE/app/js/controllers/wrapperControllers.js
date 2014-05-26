'use strict';

controllers.controller('wrapperCtrl', function($scope, $modal, $log, wrapMenu) {

	$scope.items = ['item1', 'item2', 'item3'];

  $scope.titleSelected = "";

  wrapMenu.getData().then(function(data) {
      $scope.menuTitles = data;
  });

  $scope.setActive = function(title) {
    $scope.titleSelected = title;
  };

	$scope.afficherModalAbsence = function() {
		var modalEdit = $modal.open({
            templateUrl: 'partials/gestionAbsences/gestionAbsences.html',
            controller: ModalSaisieAbsenceCtrl,
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