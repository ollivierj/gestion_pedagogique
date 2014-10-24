'use strict';
controllers.controller('authentificationCtrl',
    function ($scope, $rootScope, $location, AuthentificationFactory) {
        // reset login status
		$rootScope.hideMenus = true;
        AuthentificationFactory.ClearCredentials();
 
        $scope.login = function () {
            $scope.dataLoading = true;
            AuthentificationFactory.Login($scope.username, $scope.password)
                .success(function (data) {
                	$rootScope.hideMenus = false;
                    AuthentificationFactory.SetCredentials($scope.username, $scope.password);
                    $location.path('/accueil');
                })
                .error(function (data) {
                	$rootScope.hideMenus = true;
                    $scope.error = data.Message;
                    $scope.dataLoading = true;
                });
        };
    });