'use strict';

var rekadmin = angular.module('myApp.view1', ['ngRoute'])

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'view1/view1.html',
            controller: 'View1Ctrl'
        });
    }]);

rekadmin.controller('View1Ctrl', function ($scope, Cartraker, $http) {
    $scope.cars = [];
    $scope.init = function ()
    {
        $scope.cars = Cartraker.query();
    }

    $scope.list = [];

    $scope.submit = function () {
        if ($scope.kenteken) {
            $scope.list.push(this.kenteken);
            $scope.kenteken = '';
        }
    };
});