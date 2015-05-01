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
            var auto = {
                kenteken: $scope.kenteken,
                voertuig: $scope.voertuig,
                eerstekleur: $scope.eerstekleur,
                aantalzitplaatsen: $scope.aantalzitplaatsen
            }
            var res = $http.post('http://localhost:24707/Rekeningadministratie/api/RekAdmin/addCartraker', JSON.stringify(auto));
            res.success(function (data, status, headers, config) {
                $scope.cars = Cartraker.query();
            });
            $scope.list.push(this.kenteken + $scope.kenteken + $scope.voertuig + $scope.eerstekleur + $scope.aantalzitplaatsen);
            $scope.kenteken = '';
            $scope.voertuig = '';
            $scope.eerstekleur = '';
            $scope.aantalzitplaatsen = '';

        }
    };
});