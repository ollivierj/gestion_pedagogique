'use strict';
var absencesCtrl = function($scope, $modalInstance, $log, StagiairesFactory, retardataires) {
	 $scope.retardataires = retardataires;
	 $scope.mytime = new Date();
	 $scope.hstep = 1;
	  $scope.mstep = 15;

	  $scope.options = {
	    hstep: [1, 2, 3],
	    mstep: [1, 5, 10, 15, 25, 30]
	  };
	  $scope.ismeridian = true;
	  $scope.toggleMode = function() {
	    $scope.ismeridian = ! $scope.ismeridian;
	  };
	  $scope.editRetardataire = function(retardataire) {
		  retardataire.editing = true;
		};
	  // Disable weekend selection
	  $scope.disabled = function(date, mode) {
	    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
	  };
	  
	  $scope.radioModel = 'matin';

	  $scope.getStagiaires = function(search) {
		  
		  return StagiairesFactory.dataAutocomplete.getData({search : search}).$promise.then(function(data){
		      var stagiaires = [];
		      angular.forEach(data, function(item){
		        stagiaires.push(item);
		      });
		      return stagiaires;
		    });
		  
	  };

	  
	  
	  $scope.checkModel = {
	    matin: true,
	    soir: false
	  };
	  
		$scope.cancelEditingRetardataire = function(retardataire) {
			retardataire.editing = false;
		};
		
		$scope.addRetardataire = function() {
			//retardataire.editing = false;
		};

		$scope.saveRetardataire = function(retardataire) {
			retardataire.editing = false;
		};

	  $scope.open = function($event) {
	    $event.preventDefault();
	    $event.stopPropagation();

	    $scope.opened = true;
	  };

	  $scope.ok = function () {
	    $modalInstance.close(null);
	  };

	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
};

