'use strict';

var rekadmin = angular.module('myApp.voertuigen', ['ngRoute']);

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/voertuigen', {
            templateUrl: 'voertuigen/voertuigen.html',
            controller: 'voertuigenCtrl'
        });
    }]);

rekadmin.controller('voertuigenCtrl', function ($scope, Cartracker, $http) {
    $scope.cars = [];
    var bewaarAutoInfo = null;
    $scope.init = function ()
    {
        $scope.cars = Cartracker.query();
        //$scope.options = [{ id: 1, kenteken: "1-gha-23", zitplaats: "5", kleur: "paars", voertuig: "beta"}, { id: 2, kenteken: "23-hah-7", zitplaats: "3", kleur: "groen", voertuig: "alfa" }];
        $scope.selectedOption = $scope.cars[0];
    };
    
    $scope.update = function () {
        
         
        $scope.id = $scope.selectedOption.id;        
        $scope.voertuig = $scope.selectedOption.voertuig;
        $scope.eersteKleur = $scope.selectedOption.eersteKleur;
        $scope.zitplaatsen = $scope.selectedOption.zitplaatsen;
        bewaarAutoInfo = $scope.selectedOption;
    };
    
 $scope.submit = function () {
        if ($scope.id) {
            var Auto = {
                id: bewaarAutoInfo.id,
                kenteken: bewaarAutoInfo.kenteken,
                voertuig: bewaarAutoInfo.voertuig,
                eersteKleur: $scope.eersteKleur,
                zitplaatsen: bewaarAutoInfo.zitplaatsen,
                fileInfo:bewaarAutoInfo.fileInfo,
                gestolen:bewaarAutoInfo.gestolen
            };
            var res = $http.post('http://localhost:5051/RekeningAdministratieBackend/api/RekAdmin/modifyAuto', Auto);
            res.success(function (data, status, headers, config) {
                $scope.cars = Cartraker.query();
            });
            $scope.selectedOption = '';            
            bewaarAutoInfo = null;
            $scope.id = '';
            $scope.voertuig = '';
            $scope.eersteKleur = '';
            $scope.zitplaatsen = '';            

        }
    };            
});