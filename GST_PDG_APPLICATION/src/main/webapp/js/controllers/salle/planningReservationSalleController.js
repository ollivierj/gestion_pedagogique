'use strict';

/* App Module */

controllers.controller('planningReservationSalleCtrl', function($scope, $location, $rootScope, $http, 
		modalService, PromotionsFactory, SallesReserveesFactory, AnimateursLibresFactory, 
		PlanningFactory, $filter, CONSTANTS, planningElements, toaster) {

	//Date du calendrier
	$scope.moment = new Date();
	//Type d'instance sélectionne
	$scope.typeInstance = null;
	
	if (!$rootScope.utilisateurConnecte && !$rootScope.authtoken){
		$http.defaults.headers.common.Authorization =  'Basic ' + $rootScope.authtoken;
	}
	
	//Assignation des couleur pour les 3 types d'éléments
    $scope.sessionsValidation = {color: '#70B200'};
    $scope.evaluations = {color: '#FF4219'};
    $scope.cours = {color: '#1242B2'};
    //Construction d'un tableau contenant les constantes de types d'éléments (Affichage de la combo box)
    $scope.typeElements = [{typeName: CONSTANTS.PLANNING_ELEMENTS.SESSION}, {typeName: CONSTANTS.PLANNING_ELEMENTS.EVALUATION}, {typeName: CONSTANTS.PLANNING_ELEMENTS.COURS}];
    
    $scope.eventSources = [];
    
	//Transforme le résultat des éléments en éléments graphique correspondant à ui calendar.
	var initElements = function (results, type) {

    	var elements = [];
		//Parcours des résultats pour les parse sur le bon format
		_(results).forEach(function (res) {
			//Création des éléments du planning
			elements.push({
				id: res.id, 
				type: res.type, 
				entityId: res.elementId,
				title: res.libelle + ' - ' + (res.salles != null ? res.salles : 'Aucune salle'),
				libelle: res.libelle,
				start: new Date(res.debut),
				end: new Date(res.fin)
			});
		});
		
		//Suivant le type sélectionné les données sont rafraichies
		if (type == undefined || type == null) {
			$scope.evaluations.events = _.filter(elements, {type:'Evaluation'});
		    $scope.sessionsValidation.events = _.filter(elements, {type:'Session'});
			$scope.cours.events = _.filter(elements, {type:'Cours'});
        } else {
        	switch (type) {
	        	case CONSTANTS.PLANNING_ELEMENTS.SESSION:
					        		$scope.evaluations.events = [];
					    		    $scope.sessionsValidation.events = _.filter(elements, {type:'Session'});
					    			$scope.cours.events = [];
	        						break;
	        						
	        	case CONSTANTS.PLANNING_ELEMENTS.EVALUATION:
					        		$scope.evaluations.events = _.filter(elements, {type:'Evaluation'});
					    		    $scope.sessionsValidation.events = [];
					    			$scope.cours.events = [];
									break;
									
	        	case CONSTANTS.PLANNING_ELEMENTS.COURS:
					        		$scope.evaluations.events = [];
					    		    $scope.sessionsValidation.events = [];
					    			$scope.cours.events = _.filter(elements, {type:'Cours'});
									break;
        	}
        }
		
		// Rafraichissement de la vue
		if(!$scope.$$phase) {
			$scope.$digest();
		}
    };
    
    //Utilisé lors de l'affichage du planning la première fois
    initElements(planningElements);
    
    //Récupère les éléments du planning en fonction d'une date
    $scope.getPlanningElements = function(date, type) {
    	//Appel au service pour récupérer les éléments du planning 
    	//en fonction de la date de début et de fin
    	PlanningFactory.initElements(angular.copy(date)).then(function(result) {
    		initElements(result, type);
    	});
    };
    
    //Affichage de la fenêtre modale correspodant à l'élément sélectionné.
    var modalEditionInstance = {
        backdrop: true,
        keyboard: true,
        size : 'lg',
        modalFade: true,
        resolve: {
            salles: function (SalleFactory) {
                return SalleFactory.salle.referenceSalle.query().$promise;
            }
        }
    };
    
    // Evènement sur le clic d'un élément
    $scope.alertOnEventClick = function( event, allDay, jsEvent, view ){
        $scope.alertMessage = (event.title + ' was clicked ');
        //En fonction un type de l'élément une fenêtre modale différente sera créée
        //Pour chaque type une vue et un controller sont associés.
        switch(event.type) {
        
        	case CONSTANTS.PLANNING_ELEMENTS.SESSION: 
						modalEditionInstance.templateUrl = 'partials/salle/instance/sessionsValidation.html';
						modalEditionInstance.controller = 'formSessionSalleCtrl';
						//Données liées à la session de validation, stagiaire inscrit et non inscrit
						modalEditionInstance.resolve.data = function (SessionValidationsFactory) {
							return SessionValidationsFactory.detail.getData({id:event.entityId}).$promise;
						};
						//Toutes les instances liées à cette session de validation
						modalEditionInstance.resolve.instanceRef = function (SessionValidationsFactory) {
							return SessionValidationsFactory.instanceRefs.getAll({id:event.entityId}).$promise;
						};
						//Les infos de la session de validation
						modalEditionInstance.resolve.eventInfo = function () {
							return event;
			            };
						break;
        						
        	case CONSTANTS.PLANNING_ELEMENTS.EVALUATION: 
						modalEditionInstance.templateUrl = 'partials/salle/instance/evaluations.html';
						modalEditionInstance.controller = 'formEvaluationSalleCtrl';
						//Données liées à l'évaluation, stagiaire inscrit et non inscrit
						modalEditionInstance.resolve.data = function (EvaluationsFactory) {
							return EvaluationsFactory.detail.getData({id:event.entityId}).$promise;
						};
						//Toutes les instances liées à cette évaluation
						modalEditionInstance.resolve.instanceRef = function (EvaluationsFactory) {
							return EvaluationsFactory.instanceRefs.getAll({id:event.entityId}).$promise;
						};
						//Les infos de l'évaluation
						modalEditionInstance.resolve.eventInfo = function () {
							return event;
			            };
						break;
								
        	case CONSTANTS.PLANNING_ELEMENTS.COURS: 
						modalEditionInstance.templateUrl = 'partials/salle/instance/cours.html';
						modalEditionInstance.controller = 'formCoursSalleCtrl';
						//Données liées au cours, stagiaire inscrit et non inscrit
						modalEditionInstance.resolve.data = function (CoursFactory) {
							return CoursFactory.detail.getData({id:event.entityId}).$promise;
						};
						//Toutes les instances liées à ce cours
						modalEditionInstance.resolve.instanceRef = function (CoursFactory) {
							return CoursFactory.instanceRefs.getAll({idString:event.entityId}).$promise;planning
						};
						//Les infos du cours
						modalEditionInstance.resolve.eventInfo = function () {
							return event;
			            };
						break;
        }
        
       //Affichage de la fenêtre modale
        modalService.showModal(modalEditionInstance, {}).then(function (result) {
        	toaster.pop('success', null, "Enregsitrement de l'instance effectuée");
        	$scope.getPlanningElements($scope.moment);
        });
    };
    
    //Evènement sur le clic du bouton next
    $scope.next = function(calendar) {
    	//Appel l'évènement next du calendrier
    	calendar.fullCalendar('next');
    	//Récupère la date courante du calendrier
    	$scope.moment = calendar.fullCalendar('getDate');
    	$scope.getPlanningElements($scope.moment, $scope.typeInstance);
    }
    
    //Evènement sur le clic du bouton previous
    $scope.previous = function(calendar) {
    	calendar.fullCalendar('prev');
    	$scope.moment = calendar.fullCalendar('getDate');
    	$scope.getPlanningElements($scope.moment, $scope.typeInstance);
    }
    
    //Evènement sur le clic du bouton today
    $scope.today = function(calendar) {
    	calendar.fullCalendar('today');
    	$scope.moment = calendar.fullCalendar('getDate');
    	$scope.getPlanningElements($scope.moment, $scope.typeInstance);
    }
    
    
    /* Change View (day, week, month)*/
    $scope.changeView = function(view,calendar) {
      calendar.fullCalendar('changeView',view);
      $scope.moment = calendar.fullCalendar('getDate');
	  $scope.getPlanningElements($scope.moment, $scope.typeInstance);
    };
    
    //Configuration du calendrier
    $scope.uiConfig = {
      calendar:{
    	  monthNames:['Janvier','Février','Mars','Avril','Mai','Juin','Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
    	  monthNamesShort:['janv.','févr.','mars','avr.','mai','juin','juil.','août','sept.','oct.','nov.','déc.'],
    	  dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
    	  dayNamesShort: ['Dim', 'Lun', 'Mar', 'Mer', 'Jeu', 'Ven', 'Sam'],
    	  titleFormat: {
    	      month: 'MMMM yyyy',
    	      week: "d[ MMMM][ yyyy]{ - d MMMM yyyy}",
    	      day: 'dddd d MMMM yyyy'
    	  },
    	  columnFormat: {
    	      month: 'ddd',
    	      week: 'ddd d',
    	      day: ''
    	  },
    	  axisFormat: 'H:mm', 
    	  timeFormat: {
    	      '': 'H:mm', 
    	      agenda: 'H:mm{ - H:mm}'
    	  },
    	  firstDay:1,
    	  buttonText: {
    	      today: 'aujourd\'hui',
    	      day: 'jour',
    	      week:'semaine',
    	      month:'mois'
    	  }, 
    	  allDayText : 'toute la journée',
    	  height: 450,
    	  editable: false,
    	  //Ne pas faire apparaitre les week end
    	  weekends: false,
    	  header:{
    		  left: '',
    		  center: 'title',
    		  right: ''
    	  },
    	  eventClick: $scope.alertOnEventClick
      }
    };
   
    //Contient tous les élements du tableaux
    $scope.eventSources = [$scope.sessionsValidation, $scope.evaluations, $scope.cours];
    
    $scope.$watch('typeElement', function (newVal, oldVal) {
    	//Récupération du type
    	$scope.typeInstance = null
    	if (newVal)
    		$scope.typeInstance = newVal.typeName
		//Refresh des éléments du planning
    	$scope.getPlanningElements($scope.moment, $scope.typeInstance);
    }, true);
    
});

