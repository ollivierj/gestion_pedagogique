'use strict';

controllers.controller('gestionAbsencesCtrl', function($scope, $modal, $log) {

	$scope.items = ['item1', 'item2', 'item3'];

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

var ModalSaisieAbsenceCtrl = function ($scope, $modalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  // Disable weekend selection
  $scope.disabled = function(date, mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };

  $scope.open = function($event) {
    $event.preventDefault();
    $event.stopPropagation();

    $scope.opened = true;
  };

  $scope.ok = function () {
    $modalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };
};