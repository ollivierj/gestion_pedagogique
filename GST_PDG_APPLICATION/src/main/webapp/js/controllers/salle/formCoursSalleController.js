var formCoursSalleCtrl = function($scope, $modalInstance, $filter, $rootScope, $http,
		eventInfo, salles, getByIdFilter, data, UtilisateursFactory, 
		CoursFactory, instanceRef, toaster) {
		
	//Formateurs déjà slectionné au sein de l'instance
	var formateursSelecteds = [];
	
	//Instances à supprimer
	var instancesToDelete = [];
	
	/***** CONSTANT *****/
	// Mot clé pour la création d'une nouvelle absence, utilisé pour un ID temporaire
	var INSTANCE_TEMP = 0;
	var TYPE_INSTANCE = 'instance';
	var TYPE_PROMOTION = 'promotion';
	var TYPE_STAGIAIRE = 'stagiaire';
	
	
	//Formattage du titre
	var dateDebut = $filter('date')(
			new Date(data.formatedDebut.replace("T", " ")),
			'dd/MM/yyyy'
			);
	var dateFin = $filter('date')(
			new Date(data.formatedFin.replace("T", " ")),
			'dd/MM/yyyy'
			);
	
	//Titre de la fenêtre modale1
	$scope.title = 'Cours du ' + dateDebut + ' au ' + dateFin + ' : ' + eventInfo.libelle;
	
	//Chargement du référentiel de salles disponibles
	$scope.referentielSalles = salles;
	var sallesSelected = [];
	
	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken) {
		$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
	}
	
	// On stocke toutes les instances lié à cette évaluation
	$scope.instances = _.transform(instanceRef, function(result, num) {
		//On ajoute l'attribut d'edition et collapse utilisé par l'IHM à chaque instance.
		_.assign(num, {editing: false, collapsed: false, stagiaires: [], type: TYPE_INSTANCE});
		result.push(num);
	});
	
	//Créé une map avec le libelle de la formation
	$scope.promotions = _.transform(data.coursStagiaires, function(result, num) {
		
		// Si la promotion n'est pas présente dans la liste, on l'ajoute
		if (!_.find(result, {name: num.stagiaire.promotion.libelle})) {
			result.push({name : num.stagiaire.promotion.libelle, type: TYPE_PROMOTION, stagiaires : []});
		};
		
		//Si le stagiaire n'est pas associé à une instance
		if (_.isNull(num.instanceCours)) {
			
			//Assignation de l'id de la session et du type (utilisé pour l'IHM)
			_.assign(num, {type: TYPE_STAGIAIRE, cours: {idString: data.idString}});
			_.find(result, {name : num.stagiaire.promotion.libelle}).stagiaires.push(num);
			
		//Si le stagiaire est associé à une instance
		} else {
			//On stocke chacune des instances
			_.find($scope.instances, {id : num.instanceCours.id}).stagiaires.push({id: num.id, 
																						instanceCours: {id: num.instanceCours.id},
																						cours: {idString: data.idString},
																						stagiaire: num.stagiaire,
																						type: TYPE_STAGIAIRE});
		}	
	});
    
	/*************************** FORMATEURS **********************************************/
	// Autocomplétion utilisée pour l'affectation des formateurs
	$scope.chargerFormateurs = function(search) {
		return UtilisateursFactory.formateurs.getData({search: search, debut: dateDebut, fin: dateFin}).$promise.then(
			function(data) {
				var formateurs = [];
				angular.forEach(data, function(item) {
//					if (($filter('filter')(formateursSelecteds, {id: item.id})).length > 0) {
//						item.isUsed = true;
//					}
					formateurs.push(item);
				});
				
				return formateurs;
			});
	};
	
	// Ajoute un formateur l'édition d'une salle
	$scope.addFormateur = function (instance, newFormateur) {
		instance.animateur = newFormateur;
		/*var formateurs = $filter('filter')(formateursSelecteds, {id: newFormateur.id});
		if (formateurs.length > 0) {
			var i, length = formateurs.length;
			for (i = 0 ; i < length ; i ++) {
				formateurs[i].isUsed = true;
			}
		}
		formateursSelecteds.push(newFormateur);*/
	}
	
	// Supression d'un formateur d'un instance
	$scope.removeFormateur = function (pIndex, instance) {
		/*var formateurs = $filter('filter')(formateursSelecteds, {id: formateur.id});
		_.remove(formateursSelecteds, {id : formateur.id});
		_.remove(formateursSelecteds, {$$hashKey : formateurs[0].$$hashKey});*/
		instance.animateur = null;
	};
	
	/*************************** SALLE **********************************************/
	//Récupère le libellé d'une salle
	$scope.getSalleLibelle = function (instance) {
		if (instance.reservationSalle.salle) {
			var salle = _.find($scope.referentielSalles, {id: instance.reservationSalle.salle.id});
			return salle != null && salle.id != null
						? salle.libelle
						: 'Inconnu';
		}
	};

	// Récupère le nombre de places restantes d'une salle
	$scope.getSallePlace = function (instance) {
		var nbPlaces = 0;
		/*if (instance.reservationSalle.salle) {
			var salle = _.find($scope.referentielSalles, {id: instance.reservationSalle.salle.id});
			return salle != null && salle.id != null
						? salle.nbPlaces - instance.stagiaires.length
						: '0';
		}*/
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
		if (instance && instance.reservationSalle.salle && instance.reservationSalle.salle.id) {
			var salle = $filter('filter')($scope.referentielSalles, {id: instance.reservationSalle.salle.id})[0];
			instance.reservationSalle.nbPosteLibre = 
				salle.nbPlaces - instance.stagiaires.length;
		}
	}
	
	var calculAllNbPlaceRestante = function () {
		var i, length = $scope.instances.length;
		for (i = 0 ; i < length ; i ++) {
			calculNbPlaceRestante($scope.instances[i]);
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
					//instance = destNode.$parent.$modelValue;
					//calculNbPlaceRestante(instance);
					stagiaire.instanceCours = {id:destNode.$parent.$modelValue.id};
				});
				calculAllNbPlaceRestante();
			}
			if (destNode.$parent.$modelValue && destNode.$parent.$modelValue.type == TYPE_PROMOTION) {
				angular.forEach(destNode.$modelValue, function(stagiaire) {
					//instance = $filter('filter')($scope.instances, {id : stagiaire.instanceCours.id})[0];
					//calculNbPlaceRestante(instance);
					//calculAllNbPlaceRestante();
					stagiaire.instanceCours = null;
				});
				calculAllNbPlaceRestante();
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
		    collapsed : false,
		    animateur : null,
		    stagiaires: [],
		    reservationSalle : {
		    	formatedDateDebut: data.formatedDebut,
		    	formatedDateFin: data.formatedFin,
		    	id:0,
		    	salle: null,
		    	nbPosteLibre: 0
		    }
		  });
	};

	$scope.editInstance = function(instance) {
		instance.editing = true;
	};
	

	$scope.saveInstance = function(instance) {
		instance.editing = false;
		calculNbPlaceRestante(instance);
	};
	
	$scope.cancelEditingInstance = function(instance) {
		if (instance.id == INSTANCE_TEMP) 
			_.remove($scope.instances, {id: INSTANCE_TEMP});
		instance.editing = false;
	};

	// Suppression d'une instance
	$scope.removeInstance = function(instance, iIndex) {
		if (window.confirm('Etes-sûr de vouloir annuler la réservation de cette salle ?')) {
			
			angular.forEach(instance.stagiaires, function(stagiaire, key) {
				//On supprime le stagiaire de l'instance
				stagiaire.instanceCours = null;
				var selectedPromotion = $filter('filter')($scope.promotions, {
					name : stagiaire.stagiaire.promotion.libelle
				})[0];
				
				selectedPromotion.stagiaires.push(stagiaire);
			});
			//On sauvegarde l'instance supprimée si elle est déjà persistée en base
			if (instance.id != INSTANCE_TEMP)
				instancesToDelete.push(instance);
			//Suppression de l'instance
			_.remove($scope.instances, {$$hashKey: instance.$$hashKey});
		}
	};
	
	
	/*************************** STAGIAIRES **********************************************/
	// Suppression d'un stagiaire associé à une instance
	// Le stagiaire retourne dans sa promotion */
	$scope.removeStagiaireFromInstance = function(instance, stagiaire) {
		//On supprime le stagiaire de l'instance
		stagiaire.instanceCours = null;
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
	$scope.reserver = function () {
		
		var instancesToSaved = _.transform($scope.instances, function(result, num) {				
			var instance = {id : num.id, animateur: num.animateur, reservationSalle: num.reservationSalle, cours : {idString:data.idString}};
			result.push({first : instance, second: num.stagiaires});
		});
		
		stagiairesToSaved = _.transform($scope.promotions, function(result, num) {
			result.push.apply(result, num.stagiaires);			
		});
		
		var dataInstances = {instances : instancesToSaved, instancesStagiaires : stagiairesToSaved, instancesToDelete : instancesToDelete};
		
		CoursFactory.instance.saveData(dataInstances).$promise.then(
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

