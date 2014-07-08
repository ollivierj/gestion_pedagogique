'use strict';

controllers.controller('gestionSessionsValidationCtrl', function($scope, $modal, $log, PromotionsFactory, PersonnelHomologuesFactory, SallesFactory, 
          sessionsValidationData, jurysSallesData) {

  $scope.sessionSelected = [];

  // Données de session de validation
	sessionsValidationData.query().$promise.then( function(data){
    $scope.periodeSessionsValidation = data;
  });

  // Paramétrage du tableau nggrid pour session
	$scope.gridOptionsSessions = {
        data: 'periodeSessionsValidation',
        selectedItems: $scope.sessionSelected,
        multiSelect: false,
        size: 'lg',
        // showFilter: true,
        // showColumnMenu:true,  
        columnDefs : [
                {field:'type', displayName:'Objet'},
                {field:'dateDebut', displayName:'Date de début'},
                {field:'dateFin', displayName:'Date de fin'},
                {displayName:'Actions', cellTemplate: 'partials/templates/ng-grid_actions.html'}
        ],
        showFooter: true,
        enablePaging: true
    };


    $scope.items = ['item1', 'item2', 'item3'];

    // Méthode pour l'affichage de la fenêtre modal
    $scope.afficherFenetreEdition = function() {
        var modalEdit = $modal.open({
            templateUrl: 'partials/gestionSessionsValidation/formulaireEditionSession.html',
            controller: ModalEditionSessionValidationCtrl,
            size: 'lg',
            // Données envoyées au controller de la fenêtre modal
            resolve: {
                items: function () {
                  return $scope.items;
                },
                personnesHomologuees: function(PersonnelHomologuesFactory) {
                  return PersonnelHomologuesFactory.query().$promise;
                },
                salles: function(SallesFactory) {
                  return SallesFactory.query().$promise;
                },
                promotions: function(PromotionsFactory) {
                  return PromotionsFactory.query().$promise;
                },
                jurysSalles: function(jurysSallesData) {
                  return jurysSallesData.query().$promise;
                }
            }
        });
   
        modalEdit.result.then(function (selectedItem) {
          $scope.selected = selectedItem;
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };


});

// Controller de la fenêtre modal
var ModalEditionSessionValidationCtrl = function ($scope, $modalInstance, items, personnesHomologuees, salles, promotions, jurysSalles) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.promotions = promotions;
  $scope.personnesHomologuees = personnesHomologuees;
  $scope.salles= salles;
  $scope.jurysSalles = jurysSalles;

  //Bouton d'action de la fenêtre modal
  $scope.ok = function () {
    $modalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $modalInstance.dismiss('cancel');
  };


  // DATEPICKER
  // Désactiver la sélection du week end pour le datepicker 
  $scope.disabled = function(date, mode) {
    return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };

  // Ouverture de la popup du datepicker
  $scope.open = function($event) {
    $event.preventDefault();
    $event.stopPropagation();

    $scope.opened = true;
  };

  $scope.dateOptions = {
    formatYear: 'yy',
    startingDay: 1
  };

  // Initialisation à la date du jour
  $scope.dt = new Date();
  $scope.format = 'dd/MM/yyyy';


  // UI TREE
  // Afficher les stagiaires pour la promotion
  $scope.togglePromotion = function(promotion) {
    promotion.collapsed = !promotion.collapsed;
  };
  // Afficher les stagiaires pour la salle
  $scope.toggleJurySalle = function(jurySalle) {
    jurySalle.collapsed = !jurySalle.collapsed;
  };
  // Supprimer un noeud jury/salle
  $scope.removeJurySalle = function(jurySalle) {

  };
  // Editer un noeud jury/salle
  $scope.editJurySalle = function(jurySalle) {
    jurySalle.editing = true;
  };
  // Annuler l'édition d'un noeud jury/salle
  $scope.cancelEditingJurySalle = function(jurySalle) {
    jurySalle.editing = false;
  };
  // Affichage du bloc d'édition du créneau de passage du stagiaire
  $scope.editStagiaireFromJurySalle = function(stagiaire) {
    stagiaire.editing = true;
  };
};