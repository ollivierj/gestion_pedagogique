/**
 * Controller de la page detail stagiaire
 */
controllers.controller('detailStagiaireCtrl', function($scope, detail, absences, SAbsenceFactory, $modal, $log, SEchangeFactory, $filter) {
    
    //Initialisation de la variable stagiaire du scope avec la variable detail (resolve de la route)
    $scope.stagiaire = detail;
    
    $scope.absences = absences.data;
    
    // Les absences ou retards du stagiaire
    $scope.gridOptionsAbsences = {
        data: 'absences',
        columnDefs : [
              	{displayName:'Absence / Retard', enableCellEdit: true,
              		editableCellTemplate: 'partials/stagiaire/template/absenceRetardButton.html',
          			cellTemplate: 'partials/stagiaire/template/absenceRetardButtonText.html'},
                {field:'formatedDate', displayName:'Date', enableCellEdit: true,
          			cellFilter: 'date : \'dd/MM/yyyy\'',
                	editableCellTemplate: 'partials/stagiaire/template/datepicker.html'},
            	{field:'formatedTime', displayName:'Heure', enableCellEdit: true,
            		editableCellTemplate: 'partials/stagiaire/template/timepicker.html',
                	cellTemplate: 'partials/stagiaire/template/timepickerText.html'},	
                {field:'auteur', displayName:'Auteur', enableCellEdit: false},
                {field:'motif', displayName:'Motif', enableCellEdit: true},
                {displayName:'Actions', cellTemplate: 'partials/stagiaire/template/actionsAbsence.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false
    };

	//L'édition d'une des colonnes du tableau active le mode edition
    $scope.editRowAbsence = function(entity) {
    	entity.editModeAbsence = true;
    };
    
    //Annule l'édition d'une ligne
    $scope.cancelAbsence = function(entity) {
    	entity.editModeAbsence = false;
    };

    // Les avis concernant le stagiaire
    $scope.gridOptionsAvis = {
        data: 'stagiaire.avis',
        columnDefs : [
                {field:'date', displayName:'Date'},
                {field:"formateur", displayName:'Auteur'},
                {field:'avis', displayName:'Avis'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false
    };
    
    $scope.okAbsence = function (entity) {
    	entity.formatedDate = $filter('date')(entity.formatedDate, 'dd/MM/yyyy');
    	entity.formatedTime = $filter('date')(entity.formatedTime, 'HH:mm');
    	//Mock de l'auteur
    	entity.auteur = {};
    	entity.auteur.id = 1;
    	entity.dateSaisie = $filter('date')(new Date(), 'dd/MM/yyyy HH:mm');
    	SAbsenceFactory.create(entity);
    	entity.editModeAbsence = false;
    	SAbsenceFactory.getAbsencesInit();
    };
    
    $scope.removeRowAbsence = function(entity) {
    	SAbsenceFactory.deleteLine.$delete
    };
    
    // Les échanges concernant le stagiaire
    $scope.gridOptionsEchanges = {
        data: 'stagiaire.echanges',
        columnDefs : [
                {field:'dateSaisie', displayName:'Date'},
                {field:"auteur", displayName:'Auteur'},
                {field:'commentaire', displayName:'Commentaire'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ],
        enablePaging: true,
        showFooter: true,
        multiSelect: false
    };
     
    $scope.createAbsence = function() {
    	$scope.absences.push({});
    	/*
    	var modalAdd = $modal.open({
			templateUrl : 'partials/stagiaire/modalEdition/absenceEdition.html',
			controller : modalEditionStagiaireAbsenceCtrl,
			resolve : {
				title : function() {
					return "Déclaration d'une absence";
				},
				readonly : function() {
					return false;
				},
				absence : function(){ 
					return {}
				},
				okTitle : function() {
					return "Créer";
				}
			}
		});

		modalAdd.result.then(function(selectedItem) {
//			TitreProfessionnelsFactory.refreshData($scope);
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
		*/
    };
    
    
    $scope.createEchange = function() {
		var modalAdd = $modal.open({
					templateUrl : 'partials/templates/form.html',
					controller : modalEditionEchangeCtrl,
					resolve : {
						title : function() {return "Ajout d'un échange";},
						readonly : function() {return false;},
						echange : function(){ return {}},
						schema : function(SEchangeFactory) {
							return SEchangeFactory.jsonschema.get().$promise;
						},
						okTitle : function() {return "Enregistrer";},
						ok : function() { return function(item){ return SEchangeFactory.createEchange.create(item);}}
					}
				});

		modalAdd.result.then(function(selectedItem) {
			
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};

    
});

var modalEditionStagiaireAbsenceCtrl = function($scope, $modalInstance, $filter,
		SAbsenceFactory, title, readonly, absence, okTitle) {
	
	$scope.absence = absence;
	$scope.okTitle = okTitle;
	$scope.title = title;
	
	$scope.absence.time = new Date(1970, 0, 1, 10, 00);
	$scope.absence.date = "2014-09-04T06:20:49.068Z"; // <- [object Date]
	
	// Class et libelle pour le bouton absence / retard
	var classAbsences	= ["btn btn-danger col-lg-offset-3 col-lg-6", 
	                 	   "btn btn-warning col-lg-offset-3 col-lg-6"];
	var typeAbsences	= ["Absence", "Retard"];
	var classRadioButton = ['btn btn-primary', 'btn btn-primary active'];
	
	//Variable pour la gestion des limites des heures sélectionnables
	var timeLimit	= [new Date(1970, 0, 1, 9, 00), 
	             	   new Date(1970, 0, 1, 12, 30), 
	             	   new Date(1970, 0, 1, 14, 00), 
	             	   new Date(1970, 0, 1, 17, 30)];
	
	$scope.isAbsence = false;
	//Variable pour la sélection de la journée
	$scope.isFullDay = false;
	
	var initializeTypeClass = function () {
		$scope.classMatin	= classRadioButton[0];
		$scope.classApresM	= classRadioButton[0];
		$scope.classJournee	= classRadioButton[0];
	};
	
	//	Initialisation du timepicker en fonction du moment de la journée sélectionné
	$scope.switchMoment = function (moment) {
		initializeTypeClass();
		//Choix du type matin après midi ou journée. Les limites de temps sont fixé en fonction.
		//Pour la journée, l'accès au timepicker est bloqué
		switch (moment) {
			case 'matin'	: 	$scope.timeMin = timeLimit[0];
						   		$scope.timeMax = timeLimit[1];
						   		$scope.isFullDay = false;
						   		$scope.classMatin = classRadioButton[1];
						   		if ($scope.isAbsence)
						   			$scope.absence.time = new Date(1970, 0, 1, 9, 00);
						   		break;
						   		
			case 'apresM'	:	$scope.timeMin = timeLimit[2];
						   		$scope.timeMax = timeLimit[3];
						   		$scope.isFullDay = false;
						   		$scope.classApresM = classRadioButton[1];
						   		if ($scope.isAbsence)
						   			$scope.absence.time = new Date(1970, 0, 1, 14, 00);
						   		break;
						   		
			case 'journee'  :   $scope.isFullDay = true;
								$scope.classJournee = classRadioButton[1];
								break;
								
			default			:	break;
		}
	};
	
	$scope.switchMoment('matin');
	
	//	Change le texte et la couleur du bouton absence/ Retard
	$scope.changeType = function() {
		//Affichage du timepicker ou non en fonction du choix retard / absence
		$scope.isAbsence = !$scope.isAbsence;
		if($scope.isAbsence) {
			$scope.classAbsence	= classAbsences[0];
			$scope.typeAbsence 	= typeAbsences[0];
		} else {
			$scope.classAbsence = classAbsences[1];
			$scope.typeAbsence 	= typeAbsences[1];
		}
	};
	
	$scope.changeType();
	
	$scope.ok = function() {
		
		//Format des dates
		var dateFormat = $filter('date')($scope.absence.date, 'yyyy/MM/dd');
		var timeFormat = $filter('date')($scope.absence.time, 'HH:mm:ss');
		$scope.absence.date = dateFormat + ' ' + timeFormat;
		//Mock de l'auteur
		$scope.absence.auteur = {};
		$scope.absence.auteur.id = 1;
		
		SAbsenceFactory.create($scope.absence);
	};
	
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};

var modalEditionEchangeCtrl = function($scope, $modalInstance,
		SEchangeFactory, title, readonly, echange, schema, ok, okTitle) {
	$scope.title = title;
	$scope.data = titreProfessionnel;
	$scope.data.readonly = readonly;
	$scope.okTitle = okTitle;
	$scope.ok = ok;
	$scope.schema = schema;
	$scope.form = 
		[
			{
				key : "formatedDate",
				disabled : $scope.data.readonly
			},
			{
	    		key : "commentaire",
	    		disabled : $scope.data.readonly
			},
			{
		    	type: "conditional",
	            condition: "!data.readonly", 
	            items: 
	           	 [
	           	 {
	           	 type: "actions",
	           	 items:	
	           		 [
			         {
			         type: 'submit', 
			         title: $scope.okTitle 
			         },
			         { 
					 type: 'button', 
					 title: 'Annuler', 
					 onClick: "cancel()" 
					 }
			         ]
	           	 }
	           	 ]	
			},
			{
		    	type: "conditional",
	            condition: "data.readonly", 
	            items: 
	           	 [
	           	 {
	           	 type: "actions",
	           	 items:	
	           		 [
			         {
			         type: 'submit', 
			         title: $scope.okTitle 
			         }
			         ]
	           	 }
	           	 ]	
			}
	    ];
	$scope.decorator = 'bootstrap-decorator';
	$scope.submit =function(){
		$scope.ok($scope.data).$promise.then(
					function(response) {
						$modalInstance.close($scope.data);
					}, 
					function(reason) {
						alert('Echec: ' + reason);
					});
		

	};
	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};