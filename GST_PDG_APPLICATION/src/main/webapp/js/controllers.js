'use strict';

/* Controllers */

var app = angular.module('ng_gst_pdg.controllers', []);


// Clear browser cache (in development mode)
//
// http://stackoverflow.com/questions/14718826/angularjs-disable-partial-caching-on-dev-machine
app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('NavCtrl', ['$scope', '$location', function ($scope, $location) {
	  $scope.menu =   
		  [
	        {	text: 'Menu', 
	        	href:'', 
	        	children :	[
	        	          	{text:'Stagiaires', href:'#/stagiaire-list'}
	        	          	]
	          } 
	       ];
}]);


app.controller('DummyCtrl', ['$scope', 'DummyFactory', function ($scope, DummyFactory) {
    $scope.bla = 'bla from controller';
    DummyFactory.get({}, function (dummyFactory) {
        $scope.firstname = dummyFactory.firstName;
    });
}]);

app.controller('StagiaireListCtrl', ['$scope', 'StagiairesFactory', 'StagiaireFactory', '$location',
    function ($scope, StagiairesFactory, StagiaireFactory, $location) {

        // callback for ng-click 'editStagiaire':
        $scope.editStagiaire = function (stagiaireId) {
            $location.path('/stagiaire-detail/' + stagiaireId);
        };

        // callback for ng-click 'deleteStagiaire':
        $scope.deleteStagiaire = function (stagiaireId) {
        	StagiaireFactory.delete({ id: stagiaireId });
            $scope.stagiaires = StagiairesFactory.query();
        };

        // callback for ng-click 'createStagiaire':
        $scope.createNewStagiaire = function () {
            $location.path('/stagiaire-creation');
        };

        $scope.stagiaires = StagiairesFactory.query();
    }]);

app.controller('StagiaireDetailCtrl', ['$scope', '$routeParams', 'StagiaireFactory', '$location',
    function ($scope, $routeParams, StagiaireFactory, $location) {

        // callback for ng-click 'updateStagiaire':
        $scope.updateStagiaire = function () {
        	StagiaireFactory.update($scope.stagiaire);
            $location.path('/stagiaire-list');
        };

        // callback for ng-click 'cancel':
        $scope.cancel = function () {
            $location.path('/stagiaire-list');
        };

        $scope.stagiaire = StagiaireFactory.show({id: $routeParams.id});
    }]);

app.controller('StagiaireCreationCtrl', ['$scope', 'StagiairesFactory', '$location',
    function ($scope, StagiairesFactory, $location) {

        // callback for ng-click 'createNewStagiaire':
        $scope.createNewStagiaire = function () {
        	StagiairesFactory.create($scope.stagiaire);
            $location.path('/stagiaire-list');
        }
    }]);



function MenuCtrl ($scope, $http) {
	   $scope.menuItems = [];

	   $scope.loadMenu = function() {
	      $http.get('menu/all').success(function(items) {
	         $scope.menuItems = items;
	      });
	   };

	   $scope.loadMenu();
	}
