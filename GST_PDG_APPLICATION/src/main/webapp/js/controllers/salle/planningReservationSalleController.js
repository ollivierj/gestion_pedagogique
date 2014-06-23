'use strict';

/* App Module */

controllers.controller('planningReservationSalleCtrl', function($scope, $location, modalService, SallesFactory, PromotionsFactory, SallesReserveesFactory, AnimateursLibresFactory) {
	var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
   
    $scope.reservationSalle103 = {
       color: '#f00',
       //textColor: 'yellow',
       events: [ 
                {id:5, salleid : 2, animateurid :2,coursid:1,  title: 'Salle 103 - Cours Reseaux animé par Henri Vincent',start: new Date(y, m, 1)},
                {id:6, salleid : 2, animateurid :2,coursid:2, title: 'Salle 103 - Cours Algo animé par Henri Vincent',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
                {id:7, salleid : 2, animateurid :2,coursid:3, title: 'Salle 103 - Cours Projet animé par Henri Vincent',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
                {id:8, salleid : 2, animateurid :2,coursid:4, title: 'Salle 103 - Cours SOA animé par Henri Vincent',start: new Date(y, m, 28),end: new Date(y, m, 29)/*,url: 'http://google.com/'*/}
        ]
    };
    
    $scope.reservationSalle104 = {
    	       color: '#a8ed13',
    	       //textColor: 'yellow',
    	       events: [ 
    	                {id:1,salleid : 10, animateurid :1, coursid:5,title: 'Salle 104 - Cours JAVA animé par Jean Brouette',start: new Date(y, m, 1)},
    	                {id:2,salleid : 10, animateurid :1, coursid:6,title: 'Salle 104 - Cours PHP animé par Jean Brouette',start: new Date(y, m, d - 5),end: new Date(y, m, d - 4)},
    	                {id:3,salleid : 10, animateurid :1, coursid:7,title: 'Salle 104 - Cours SQL animé par Jean Brouette',start: new Date(y, m, d + 4, 19, 0),end: new Date(y, m, d + 7, 22, 30),allDay: false},
    	                {id:4,salleid : 10, animateurid :1, coursid:8,title: 'Salle 104 - Cours Math animé par Jean Brouette',start: new Date(y, m, 28),end: new Date(y, m, 29)/*,url: 'http://google.com/'*/}
    	        ]
    	    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function( event, allDay, jsEvent, view ){
        $scope.alertMessage = (event.title + ' was clicked ');
        var modalDefaults = {
                backdrop: true,
                keyboard: true,
                size : 'lg',
                modalFade: true,
                templateUrl: 'partials/salle/formulaireReservationSalle.html',
                controller: formulaireReservationSalleCtrl,
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
                    animateursLibres : function (AnimateursLibresFactory) {
                        return AnimateursLibresFactory.query().$promise;
                    },
                    
                    
                }
            };
       
            modalService.showModal(modalDefaults, {}).then(function (result) {
                //dataService.deleteCustomer($scope.customer.id).then(function () {
                    $location.path('/salle');
                //}, processError);
            });
    };
    /* alert on Drop */
     $scope.alertOnDrop = function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Droped to make dayDelta ' + dayDelta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ){
       $scope.alertMessage = ('Event Resized to make dayDelta ' + minuteDelta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function(sources,source) {
      var canAdd = 0;
      angular.forEach(sources,function(value, key){
        if(sources[key] === source){
          sources.splice(key,1);
          canAdd = 1;
        }
      });
      if(canAdd === 0){
        sources.push(source);
      }
    };
    /* add custom event*/
    $scope.addEvent = function() {
      $scope.events.push({
        title: 'Open Sesame',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        className: ['openSesame']
      });
    };
    /* remove event */
    $scope.remove = function(index) {
      $scope.events.splice(index,1);
    };
    /* Change View */
    $scope.changeView = function(view,calendar) {
      calendar.fullCalendar('changeView',view);
    };
    /* Change View */
    $scope.renderCalender = function(calendar) {
      calendar.fullCalendar('render');
    };
    /* config object */
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
        editable: true,
        header:{
          left: 'today prev,next',
          center: 'title',
          right: ''
        },
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize
      }
    };
    


   
    /* event sources array*/
    $scope.eventSources = [$scope.reservationSalle103, $scope.reservationSalle104];
    

});

