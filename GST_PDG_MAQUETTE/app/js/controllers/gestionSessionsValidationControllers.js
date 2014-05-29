'use strict';

controllers.controller('gestionSessionsValidationCtrl', function($scope, $modal, $log, PromotionsFactory, pHomologuee, SallesFactory, sessionsValidationData) {

  $scope.sessionSelected = [];

  // Données de session de validation
	$scope.periodeSessionsValidation = [
		{type:'Session de validation', formation:'AL3', dateDebut:'22/06/2014', dateFin:'27/06/2014', salle:'302', 
            jury: ['jean', 'marc'], 
            stagiaires:['toto', 'titi']},
		{type:'CQPM', formation:'Metallurgie', dateDebut:'10/08/2014', dateFin:'11/08/2014', salle:'102'},
        {type:'Session de validation', formation:'MS2I', dateDebut:'01/09/2014', dateFin:'06/09/2014', salle:'103'},
        {type:'Session de validation', formation:'Micro', dateDebut:'13/10/2014', dateFin:'18/10/2014', salle:'203'},
        {type:'Session de validation', formation:'AL2', dateDebut:'05/11/2014', dateFin:'10/11/2014', salle:'101'},
        {type:'Session de validation', formation:'AL2', dateDebut:'11/12/2014', dateFin:'16/12/2014', salle:'207'},
        {type:'CQPM', formation:'Metallurgie', dateDebut:'02/01/2015', dateFin:'03/01/2015', salle:'208'},
        {type:'Session de validation', formation:'ASR', dateDebut:'15/02/2015', dateFin:'20/02/2015', salle:'301'}
	];

  // Paramétrage du tableau nggrid pour session
	$scope.gridOptionsSessions = {
        data: 'periodeSessionsValidation',
        selectedItems: $scope.sessionSelected,
        multiSelect: false,
        size: 'lg',
        columnDefs : [
                {field:'type', displayName:'Type'},
                {field:'formation', displayName:'Formation'},
                {field:'dateDebut', displayName:'Date de début'},
                {field:'dateFin', displayName:'Date de fin'},
                {field:'salle', displayName:'Salle'}
        ]
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
                personnesHomologuees: function(pHomologuee) {
                  return pHomologuee.query().$promise;
                },
                salles: function(SallesFactory) {
                  return SallesFactory.query().$promise;
                },
                promotions: function(PromotionsFactory) {
                  return PromotionsFactory.query().$promise;
                },
                sessionsValidation: function(sessionsValidationData) {
                  return sessionsValidationData.query().$promise;
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
var ModalEditionSessionValidationCtrl = function ($scope, $modalInstance, items, personnesHomologuees, salles, promotions, sessionsValidation) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.promotions = promotions;
  $scope.personnesHomologuees = personnesHomologuees;
  $scope.salles= salles;
  $scope.sessionsValidation = sessionsValidation;

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
  // Afficher la promotion
  $scope.togglePromotion = function(promotion) {
    promotion.collapsed = !promotion.collapsed;
  };

  $scope.toggleSession = function(session) {
    session.collapsed = !session.collapsed;
  };

  $scope.removeSession = function(session) {

  };

  $scope.editSession = function(session) {
    session.editing = true;
  };
};