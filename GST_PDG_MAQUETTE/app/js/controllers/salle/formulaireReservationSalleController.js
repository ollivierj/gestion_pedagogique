var formulaireReservationSalleCtrl = function($scope, $modalInstance, $filter,items) {

	$scope.sallesLibres = [ {
		value : 1,
		text : 'Salle 101'
	}, {
		value : 2,
		text : 'Salle 103'
	}, {
		value : 10,
		text : 'Salle 104'
	}, {
		value : 3,
		text : 'Salle 108'
	}, {
		value : 4,
		text : 'Salle 201'
	}, {
		value : 5,
		text : 'Salle 204'
	}, {
		value : 6,
		text : 'Salle 205'
	}, {
		value : 7,
		text : 'Salle 206'
	}, {
		value : 8,
		text : 'Salle 207'
	}, {
		value : 9,
		text : 'Salle 208'
	}, ];

	$scope.animateursLibres = [ {
		value : 1,
		text : 'Jean Brouette'
	}, {
		value : 2,
		text : 'Henri Vincent'
	}, {
		value : 3,
		text : 'Elliot Poirot'
	}, {
		value : 4,
		text : 'Kevin Minja'
	}, {
		value : 5,
		text : 'Fabien Gerard'
	} ];

	$scope.coursPlanifies = [ {
		value : 1,
		text : 'Cours Reseaux'
	}, {
		value : 2,
		text : 'Cours Algo'
	}, {
		value : 3,
		text : 'Cours Projet'
	}, {
		value : 4,
		text : 'Cours SOA'
	}, {
		value : 5,
		text : 'Cours JAVA'
	}, {
		value : 6,
		text : 'Cours PHP'
	}, {
		value : 7,
		text : 'Cours SQL'
	}, {
		value : 8,
		text : 'Cours Math'
	} ];

	$scope.reservation = items;
	$scope.selected = {
		item : $scope.reservation
	};

	$scope.reserver = function() {
		var filtre = $filter('filter'); 
		
		var sallereservee = filtre( $scope.sallesLibres, { value: $scope.selected.item.salleid } )[0];
		var animateurplanifiee = filtre( $scope.animateursLibres, { value: $scope.selected.item.animateurid } )[0];
		var coursplanifie = filtre( $scope.coursPlanifies, { value: $scope.selected.item.coursid } )[0];


		$scope.selected.item.title = sallereservee.text + ' - '
				+ coursplanifie.text + ' animé par ' + animateurplanifiee.text;
		$modalInstance.close($scope.selected.item);
	};

	$scope.annuler = function() {
		$modalInstance.dismiss('cancel');
	};
};
