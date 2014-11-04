var formSessionSalleCtrl = function($scope, $modalInstance, $filter, $rootScope, $http,
		items, salles, sallesReservees, getByIdFilter, data, ProfessionnelHomologuesFactory, SessionValidationsFactory) {
	
	
	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
		$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
	}
	// Contient toutes les instances réservées.
	$scope.instances = [];
	// Mot clé pour la création d'une nouvelle absence, utilisé pour un ID temporaire
	var INSTANCE_TEMP = 'TEMP';

	//Créé une map avec le libelle de la formation
	$scope.promotions = _.transform(data.sessionValidationStagiaires, function(result, num) {
		//Si le stagiaire n'est pas associé à une instance
		if (_.isNull(num.instanceSessionValidation)) {
			//Classement par promotion
			//Si la promotion est déjà présente dans la liste
			if (_.find(result, {'name' : num.stagiaire.promotion.libelle})) {
				num.type = 'stagiaire';
				_.find(result, {'name' : num.stagiaire.promotion.libelle}).stagiaires.push(num);
			//Si la promotion n'est pas présente dans la liste
			} else {
				num.type = 'stagiaire';
				result.push({name : num.stagiaire.promotion.libelle, type: 'promotion', stagiaires : [num]});
			}
		//Si le stagiaire est associé à une instance
		} else {
			//On stocke chacune des instances
			if (!_.find($scope.instances, {'id' : num.instanceSessionValidation.id})) {
				$scope.instances.push(num.instanceSessionValidation);
				_.find($scope.instances, {'id' : num.instanceSessionValidation.id}).editing = false;
				_.find($scope.instances, {'id' : num.instanceSessionValidation.id}).collapsed = false;
				_.find($scope.instances, {'id' : num.instanceSessionValidation.id}).stagiaires = [num];
			} else { 
				_.find($scope.instances, {'id' : num.instanceSessionValidation.id}).stagiaires.push(num);
			}
		}	
	});
	
	$scope.info = "";
	
	//Chargement du référentiel de salles disponibles
	//TODO limitez sur les salles dispo ce jour
	$scope.referentielSalles = salles;
	
	$scope.salles = sallesReservees;
	//$scope.professionnelHomologues = [];
	
	// Autocomplétion uetilisé pour l'affectation des professionnel homolgue
	$scope.chargerProfessionnels = function(search) {
		return ProfessionnelHomologuesFactory.autocomplete.getData({search: search}).$promise.then(
			function(data) {
				var professionnelHomologues = [];
				angular.forEach(data, function(item) {
					professionnelHomologues.push(item);
				});
				return professionnelHomologues;
			});
	};
    
	// Ajoute un professionnel homologué l'édition d'une salle
	$scope.addProfessionnel = function (instance, newProfessionnelH) {
		instance.jures.unshift(newProfessionnelH);
		newProfessionnelH = null;
	}
	
	//Récupère le libellé d'une salle
	$scope.getSalleLibelle = function (id) {
		if (id != -1) {
			var salle = _.find($scope.referentielSalles, {id: id});
			return salle != null 
						? salle.libelle
						: 'Inconnu';
		}
	};

	// Récupère le nombre de places restantes d'une salle
	$scope.getSallePlace = function (instance) {
		if (instance.reservationSalle) {
			var salle = _.find($scope.referentielSalles, {id: instance.reservationSalle.salle.id});
			return salle != null 
						? salle.nbPlaces - instance.stagiaires.length
						: 'Inconnu';
		}
	};
	
	$scope.getClassName = function(id) {
		return getByIdFilter($scope.referentielSalles, id).nom;
	};
	
	/*$scope.promotions.sort(function(promotion1, promotion2) {
		return promotion1.sortOrder - promotion2.sortOrder;
	});*/
	
	$scope.togglePromotion = function(promotion) {
		promotion.collapsed = !promotion.collapsed;
	};
	
	$scope.toggleInstance = function(instance) {
		instance.collapsed = !instance.collapsed;
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

	$scope.addInstance = function() {
		$scope.instances.unshift( {
		    sortOrder: $scope.instances.length,
		    type: "salle",
		    id: INSTANCE_TEMP,
		    editing: true,
		    collapsed : true,
		    jures : [],
		    stagiaires: []
		  });
	};

	$scope.editInstance = function(instance) {
		instance.editing = true;
	};
	

	$scope.cancelEditingInstance = function(instance) {
		if (instance.id == INSTANCE_TEMP) 
			_.remove($scope.instances, {id: INSTANCE_TEMP});
		instance.editing = false;
	};

	$scope.saveSalle = function(salle) {
		salle.save();
	};

	// Suppression d'une instance
	$scope.removeInstance = function(instance, iIndex) {
		/*if (window.confirm('Etes-sûr de vouloir annuler la réservation de cette salle ?')) {
			salle.destroy();
		}*/
		
		angular.forEach(instance.stagiaires, function(stagiaire, key) {
			var selectedPromotion = $filter('filter')($scope.promotions, {
				name : stagiaire.stagiaire.promotion.libelle
			})[0];
			
			selectedPromotion.stagiaires.push(stagiaire);
		});
		
		_.remove($scope.instances, {$$hashKey: instance.$$hashKey});
	};

	$scope.saveInstance = function(instance) {
		instance.editing = false;
		console.log(instance);
		/*for (var i = $scope.salles.length - 1; i >= 0; i--) {
			var salle = $scope.salles[i];
			salle.sortOrder = i + 1;
			//salle.save();
		}*/
	};

	$scope.addStagiaireToInstance = function(salle) {
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

	
	// Suppression d'un stagiaire associé à une instance
	// Le stagiaire retourne dans sa promotion */
	$scope.removeStagiaireFromInstance = function(instance, stagiaire) {
		var index = instance.stagiaires.indexOf(stagiaire);
		if (index > -1) {
			instance.stagiaires.splice(index, 1)[0];
		}
		var selectedPromotion = $filter('filter')($scope.promotions, {
			name : stagiaire.stagiaire.promotion.libelle
		})[0];
		selectedPromotion.stagiaires.push(stagiaire);

	};
	
	
	// Supression d'un professionnel homologue d'un instance
	$scope.removeProfessionnel = function (pIndex, instance) {
		instance.jures.splice(pIndex, 1);
	};

	// Réservation et enregistrement de toutes les données de l'écran
	$scope.reserver = function() {
		
		stagiairesToSaved = _.transform($scope.promotions, function(result, num) {
			result.push.apply(result, num.stagiaires);			
		});
		
		var instancesToSaved = _.transform($scope.instances, function(result, num) {
			var stagiaires = num.stagiaires;
			num.stagiaires = null;
			var sessionValidation = num.sessionValidation;
			num.sessionValidation = null;
			num.sessionValidation = {id: sessionValidation.id}; 
			//result.push(num, stagiaires);
			result[num] = stagiaires;
		});
		
		console.log('data to saved');
		console.log(stagiairesToSaved);
		console.log(instancesToSaved);
		
		SessionValidationsFactory.instance.saveData(
				instancesToSaved, 
				stagiairesToSaved);
	};

	$scope.annuler = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.editStagiaireFromInstance = function () {
		
	};

	$scope.reservation = items;
	$scope.selected = {
		//item : $scope.reservation
	};

};

