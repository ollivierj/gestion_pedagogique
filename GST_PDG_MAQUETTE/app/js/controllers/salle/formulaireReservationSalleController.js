var formulaireReservationSalleCtrl = function($scope, $modalInstance, $filter,items) {


$scope.info = "";
    
    
      $scope.promotions = [
  {
    "name": "AL3B",
    "sortOrder": 0,
    "type": "promotion",
    "id": "-JN-NMji-X2NjEtAVyaT",
    "editing": false,
    "stagiaires": [
      {
        "name": "Jean-Louis Aubert",
        "sortOrder": 2,
        "type": "stagiaire"
      },
      {
        "name": "Eric Smith",
        "sortOrder": 1,
        "type": "stagiaire"
      },
      {
        "name": "Paul Wilcow",
        "sortOrder": 1,
        "type": "stagiaire"
      },
      {
        "name": "James Gold ",
        "sortOrder": 1,
        "type": "stagiaire"
      },
      {
        "name": "Kevin Majin",
        "sortOrder": 6,
        "type": "stagiaire"
      },
      {
        "name": "Eric Smidt",
        "sortOrder": 5,
        "type": "stagiaire"
      },
      {
        "name": "Michel Bouygues",
        "sortOrder": 6,
        "type": "stagiaire"
      }
    ]   
  },
  {
    "name": "Contrat Pro",
    "sortOrder": 2,
    "type": "promotion",
    "id":  "-JMzzbtxYOdYp3FW2Q_8",
    "editing": false,
    "stagiaires": [
      {
        "name": "Julien Gustave",
        "sortOrder": 0,
        "type": "stagiaire"
      },
      {
        "name": "Michel Poule",
        "sortOrder": 5,
        "type": "stagiaire"
      }
    ]
  }
];


    $scope.salles = [
  {
    "name": "104",
    "sortOrder": 0,
    "type": "salle",
    "id": 1,
    "editing": false,
    "stagiaires": [
      {
        "name": "Jean-Louis Aubert",
        "sortOrder": 2,
        "type": "stagiaire"
      },
      {
        "name": "Eric Smith",
        "sortOrder": 1,
        "type": "stagiaire"
      },
      {
        "name": "Paul Wilcow",
        "sortOrder": 1,
        "type": "stagiaire"
      },
      {
        "name": "James Gold ",
        "sortOrder": 1,
        "type": "stagiaire"
      },
      {
        "name": "Kevin Majin",
        "sortOrder": 6,
        "type": "stagiaire"
      },
      {
        "name": "Eric Smidt",
        "sortOrder": 5,
        "type": "stagiaire"
      },
      {
        "name": "Michel Bouygues",
        "sortOrder": 6,
        "type": "stagiaire"
      }
    ]   
  },
  {
    "name": "108",
    "sortOrder": 2,
    "type": "salle",
    "id": 2,
    "editing": false,
    "stagiaires": [
      {
        "name": "Julien Gustave",
        "sortOrder": 0,
        "type": "stagiaire"
      },
      {
        "name": "Michel Poule",
        "sortOrder": 5,
        "type": "stagiaire"
      }
    ]
  }
];


      $scope.promotions.sort(function(promotion1, promotion2) {
        return promotion1.sortOrder - promotion2.sortOrder;
      });
     
    

    $scope.addPromotion = function() {
      if ($scope.promotions.length > 10) {
        window.alert('You can\'t add more than 10 promotions!');
        return;
      }
      var promotionName = document.getElementById("promotionName").value;
      if (promotionName.length > 0) {
        Promotions.$add({
          name: promotionName,
          type: "promotion",
          stagiaires: [],
          sortOrder: $scope.promotions.length
        });
        document.getElementById("promotionName").value = '';
      }
    };

    $scope.editPromotion = function(promotion) {
      promotion.editing = true;
    };

    $scope.cancelEditingPromotion = function(promotion) {
      promotion.editing = false;
    };

    $scope.savePromotion = function(promotion) {
      promotion.save();
    };

    $scope.removePromotion = function(promotion) {
      if (window.confirm('Are you sure to remove this promotion?')) {
        promotion.destroy();
      }
    };

    $scope.savePromotions = function() {
      for (var i = $scope.promotions.length - 1; i >= 0; i--) {
        var promotion = $scope.promotions[i];
        promotion.sortOrder = i + 1;
        promotion.save();
      }
    };

    $scope.addStagiaireToPromotion = function(promotion) {
      if (!promotion.newStagiaireName || promotion.newStagiaireName.length === 0) {
        return;
      }
      promotion.stagiaires.push({
        name: promotion.newStagiaireName,
        sortOrder: promotion.stagiaires.length,
        type: "stagiaire"
      });
      promotion.newStagiaireName = '';
      promotion.save();
    };

    $scope.removeStagiaireFromPromotion = function(promotion, stagiaire) {
      if (window.confirm('Are you sure to remove this stagiaire?')) {
        var index = promotion.stagiaires.indexOf(stagiaire);
        if (index > -1) {
          promotion.stagiaires.splice(index, 1)[0];
        }
        promotion.save();
      }
    };

    $scope.options = {
      accept: function(sourceNode, destNodes, destIndex) {
        var data = sourceNode.$modelValue;
        var destType = destNodes.$element.attr('data-type');
        return (data.type == destType); // only accept the same type
      },
      dropped: function(event) {
        console.log(event);
        var sourceNode = event.source.nodeScope;
        var destNodes = event.dest.nodesScope;
        // update changes to server
        if (destNodes.isParent(sourceNode)
          && destNodes.$element.attr('data-type') == 'stagiaire') { // If it moves in the same promotion, then only update promotion
          var promotion = destNodes.$nodeScope.$modelValue;
          promotion.save();
        } else { // save all
          $scope.savePromotions();
        }
      },
      beforeDrop: function(event) {
        if (!window.confirm('Are you sure you want to drop it here?')) {
          event.source.nodeScope.$$apply = false;
        }
      }
    };








$scope.promotions.sort(function(promotion1, promotion2) {
        return promotion1.sortOrder - promotion2.sortOrder;
      });
     
    

    $scope.addSalle = function() {
      if ($scope.promotions.length > 10) {
        window.alert('You can\'t add more than 10 promotions!');
        return;
      }
      var promotionName = document.getElementById("salleName").value;
      if (promotionName.length > 0) {
        Salles.$add({
          name: promotionName,
          type: "salle",
          stagiaires: [],
          sortOrder: $scope.salles.length
        });
        document.getElementById("salleName").value = '';
      }
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
      if (window.confirm('Are you sure to remove this promotion?')) {
        salle.destroy();
      }
    };

    $scope.saveSalles = function() {
      for (var i = $scope.salles.length - 1; i >= 0; i--) {
        var salle = $scope.salles[i];
        salle.sortOrder = i + 1;
        salle.save();
      }
    };

    $scope.addStagiaireToSalle = function(salle) {
      if (!salle.newStagiaireName || salle.newStagiaireName.length === 0) {
        return;
      }
      salle.stagiaires.push({
        name: salle.newStagiaireName,
        sortOrder: salle.stagiaires.length,
        type: "stagiaire"
      });
      salle.newStagiaireName = '';
      salle.save();
    };

    $scope.removeStagiaireFromSalle = function(salle, stagiaire) {
      if (window.confirm('Are you sure to remove this stagiaire?')) {
        var index = salle.stagiaires.indexOf(stagiaire);
        if (index > -1) {
          salle.stagiaires.splice(index, 1)[0];
        }
        salle.save();
      }
    };









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
