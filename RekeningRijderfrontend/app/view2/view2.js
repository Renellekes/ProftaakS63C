'use strict';

var rekadmin = angular.module('myApp.view2', ['ngRoute']);

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'view2/view2.html',
            controller: 'View2Ctrl'
        });
    }]);

rekadmin.controller('View2Ctrl', function ($scope, restService, $http) {
    $scope.eigenaars = [];
    $scope.init = function ()
    {
       //$scope.eigenaar = [{naam:'hans', adres:"ja", woonplaats:"nee"}];
       $scope.eigenaars = restService.getEigenaars();
        $scope.owner = $scope.eigenaar[0];
        
    };
});