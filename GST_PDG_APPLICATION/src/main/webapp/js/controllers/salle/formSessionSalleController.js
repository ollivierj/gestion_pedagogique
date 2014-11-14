var formSessionSalleCtrl = function($scope, $modalInstance, $filter,
		$rootScope, $http, eventInfo, salles, getByIdFilter, data,
		ProfessionnelHomologuesFactory, SessionValidationsFactory, instanceRef) {

	// Instances à supprimer
	var instancesToDelete = [];

	/** *** CONSTANT **** */
	// Mot clé pour la création d'une nouvelle absence, utilisé pour un ID
	// temporaire
	var INSTANCE_TEMP = 0;
	var TYPE_INSTANCE = 'instance';
	var TYPE_PROMOTION = 'promotion';
	var TYPE_STAGIAIRE = 'stagiaire';

	$scope.instancesToDislay = [];

	// Formattage du titre
	$scope.debutLimit = new Date(data.formatedDateDebut.replace("T", " "));
	$scope.finLimit = new Date(data.formatedDateFin.replace("T", " "));
	$scope.dateInstance = angular.copy($scope.debutLimit);
	var dateDebut = $filter('date')($scope.debutLimit, 'dd/MM/yyyy');
	var dateFin = $filter('date')($scope.finLimit, 'dd/MM/yyyy');

	$scope.title = 'Sessions de validation du ' + dateDebut + ' au ' + dateFin
			+ ' : ' + eventInfo.libelle;

	// Chargement du référentiel de salles disponibles
	$scope.referentielSalles = salles;

	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken) {
		$http.defaults.headers.common.Authorization = 'Basic '
				+ $rootScope.authtoken;
	}

	// On stocke toutes les instances lié à cette session de validation
	$scope.instances = _.transform(instanceRef, function(result, num) {
		// On ajoute l'attribut d'edition et collapse utilisé par l'IHM à chaque
		// instance.
		_.assign(num, {
			editing : false,
			collapsed : false,
			stagiaires : [],
			type : TYPE_INSTANCE
		});
		result.push(num);
	});
	
	// Créé une map avec le libelle de la formation
	$scope.promotions = _.transform(data.sessionValidationStagiaires, function(
			result, num) {

		// Si la promotion n'est pas présente dans la liste, on l'ajoute
		if (!_.find(result, {
			name : num.stagiaire.promotion.libelle
		})) {
			result.push({
				name : num.stagiaire.promotion.libelle,
				type : TYPE_PROMOTION,
				stagiaires : []
			});
		}
		;

		// Si le stagiaire n'est pas associé à une instance
		if (_.isNull(num.instanceSessionValidation)) {

			// Assignation de l'id de la session et du type (utilisé pour l'IHM)
			_.assign(num, {
				type : TYPE_STAGIAIRE,
				sessionValidation : {
					id : data.id
				}
			});
			_.find(result, {
				name : num.stagiaire.promotion.libelle
			}).stagiaires.push(num);

			// Si le stagiaire est associé à une instance
		} else {
			// On stocke chacune des instances
			_.find($scope.instances, {
				id : num.instanceSessionValidation.id
			}).stagiaires.push({
				id : num.id,
				dateHeureDebut : num.dateHeureDebut,
				dateHeureFin : num.dateHeureDebut,
				instanceSessionValidation : {
					id : num.instanceSessionValidation.id
				},
				sessionValidation : {
					id : data.id
				},
				stagiaire : num.stagiaire,
				type : TYPE_STAGIAIRE
			});
		}
	});

	// Autocomplétion utilisé pour l'affectation des professionnel homolgue
	$scope.chargerProfessionnels = function(search) {
		return ProfessionnelHomologuesFactory.autocomplete.getData({
			search : search
		}).$promise.then(function(data) {
			var professionnelHomologues = [];
			angular.forEach(data, function(item) {
				// if (($filter('filter')($scope.phSelecteds, {id:
				// item.id})).length == 0) {
				professionnelHomologues.push(item);
				// }
			});

			return professionnelHomologues;
		});
	};

	/**
	 * ************************* CHOIX DU JOUR
	 * *********************************************
	 */

	// Observe la date de la session choisie
	$scope.$watch('dateInstance', function(newVal, oldVal) {
		if (newVal != oldVal)
			changeDateInstance();
	}, true);

	// Appelé pour modifié les instances à afficher suivant le choix de la date.
	var changeDateInstance = function() {
		var dateChoice = $filter('date')($scope.dateInstance,
				'yyyy-MM-ddTHH:mm:ss');
		$scope.instancesToDisplay = [];
		var i, length = $scope.instances.length;
		for (i = 0; i < length; i++) {
			if ($scope.instances[i].reservationSalle.formatedDateDebut == dateChoice)
				$scope.instancesToDisplay.push($scope.instances[i]);
		}
	}
	changeDateInstance();

	/**
	 * ************************* PROFESSIONNEL HOMOLOGUE
	 * *********************************************
	 */
	// Ajoute un professionnel homologué l'édition d'une salle
	$scope.addProfessionnel = function(instance, newProfessionnelH) {
		instance.jures.unshift(newProfessionnelH);
		// $scope.phSelecteds.push(newProfessionnelH);
	}

	// Supression d'un professionnel homologue d'un instance
	$scope.removeProfessionnel = function(pIndex, instance, professionnelH) {
		// _.remove($scope.phSelecteds, {id : professionnelH.id});
		_.remove(instance.jures, {
			id : professionnelH.id
		});
	};

	/**
	 * ************************* SALLE
	 * *********************************************
	 */
	// Récupère le libellé d'une salle
	$scope.getSalleLibelle = function(instance) {
		if (instance.reservationSalle.salle) {
			var salle = _.find($scope.referentielSalles, {
				id : instance.reservationSalle.salle.id
			});
			return salle != null ? salle.libelle : 'Inconnu';
		}
	};

	// Récupère le nombre de places restantes d'une salle
	$scope.getSallePlace = function(instance) {
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

	// Calcul du nombre de place restante d'une salle
	var calculNbPlaceRestante = function(instance) {
		if (instance) {
			var salle = $filter('filter')($scope.referentielSalles, {
				id : instance.reservationSalle.salle.id
			})[0];
			instance.reservationSalle.nbPosteLibre = salle.nbPlaces
					- instance.stagiaires.length;
		}
	}

	var calculAllNbPlaceRestante = function() {
		var i, length = $scope.instances.length;
		for (i = 0; i < length; i++) {
			calculNbPlaceRestante($scope.instances[i]);
		}
	}

	/**
	 * ************************* UI TREE OPTIONS
	 * *********************************************
	 */
	$scope.options = {
		// Appelé lors du déplament d'un noeud vers un autre
		accept : function(sourceNode, destNodes, destIndex) {
			// Récupère les données du model du noeud source
			var dataSource = sourceNode.$modelValue;
			// Récupère l'attribut type du noeud de destination
			var destType = destNodes.$element.attr('data-type');
			return ((dataSource.type == destType) || (dataSource.type == TYPE_PROMOTION && destType == TYPE_STAGIAIRE));
		},
		// Appelé lorsque le déplacement est effectué
		dropped : function(event) {
			/*
			 * //Noeud qui est déplacé var sourceNode = event.source.nodeScope;
			 * //Noeud de destination var destNode = event.dest.nodesScope; //
			 * update changes to server if (destNode.isParent(sourceNode) &&
			 * destNode.$element.attr('data-type') == 'stagiaire') { var
			 * promotion = destNode.$nodeScope.$modelValue; // promotion.save(); }
			 * else { // save all //$scope.savePromotions(); }
			 * event.dest.nodesScope.$modelValue;
			 */

		},
		// Appelé avant le déplacement
		beforeDrop : function(event) {

			if (TYPE_PROMOTION == event.source.nodeScope.$modelValue.type) {
				event.source.nodeScope.$$apply = false;
				var nodeData = event.source.nodeScope.$childNodesScope.$modelValue;
				$scope.insertArrayAt(event.dest.nodesScope.$modelValue,
						event.dest.index, nodeData);
				event.source.nodeScope.$modelValue.stagiaires = [];
			}

		},

		dragStop : function(event) {
			var destNode = event.dest.nodesScope;
			var instance = null;
			if (destNode.$parent.$modelValue
					&& destNode.$parent.$modelValue.type == TYPE_INSTANCE) {
				angular.forEach(destNode.$modelValue, function(stagiaire) {
					stagiaire.instanceSessionValidation = {
						id : destNode.$parent.$modelValue.id
					};
				});
				calculAllNbPlaceRestante();
			}
			if (destNode.$parent.$modelValue
					&& destNode.$parent.$modelValue.type == TYPE_PROMOTION) {
				angular.forEach(destNode.$modelValue, function(stagiaire) {
					stagiaire.instanceSessionValidation = null;
					stagiaire.formatedDateHeureDebut = null;
					stagiaire.formatedDateHeureFin = null;
					stagiaire.dateHeureDebut = null;
					stagiaire.dateHeureFin = null;
				});
				calculAllNbPlaceRestante();
			}
		}
	};

	/**
	 * ************************* INSTANCE
	 * *********************************************
	 */

	$scope.toggleInstance = function(instance) {
		instance.collapsed = !instance.collapsed;
	};

	$scope.addInstance = function() {
		$scope.instances.unshift({
			sortOrder : $scope.instances.length,
			type : TYPE_INSTANCE,
			id : INSTANCE_TEMP,
			editing : true,
			collapsed : false,
			jures : [],
			stagiaires : [],
			reservationSalle : {
				formatedDateDebut : $filter('date')($scope.dateInstance,
						'yyyy-MM-ddTHH:mm:ss'),
				formatedDateFin : $filter('date')($scope.dateInstance,
						'yyyy-MM-ddTHH:mm:ss'),
				id : 0,
				salle : null,
				nbPosteLibre : 0
			}
		});

		changeDateInstance();
	};

	$scope.editInstance = function(instance) {
		instance.editing = true;
	};

	$scope.cancelEditingInstance = function(instance) {
		if (instance.id == INSTANCE_TEMP)
			_.remove($scope.instances, {
				id : INSTANCE_TEMP
			});
		instance.editing = false;
	};

	// Suppression d'une instance
	$scope.removeInstance = function(instance, iIndex) {
		/*
		 * if (window.confirm('Etes-sûr de vouloir annuler la réservation de
		 * cette salle ?')) { salle.destroy(); }
		 */

		angular.forEach(instance.stagiaires, function(stagiaire, key) {
			// On supprime le stagiaire de l'instance
			stagiaire.instanceSessionValidation = null;

			var selectedPromotion = $filter('filter')($scope.promotions, {
				name : stagiaire.stagiaire.promotion.libelle
			})[0];

			selectedPromotion.stagiaires.push(stagiaire);
		});

		// On sauvegarde l'instance supprimée si elle est déjà persistée en base
		if (instance.id != INSTANCE_TEMP)
			instancesToDelete.push(instance);

		_.remove($scope.instances, {
			$$hashKey : instance.$$hashKey
		});
	};

	$scope.saveInstance = function(instance) {
		instance.editing = false;
		/*
		 * for (var i = $scope.salles.length - 1; i >= 0; i--) { var salle =
		 * $scope.salles[i]; salle.sortOrder = i + 1; //salle.save(); }
		 */
		calculNbPlaceRestante(instance);
	};

	/*
	 * $scope.addStagiaireToInstance = function(salle) { if
	 * (!salle.newStagiaireName || salle.newStagiaireName.length === 0) {
	 * return; } salle.stagiaires.push({ name : salle.newStagiaireName,
	 * sortOrder : salle.stagiaires.length, type : "stagiaire" });
	 * salle.newStagiaireName = ''; salle.save(); };
	 */

	/**
	 * ************************* STAGIAIRES
	 * *********************************************
	 */
	// Suppression d'un stagiaire associé à une instance
	// Le stagiaire retourne dans sa promotion */
	$scope.removeStagiaireFromInstance = function(instance, stagiaire) {
		// On supprime le stagiaire de l'instance
		stagiaire.instanceSessionValidation = null;
		var index = instance.stagiaires.indexOf(stagiaire);
		if (index > -1) {
			instance.stagiaires.splice(index, 1)[0];
		}
		// On récupère sa promotion
		var selectedPromotion = $filter('filter')($scope.promotions, {
			name : stagiaire.stagiaire.promotion.libelle
		})[0];
		// On le réaffecte à sa promotion
		selectedPromotion.stagiaires.push(stagiaire);

		calculNbPlaceRestante(instance);
	};

	$scope.updateStagiaire = function(stagiaire) {
		stagiaire.formatedDateHeureDebut = $filter('date')($scope.dateInstance,
				'yyyy-MM-dd')
				+ 'T' + $filter('date')(stagiaire.dateHeureDebut, 'HH:mm:ss');
		stagiaire.formatedDateHeureFin = $filter('date')($scope.dateInstance,
				'yyyy-MM-dd')
				+ 'T' + $filter('date')(stagiaire.dateHeureFin, 'HH:mm:ss');
		stagiaire.editing = false;
	};

	$scope.editStagiaire = function(stagiaire) {
		stagiaire.editing = true;
		if (stagiaire.formatedDateHeureDebut)
			stagiaire.dateHeureDebut = new Date(
					stagiaire.formatedDateHeureDebut.replace("T", ""));
		if (stagiaire.formatedDateHeureFin)
			stagiaire.dateHeureFin = new Date(stagiaire.formatedDateHeureFin
					.replace("T", ""));
	}

	/**
	 * ************************* RESERVATION
	 * *********************************************
	 */
	// Si un des éléments de l'IHM est en mode édition
	$scope.oneIsEditing = function() {
		var i, length = $scope.instances.length, isEditing = false, j, lengthJ, instance;

		for (i = 0; i < length; i++) {
			instance = $scope.instances[i];
			if (instance.editing == true) {
				isEditing = true;
				break;
			}
			/*
			 * lengthJ = instance.stagiaires.length; for (j = 0 ; j < lengthJ ;
			 * i ++) { if (instance.stagiaires[j].editing == true) { isEditing =
			 * true; break; } }
			 */
		}
		return isEditing;
	}

	// Réservation et enregistrement de toutes les données de l'écran
	$scope.reserver = function() {

		console.log($scope.instances);
		var instancesToSaved = _.transform($scope.instances, function(result,
				num) {
			var instance = {
				id : num.id,
				jures : num.jures,
				reservationSalle : num.reservationSalle,
				sessionValidation : {
					id : data.id
				}
			};
			result.push({
				first : instance,
				second : num.stagiaires
			});
		});

		stagiairesToSaved = _.transform($scope.promotions,
				function(result, num) {
					result.push.apply(result, num.stagiaires);
				});

		var dataInstances = {
			instances : instancesToSaved,
			instancesStagiaires : stagiairesToSaved,
			instancesToDelete : instancesToDelete
		};

		console.log(dataInstances);
		SessionValidationsFactory.instance.saveData(dataInstances).$promise
				.then(function(success) {
					$modalInstance.close('success');
				}, function(error) {
					toaster.pop('error', null, error.data.message);
				});
	};

	$scope.annuler = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.editStagiaireFromInstance = function() {

	};

	/**
	 * ************************* FONCTIONS
	 * *********************************************
	 */

	$scope.insertAt = function(array, index) {
		var arrayToInsert = Array.prototype.splice.apply(arguments, [ 2 ]);
		return insertArrayAt(array, index, arrayToInsert);
	};

	$scope.insertArrayAt = function(array, index, arrayToInsert) {
		Array.prototype.splice.apply(array, [ index, 0 ].concat(arrayToInsert));
		return array;
	};

};
