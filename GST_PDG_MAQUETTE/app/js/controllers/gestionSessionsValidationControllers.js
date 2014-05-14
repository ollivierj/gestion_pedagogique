'use strict';

controllers.controller('gestionSessionsValidationCtrl', function($scope) {

    $scope.sessionSelected = [];

	$scope.sessionsValidation = [
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

	$scope.gridOptionsSessions = {
        data: 'sessionsValidation',
        selectedItems: $scope.sessionSelected,
        multiSelect: false,
        columnDefs : [
                {field:'type', displayName:'Type'},
                {field:'formation', displayName:'Formation'},
                {field:'dateDebut', displayName:'Date de début'},
                {field:'dateFin', displayName:'Date de fin'},
                {field:'salle', displayName:'Salle'}
        ]
    };

});