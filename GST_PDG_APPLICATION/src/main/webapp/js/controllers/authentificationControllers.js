'use strict';
controllers.controller('authentificationCtrl',
    function ($scope, $location, $route, $state, $rootScope, AuthentificationFactory) {
		$rootScope.hideMenus = true;
        AuthentificationFactory.clearCredentials();
        $scope.login = function () {
            $scope.dataLoading = true;
            AuthentificationFactory.Login($scope.username, $scope.password)
                .success(function (data) {
                	$rootScope.hideMenus = false;
                    AuthentificationFactory.setCredentials(data);
                    $state.go('accueil');
                })
                .error(function (data) {
                	$rootScope.hideMenus = true;
                    $scope.error = data.Message;
                    $scope.dataLoading = true;
                });
        };
    });