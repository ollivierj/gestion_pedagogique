'use strict';

/* App Module */

controllers.controller('planningReservationSalleCtrl', function($scope, $location, modalService, SallesFactory, PromotionsFactory, SallesReserveesFactory, AnimateursLibresFactory, PlanningFactory, $filter, PLANNING_ELEMENTS) {
	var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    //Assignation des couleur pour les 3 types d'éléments
    $scope.sessionsValidation = {color: '#70B200'};
    $scope.evaluations = {color: '#FF4219'};
    $scope.cours = {color: '#1242B2'};
    //Construction d'un tableau contenant les constantes de types d'éléments (Affichage de la combo box)
    $scope.typeElements = [{typeName: PLANNING_ELEMENTS.SESSION}, {typeName: PLANNING_ELEMENTS.EVALUATION}, {typeName: PLANNING_ELEMENTS.COURS}];
    
    //Récupère les éléments du planning en fonction d'une date
    $scope.getPlanningElements = function(date) {
    	//Transformation de la date courante en date de début et de fin
    	var debut = $filter('date')(date, 'yyyy-dd-MM');
    	date.setMonth(date.getMonth() + 1);
    	var fin = $filter('date')(date, 'yyyy-dd-MM');
    	
    	//Appel au service pour récupérer les éléments du planning 
    	//en fonction de la date de début et de fin
    	PlanningFactory.elements.query({debut: debut, fin: fin}, function(result) {
    		var resultElements = [];
    		//Parcours des résultats pour les parse sur le bon format
    		_(result).forEach(function (res) {
    			//Création des éléments du planning
    			resultElements.push({
    				id: res.id, 
    				type: res.type, 
    				title: res.libelle + ' - ' + (res.salles != null ? res.salles : 'Aucune salle'), 
    				start: new Date(res.debut),
    				end: new Date(res.fin)
    			});
    		});
    		
    		//Ajout des élements dans les tableaux correpondants.
    		$scope.evaluations.events = _.filter(resultElements, {type:'Evaluation'});
    	    $scope.sessionsValidation.events = _.filter(resultElements, {type:'Session'});
    		$scope.cours.events = _.filter(resultElements, {type:'Cours'});
    		
    		// Rafraichissement de la vue
    		if(!$scope.$$phase) {
    			$scope.$digest();
    		}
    	});
    };
    
    //Affichage de la fenêtre modale correspodant à l'élément sélectionné.
    var modalEditionInstance = {
        backdrop: true,
        keyboard: true,
        size : 'lg',
        modalFade: true,
        templateUrl: '',
        controller: '',
        resolve: {
            items: function () {
              return event;
            },
            salles: function (SallesFactory) {
                return SallesFactory.query().$promise;
            },
            promotions: function (PromotionsFactory) {
                return PromotionsFactory.query().$promise;
            },
            sallesReservees : function (SallesReserveesFactory) {
                return SallesReserveesFactory.query().$promise;
            },
           /* animateursLibres : function (AnimateursLibresFactory) {
                return AnimateursLibresFactory.query().$promise;
            },*/
            
            
        }
    };
    
    // Evènement sur le clic d'un élément
    $scope.alertOnEventClick = function( event, allDay, jsEvent, view ){
        $scope.alertMessage = (event.title + ' was clicked ');
        
        //En fonction un type de l'élément une fenêtre modale différente sera créée
        //Pour chaque type une vue et un controller sont associés.
        switch(event.type) {
        
        	case PLANNING_ELEMENTS.SESSION: modalEditionInstance.templateUrl = 'partials/salle/instance/sessionsValidation.html';
        						modalEditionInstance.controller = 'formulaireReservationSalleCtrl';
        						break;
        						
        	case PLANNING_ELEMENTS.EVALUATION: modalEditionInstance.templateUrl = 'partials/salle/instance/evaluations.html';
        						modalEditionInstance.controller = 'formulaireReservationSalleCtrl';
								break;
								
        	case PLANNING_ELEMENTS.COURS: modalEditionInstance.templateUrl = 'partials/salle/instance/cours.html';
        						modalEditionInstance.controller = 'formulaireReservationSalleCtrl';
								break;
        }
        
       //Affichage de la fenêtre modale
        modalService.showModal(modalEditionInstance, {}).then(function (result) {
            //dataService.deleteCustomer($scope.customer.id).then(function () {
                $location.path('/salle');
            //}, processError);
        });
    };
    
    //Evènement sur le clic du bouton next
    $scope.next = function(calendar) {
    	//Appel l'évènement next du calendrier
    	calendar.fullCalendar('next');
    	//Récupère la date courante du calendrier
    	var moment = calendar.fullCalendar('getDate');
    	$scope.getPlanningElements(moment);
    }
    
    //Evènement sur le clic du bouton previous
    $scope.previous = function(calendar) {
    	calendar.fullCalendar('prev');
    	var moment = calendar.fullCalendar('getDate');
    	$scope.getPlanningElements(moment);
    }
    
    //Evènement sur le clic du bouton today
    $scope.today = function(calendar) {
    	calendar.fullCalendar('today');
    	var moment = calendar.fullCalendar('getDate');
    	$scope.getPlanningElements(moment);
    }
    
    
    /* Change View (day, week, month)*/
    $scope.changeView = function(view,calendar) {
      calendar.fullCalendar('changeView',view);
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
    	  eventClick: $scope.alertOnEventClick,
    	  next: $scope.next
      }
    };
   
    //Contient tous les élements du tableaux
    $scope.eventSources = [$scope.sessionsValidation, $scope.evaluations, $scope.cours];
    

});

