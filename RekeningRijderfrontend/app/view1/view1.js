'use strict';

var rekadmin = angular.module('myApp.view1', ['ngRoute']);

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
        //$scope.options = [{ id: 1, kenteken: "1-gha-23", zitplaats: "5", kleur: "paars", voertuig: "beta"}, { id: 2, kenteken: "23-hah-7", zitplaats: "3", kleur: "groen", voertuig: "alfa" }];
        $scope.selectedOption = $scope.cars[0];
    };
 $scope.modifyCar = function () {
     
             var Auto = {
                id: $scope.cars.id,
                kenteken: $scope.cars.kenteken,
                eigenaar: $scope.cars.eigenaar,
                gestolen: $scope.cars.gestolen,
                voertuig: $scope.cars.voertuig,
                eersteKleur: $scope.kleur,
                zitplaaten: $scope.cars.zitplaaten
            };
            $scope.list = Auto[0];  
            var res = $http.post('http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/modifyCar', Auto);
            res.success(function (data, status, headers, config) {
                $scope.cars = Cartraker.query();
            });
    };
      
});