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
    var bewaarAuto;
    $scope.initData = function ()
    {
        $scope.cars = Cartraker.query();
        console.log($scope.cars);
        var Auto = {
            kenteken: $scope.kenteken,
            voertuig: $scope.voertuig,
            eerstekleur: $scope.eerstekleur,
            zitplaatsen: $scope.aantalzitplaatsen
        };
    };

    $scope.clear = function(){
        bewaarAuto = null;
    }

    $scope.list = [];

    $scope.chooseCar = function (auto) {
        $scope.kenteken = auto.kenteken;
        $scope.voertuig = auto.voertuig;
        $scope.eerstekleur = auto.eerstekleur;
        $scope.aantalzitplaatsen = auto.aantalzitplaatsen;
        console.log($scope.voertuig + $scope.eerstekleur + $scope.aantalzitplaatsen)
        bewaarAuto = auto;
    }

    $scope.addid = function () {
        if ($scope.kenteken) {
            console.log('testing');
            var Auto = {
                id: bewaarAuto.id,
                kenteken: $scope.kenteken,
                voertuig: $scope.voertuig,
                eerstekleur: $scope.eerstekleur,
                zitplaatsen: $scope.aantalzitplaatsen
            };
            var res = $http.post('http://localhost:24707/Rekeningadministratie/api/RekAdmin/ModifyCartraker', Auto);
            $scope.list.push(Auto.kenteken + Auto.voertuig + Auto.eerstekleur + Auto.zitplaatsen);
            $scope.kenteken = '';
            $scope.voertuig = '';
            $scope.eerstekleur = '';
            $scope.aantalzitplaatsen = '';
        }
    }
    $scope.submit = function () {
        if ($scope.kenteken) {
            var Auto = {
                kenteken: $scope.kenteken,
                voertuig: $scope.voertuig,
                eerstekleur: $scope.eerstekleur,
                zitplaatsen: $scope.aantalzitplaatsen
            };
            var res = $http.post('http://localhost:47010/Rekeningadministratie/api/RekAdmin/addCartraker', Auto);
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
