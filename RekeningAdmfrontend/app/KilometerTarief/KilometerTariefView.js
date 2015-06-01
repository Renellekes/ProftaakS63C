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
            var res = $http.post('http://localhost:5051/Rekeningadministratie/api/RekAdmin/KilometerTarieven/Add', KilometerTarief);
            res.success(function (data, status, headers, config) {
                $scope.kilometerTarieven = restService.getAlleKilometerTarieven();
            });
            $scope.regio = '';
            $scope.tariefCategorie = '';
            $scope.bedrag = '';
    };
    
    
    $scope.chooseTarief = function (tarief) {
        $scope.wijzigid = tarief.id;
        $scope.wijzigregio = tarief.regio;
        $scope.wijzigcategorie = tarief.tariefCategorie;
        $scope.wijzigbedrag = tarief.bedrag;
    }
    
    $scope.wijzigKilometerTarief = function () {
        if ($scope.wijzigid) {
            var tarief = {
                id: $scope.wijzigid,
                regio: $scope.wijzigregio,
                tariefCategorie: $scope.wijzigcategorie,
                bedrag: $scope.wijzigbedrag
            };

            var res = $http.post('http://localhost:5051/RekeningAdministratieBackend/api/RekAdmin/KilometerTarieven/Edit', tarief);
            res.success(function (data, status, headers, config) {
                $scope.kilometerTarieven = restService.getAlleKilometerTarieven();
            });
        }
    };
    
});
