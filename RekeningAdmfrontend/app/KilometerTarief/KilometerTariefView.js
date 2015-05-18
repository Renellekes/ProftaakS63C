'use strict';

var rekadmin = angular.module('myApp.KilometerTariefView', ['ngRoute']);

rekadmin.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/KilometerTariefView', {
    templateUrl: 'KilometerTarief/KilometerTariefView.html',
    controller: 'KilometerTariefCtrl'
  });
}]);

rekadmin.controller('KilometerTariefCtrl', function($scope, restService, $http) {
    
    $scope.init = function ()
    {
        $scope.kilometerTarieven = restService.getAlleKilometerTarieven();
    };
    
    $scope.voegKilometerTariefToe = function () {
            var KilometerTarief = {
                regio: $scope.regio,
                tariefCategorie: $scope.tariefCategorie,
                bedrag: $scope.bedrag
            };
            var res = $http.post('http://localhost:47010/Rekeningadministratie/api/RekAdmin/kilometerTarieven/Add', KilometerTarief);
            res.success(function (data, status, headers, config) {
                $scope.kilometerTarieven = restService.getAlleKilometerTarieven();
            });
            $scope.regio = '';
            $scope.tariefCategorie = '';
            $scope.bedrag = '';
    };
    
});