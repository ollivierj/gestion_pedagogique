var formSessionSalleCtrl = function($scope, $modalInstance, $filter,
		items, salles, sallesReservees, promotions, getByIdFilter, data) {

	
	console.log('data session');
	console.log(data);
	
	$scope.info = "";
	$scope.referentielSalles = salles;
	$scope.promotions = promotions;
	$scope.salles = sallesReservees;
	$scope.animateursLibres = [];
	$scope.getClassName = function(id) {
		return getByIdFilter($scope.referentielSalles, id).nom;
	};
	$scope.promotions.sort(function(promotion1, promotion2) {
		return promotion1.sortOrder - promotion2.sortOrder;
	});
	$scope.togglePromotion = function(promotion) {
		promotion.collapsed = !promotion.collapsed;
	};
	$scope.toggleSalle = function(salle) {
		salle.collapsed = !salle.collapsed;
	};

	$scope.options = {
		accept : function(sourceNode, destNodes, destIndex) {
			var data = sourceNode.$modelValue;
			var destType = destNodes.$element.attr('data-type');
			return (data.type == destType || (data.type == 'promotion' && destType == 'stagiaire'));
		},
		dropped : function(event) {
			var sourceNode = event.source.nodeScope;
			var destNodes = event.dest.nodesScope;
			// update changes to server
			if (destNodes.isParent(sourceNode)
					&& destNodes.$element.attr('data-type') == 'stagiaire') {
				var promotion = destNodes.$nodeScope.$modelValue;
				// promotion.save();
			} else { // save all
				//$scope.savePromotions();
			}
			event.dest.nodesScope.$modelValue

		},
		beforeDrop : function(event) {
			if ('promotion' == event.source.nodeScope.$modelValue.type) {
				event.source.nodeScope.$$apply = false;
				var nodeData = event.source.nodeScope.$childNodesScope.$modelValue;
				$scope.insertArrayAt(event.dest.nodesScope.$modelValue,
						event.dest.index, nodeData);
				event.source.nodeScope.$modelValue.stagiaires = [];
			}
		}
	};

	$scope.insertAt = function(array, index) {
		var arrayToInsert = Array.prototype.splice.apply(arguments, [ 2 ]);
		return insertArrayAt(array, index, arrayToInsert);
	};

	$scope.insertArrayAt = function(array, index, arrayToInsert) {
		Array.prototype.splice.apply(array, [ index, 0 ].concat(arrayToInsert));
		return array;
	};

	$scope.promotions.sort(function(promotion1, promotion2) {
		return promotion1.sortOrder - promotion2.sortOrder;
	});

	$scope.addSalle = function() {
$scope.salles.push( {
    "sortOrder": salles.length,
    "type": "salle",
    "id": salles.length,
    "editing": true,
    "collapsed" : true,
    "stagiaires": [
          ]
  });
	};

	$scope.editSalle = function(salle) {
		salle.editing = true;
	};
	


	$scope.cancelEditingSalle = function(salle) {
		salle.editing = false;
	};

	$scope.saveSalle = function(salle) {
		salle.save();
	};

	$scope.removeSalle = function(salle) {
		if (window
				.confirm('Etes-sûr de vouloir annuler la réservation de cette salle ?')) {
			//salle.destroy();
		}
	};

	$scope.saveSalles = function() {
		for (var i = $scope.salles.length - 1; i >= 0; i--) {
			var salle = $scope.salles[i];
			salle.sortOrder = i + 1;
			//salle.save();
		}
	};

	$scope.addStagiaireToSalle = function(salle) {
		if (!salle.newStagiaireName || salle.newStagiaireName.length === 0) {
			return;
		}
		salle.stagiaires.push({
			name : salle.newStagiaireName,
			sortOrder : salle.stagiaires.length,
			type : "stagiaire"
		});
		salle.newStagiaireName = '';
		salle.save();
	};

	$scope.removeStagiaireFromSalle = function(salle, stagiaire) {
		var index = salle.stagiaires.indexOf(stagiaire);
		if (index > -1) {
			salle.stagiaires.splice(index, 1)[0];
		}
		var selectedPromotion = $filter('filter')($scope.promotions, {
			name : stagiaire.promotion
		})[0];
		selectedPromotion.stagiaires.push(stagiaire);

	};

	$scope.reservation = items;
	$scope.selected = {
		//item : $scope.reservation
	};

	$scope.reserver = function() {
		
	};

	$scope.annuler = function() {
		$modalInstance.dismiss('cancel');
	};
};
