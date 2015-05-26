'use strict';

var rekadmin = angular.module('myApp.view2', ['ngRoute']);

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view2', {
            templateUrl: 'view2/view2.html',
            controller: 'View2Ctrl'
        });
    }]);

rekadmin.controller('View2Ctrl', function ($scope, Cartraker, $http) {
    $scope.cars = [];
    $scope.init = function ()
    {
        $scope.cars = Cartraker.query();
    };

    $scope.list = [];

    $scope.submit = function () {
        if ($scope.kenteken) {
            var Auto = {
                kenteken: $scope.kenteken,
                voertuig: $scope.voertuig,
                eerstekleur: $scope.eerstekleur,
                zitplaatsen: $scope.aantalzitplaatsen
            };
            var res = $http.post('http://localhost:24707/Rekeningadministratie/api/RekAdmin/addCartraker', Auto);
            res.success(function (data, status, headers, config) {
                $scope.cars = Cartraker.query();
            });
            $scope.list.push(Auto.kenteken + Auto.voertuig + Auto.eerstekleur + Auto.zitplaatsen);
            $scope.kenteken = '';
            $scope.voertuig = '';
            $scope.eerstekleur = '';
            $scope.aantalzitplaatsen = '';

        }
    };
});