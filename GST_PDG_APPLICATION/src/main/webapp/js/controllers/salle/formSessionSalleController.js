var formSessionSalleCtrl = function($scope, $modalInstance, $filter, $rootScope, $http,
		eventInfo, salles, getByIdFilter, data, ProfessionnelHomologuesFactory, 
		SessionValidationsFactory, instanceRef) {
		
	
	//$scope.phSelecteds = [];
	
	//Instances à supprimer
	var instancesToDelete = [];
	
	/***** CONSTANT *****/
	// Mot clé pour la création d'une nouvelle absence, utilisé pour un ID temporaire
	var INSTANCE_TEMP = 0;
	var TYPE_INSTANCE = 'instance';
	var TYPE_PROMOTION = 'promotion';
	var TYPE_STAGIAIRE = 'stagiaire';
	
	
	//Formattage du titre
	$scope.dateDebut = $filter('date')($scope.dateD,'dd/MM/yyyy');
	$scope.dateFin = $filter('date')($scope.dateF,'dd/MM/yyyy');
	$scope.title = 'Sessions de validation du ' + $scope.dateDebut + ' au ' + $scope.dateFin;
	
	//Chargement du référentiel de salles disponibles
	//TODO limitez sur les salles dispo ce jour
	$scope.referentielSalles = salles;
	
	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken) {
		$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
	}
	
	// On stocke toutes les instances lié à cette session de validation
	$scope.instances = _.transform(instanceRef, function(result, num) {
		//On ajoute l'attribut d'edition et collapse utilisé par l'IHM à chaque instance.
		_.assign(num, {editing: false, collapsed: false, stagiaires: [], type: TYPE_INSTANCE});
		result.push(num);
	});
	
	//Créé une map avec le libelle de la formation
	$scope.promotions = _.transform(data.sessionValidationStagiaires, function(result, num) {
		
		// Si la promotion n'est pas présente dans la liste, on l'ajoute
		if (!_.find(result, {name: num.stagiaire.promotion.libelle})) {
			result.push({name : num.stagiaire.promotion.libelle, type: TYPE_PROMOTION, stagiaires : []});
		};
		
		//Si le stagiaire n'est pas associé à une instance
		if (_.isNull(num.instanceSessionValidation)) {
			
			//Assignation de l'id de la session et du type (utilisé pour l'IHM)
			_.assign(num, {type: TYPE_STAGIAIRE, sessionValidation: {id: data.id}});
			_.find(result, {name : num.stagiaire.promotion.libelle}).stagiaires.push(num);
			
		//Si le stagiaire est associé à une instance
		} else {
			//On stocke chacune des instances
			_.find($scope.instances, {id : num.instanceSessionValidation.id}).stagiaires.push({id: num.id, 
																								dateHeureDebut: num.dateHeureDebut, 
																								dateHeureFin: num.dateHeureDebut, 
																								instanceSessionValidation: {id: num.instanceSessionValidation.id},
																								sessionValidation: {id: data.id},
																								stagiaire: num.stagiaire,
																								type: TYPE_STAGIAIRE});
		}	
	});
	
	// Autocomplétion utilisé pour l'affectation des professionnel homolgue
	$scope.chargerProfessionnels = function(search) {
		return ProfessionnelHomologuesFactory.autocomplete.getData({search: search}).$promise.then(
			function(data) {
				var professionnelHomologues = [];
				angular.forEach(data, function(item) {
					//if (($filter('filter')($scope.phSelecteds, {id: item.id})).length == 0) {
						professionnelHomologues.push(item);
					//}
				});
				
				return professionnelHomologues;
			});
	};
    
	/*************************** PROFESSIONNEL HOMOLOGUE **********************************************/
	// Ajoute un professionnel homologué l'édition d'une salle
	$scope.addProfessionnel = function (instance, newProfessionnelH) {
		instance.jures.unshift(newProfessionnelH);
		//$scope.phSelecteds.push(newProfessionnelH);
	}
	
	// Supression d'un professionnel homologue d'un instance
	$scope.removeProfessionnel = function (pIndex, instance, professionnelH) {
		_.remove($scope.phSelecteds, {id : professionnelH.id});
		//_.remove(instance.jures, {id : professionnelH.id});
	};
	
	/*************************** SALLE **********************************************/
	//Récupère le libellé d'une salle
	$scope.getSalleLibelle = function (instance) {
		if (instance.reservationSalle.salle) {
			var salle = _.find($scope.referentielSalles, {id: instance.reservationSalle.salle.id});
			return salle != null 
						? salle.libelle
						: 'Inconnu';
		}
	};

	// Récupère le nombre de places restantes d'une salle
	$scope.getSallePlace = function (instance) {
		var nbPlaces = 0;
		if (instance.reservationSalle) {
			nbPlaces = instance.reservationSalle.nbPosteLibre;
		}
		return nbPlaces;
	};
	
	$scope.saveSalle = function(salle) {
		salle.save();
	};
	
	$scope.togglePromotion = function(promotion) {
		promotion.collapsed = !promotion.collapsed;
	};
	
	//Calcul du nombre de place restante d'une salle
	var calculNbPlaceRestante = function (instance) {
		if (instance) {
			var salle = $filter('filter')($scope.referentielSalles, {id: instance.reservationSalle.salle.id})[0];
			instance.reservationSalle.nbPosteLibre = 
				salle.nbPlaces - instance.stagiaires.length;
		}
	}

	/*************************** UI TREE OPTIONS **********************************************/
	$scope.options = {
		//Appelé lors du déplament d'un noeud vers un autre
		accept : function(sourceNode, destNodes, destIndex) {
			//Récupère les données du model du noeud source
			var dataSource = sourceNode.$modelValue;
			//Récupère l'attribut type du noeud de destination
			var destType = destNodes.$element.attr('data-type');
			return ((dataSource.type == destType) 
					|| (dataSource.type == TYPE_PROMOTION && destType == TYPE_STAGIAIRE));
		},
		//Appelé lorsque le déplacement est effectué
		dropped : function(event) {
			/*
			//Noeud qui est déplacé
			var sourceNode = event.source.nodeScope;
			//Noeud de destination
			var destNode = event.dest.nodesScope;
			
			// update changes to server
			if (destNode.isParent(sourceNode)
					&& destNode.$element.attr('data-type') == 'stagiaire') {
				var promotion = destNode.$nodeScope.$modelValue;
				// promotion.save();				
			} else { // save all
				//$scope.savePromotions();
			}
			event.dest.nodesScope.$modelValue;*/

		},
		//Appelé avant le déplacement
		beforeDrop : function(event) {	
			
			if (TYPE_PROMOTION == event.source.nodeScope.$modelValue.type) {
				event.source.nodeScope.$$apply = false;
				var nodeData = event.source.nodeScope.$childNodesScope.$modelValue;
				$scope.insertArrayAt(event.dest.nodesScope.$modelValue,
						event.dest.index, nodeData);
				event.source.nodeScope.$modelValue.stagiaires = [];
			}
			
		},
		
		dragStop : function (event) {
			var destNode = event.dest.nodesScope;
			var instance = null;
			if (destNode.$parent.$modelValue && destNode.$parent.$modelValue.type == TYPE_INSTANCE) {
				angular.forEach(destNode.$modelValue, function(stagiaire) {
					instance = destNode.$parent.$modelValue;
					calculNbPlaceRestante(instance);
					stagiaire.instanceSessionValidation = {id:destNode.$parent.$modelValue.id};
				});
			}
			if (destNode.$parent.$modelValue && destNode.$parent.$modelValue.type == TYPE_PROMOTION) {
				angular.forEach(destNode.$modelValue, function(stagiaire) {
					instance = $filter('filter')($scope.instances, {id : stagiaire.instanceCours})[0];
					calculNbPlaceRestante(instance);
					stagiaire.instanceSessionValidation = null;
				});
			}
		}
	};
	
	/*************************** INSTANCE **********************************************/
	
	$scope.toggleInstance = function(instance) {
		instance.collapsed = !instance.collapsed;
	};
	
	$scope.addInstance = function() {
		$scope.instances.unshift( {
		    sortOrder: $scope.instances.length,
		    type: TYPE_INSTANCE,
		    id: INSTANCE_TEMP,
		    editing: true,
		    collapsed : true,
		    jures : [],
		    stagiaires: [],
		    reservationSalle : {
		    	formatedDateDebut: data.formatedDateDebut,
		    	formatedDateFin: data.formatedDateFin,
		    	id:0,
		    	salle: null,
		    	nbPosteLibre: 0
		    }
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

	// Suppression d'une instance
	$scope.removeInstance = function(instance, iIndex) {
		/*if (window.confirm('Etes-sûr de vouloir annuler la réservation de cette salle ?')) {
			salle.destroy();
		}*/
		
		angular.forEach(instance.stagiaires, function(stagiaire, key) {
			//On supprime le stagiaire de l'instance
			stagiaire.instanceSessionValidation = null;
			
			var selectedPromotion = $filter('filter')($scope.promotions, {
				name : stagiaire.stagiaire.promotion.libelle
			})[0];
			
			selectedPromotion.stagiaires.push(stagiaire);
		});
		
		//On sauvegarde l'instance supprimée si elle est déjà persistée en base
		if (instance.id != INSTANCE_TEMP)
			instancesToDelete.push(instance);
		
		_.remove($scope.instances, {$$hashKey: instance.$$hashKey});
	};

	$scope.saveInstance = function(instance) {
		instance.editing = false;
		/*for (var i = $scope.salles.length - 1; i >= 0; i--) {
			var salle = $scope.salles[i];
			salle.sortOrder = i + 1;
			//salle.save();
		}*/
		calculNbPlaceRestante(instance);
	};

	/*
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
*/
	
	/*************************** STAGIAIRES **********************************************/
	// Suppression d'un stagiaire associé à une instance
	// Le stagiaire retourne dans sa promotion */
	$scope.removeStagiaireFromInstance = function(instance, stagiaire) {
		//On supprime le stagiaire de l'instance
		stagiaire.instanceSessionValidation = null;
		var index = instance.stagiaires.indexOf(stagiaire);
		if (index > -1) {
			instance.stagiaires.splice(index, 1)[0];
		}
		//On récupère sa promotion
		var selectedPromotion = $filter('filter')($scope.promotions, {
			name : stagiaire.stagiaire.promotion.libelle
		})[0];
		//On le réaffecte à sa promotion
		selectedPromotion.stagiaires.push(stagiaire);

		calculNbPlaceRestante(instance);
	};
	
	$scope.updateStagiaire = function (stagiaire) {
		stagiaire.formatedDateHeureDebut = $filter('date')(stagiaire.dateHeureDebut, "'yyyy-MM-dd HH:mm:ss'");
		stagiaire.formatedDateHeureFin = $filter('date')(stagiaire.dateHeureFin, "'yyyy-MM-dd HH:mm:ss'");
		stagiaire.editing = false;
	};
	

	/*************************** RESERVATION **********************************************/
	//Si un des éléments de l'IHM est en mode édition
	$scope.oneIsEditing = function () {
		var i, length = $scope.instances.length, isEditing = false;
		for (i = 0 ; i < length ; i ++) {
			if ($scope.instances[i].editing == true) {
				isEditing = true; break;
			}
		}
		return isEditing;
	}
	
	// Réservation et enregistrement de toutes les données de l'écran
	$scope.reserver = function() {
		
		var instancesToSaved = _.transform($scope.instances, function(result, num) {				
			var instance = {id : num.id, jures: num.jures, reservationSalle: num.reservationSalle, sessionValidation : {id:data.id}};
			result.push({first : instance, second: num.stagiaires});
		});
		
		stagiairesToSaved = _.transform($scope.promotions, function(result, num) {
			result.push.apply(result, num.stagiaires);			
		});
		
		var dataInstances = {instances : instancesToSaved, instancesStagiaires : stagiairesToSaved, instancesToDelete : instancesToDelete};
	
		SessionValidationsFactory.instance.saveData(dataInstances).$promise.then(
				function (success) {
					$modalInstance.close('success');
				},
				function (error) {
					toaster.pop('error', null, error.data.message);
				}
		);
	};

	$scope.annuler = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.editStagiaireFromInstance = function () {
		
	};
	
	/*************************** FONCTIONS **********************************************/
	
	$scope.insertAt = function(array, index) {
		var arrayToInsert = Array.prototype.splice.apply(arguments, [ 2 ]);
		return insertArrayAt(array, index, arrayToInsert);
	};

	$scope.insertArrayAt = function(array, index, arrayToInsert) {
		Array.prototype.splice.apply(array, [ index, 0 ].concat(arrayToInsert));
		return array;
	};

};

