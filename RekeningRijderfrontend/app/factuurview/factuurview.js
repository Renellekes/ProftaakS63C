'use strict';

var rekadmin = angular.module('myApp.factuurview', ['ngRoute']);

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/factuurview', {
            templateUrl: 'factuurview/factuurview.html',
            controller: 'factuurviewCtrl'
        });
    }]);

rekadmin.controller('factuurviewCtrl', function ($scope, Cartracker) {
    $scope.facturen = [];
    $scope.init = function ()
    {
        $scope.facturen = Cartracker.getFactuurs();
    };

    $scope.list = [];    
});
