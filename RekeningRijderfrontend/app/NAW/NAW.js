'use strict';

var rekadmin = angular.module('myApp.NAW', ['ngRoute']);

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/NAW', {
            templateUrl: 'NAW/NAW.html',
            controller: 'NAWCtrl'
        });
    }]);

rekadmin.controller('NAWCtrl', function ($scope, restService, $http) {
    $scope.eigenaars = [];
    var bewaarEigenaarInfo = null;
    $scope.init = function ()
    {
       $scope.eigenaars = restService.getEigenaars();
        $scope.owner = $scope.eigenaars[0];
        
    };
    
     $scope.update = function () {       
         
        $scope.id = $scope.selectedOption.id;        
        $scope.adres = $scope.selectedOption.adres;
        $scope.woonplaats = $scope.selectedOption.woonplaats;        
        bewaarEigenaarInfo = $scope.selectedOption;
    };
    
 $scope.submit = function () {
        if ($scope.adres && $scope.woonplaats) {
            var Eigenaar = {
                id: bewaarEigenaarInfo.id,
                woonplaats: $scope.woonplaats,
                adres: $scope.adres               
            };
            var res = $http.post('http://localhost:5051/RekeningAdministratieBackend/api/RekAdmin/modifyEigenaar', Eigenaar);
            res.success(function (data, status, headers, config) {
                $scope.eigenaars = restService.getEigenaars();
            });
            $scope.selectedOption = '';            
            bewaarEigenaarInfo = null;
            $scope.woonplaats = '';
            $scope.adres = '';     
        

        }
    };            
    
});