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
        
        //$scope.facturen = [{ id: 1, kenteken: "1-gha-23", zitplaats: "5", kleur: "paars", voertuig: "beta"}, { id: 2, kenteken: "23-hah-7", zitplaats: "3", kleur: "groen", voertuig: "alfa" }];
       
    };

$scope.kenteken = function (id)
    {
       return Cartracker.getCar(id);        
        
    };
    $scope.list = [];    
});
