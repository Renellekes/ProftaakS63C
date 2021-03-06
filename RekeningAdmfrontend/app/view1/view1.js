'use strict';

var rekadmin = angular.module('myApp.view1', ['ngRoute']);

rekadmin.config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'view1/view1.html',
            controller: 'View1Ctrl'
        });
    }]);

rekadmin.controller('View1Ctrl', function ($scope, Cartracker, $http) {
    $scope.cartrackers = [];
    $scope.homefacturen = [];
    $scope.hoemfileInfo = [];
    var bewaarAutoInfo = null;
    $scope.initData = function ()
    {
        $scope.cartrackers = Cartracker.query();
        console.log($scope.cartrackers);
        $scope.page = 1;
    };

    $scope.clear = function () {
        $scope.homefacturen = [];
        $scope.hoemfileInfo = [];
    }

    $scope.list = [];

    $scope.chooseCar = function (cartracker) {
        $scope.Cartrackerid = cartracker.id;
        $scope.kenteken = cartracker.auto.kenteken;
        $scope.voertuig = cartracker.auto.voertuig;
        $scope.eersteKleur = cartracker.auto.eersteKleur;
        $scope.zitplaatsen = cartracker.auto.zitplaatsen;
        $scope.website = cartracker.website;
        $scope.homefacturen = cartracker.facturen;
        $scope.homefileInfo = cartracker.fileInfo;
        bewaarAutoInfo = cartracker.auto
    }

    $scope.addid = function () {
        if ($scope.kenteken) {
            console.log('testing');
            var Auto = {
                id: bewaarAutoInfo.id,
                kenteken: $scope.kenteken,
                voertuig: $scope.voertuig,
                eersteKleur: $scope.eersteKleur,
                zitplaatsen: $scope.zitplaatsen
            };
            var res = $http.post('http://localhost:24707/RekeningAdministratieBackend/api/RekAdmin/ModifyCartracker', Auto);
//            $scope.cartrackers = Cartracker.query();
            $scope.kenteken = '';
            $scope.voertuig = '';
            $scope.eersteKleur = '';
            $scope.zitplaatsen = '';
        }
    };
    $scope.submit = function () {
        if ($scope.kenteken) {
            var Auto = {
                id: bewaarAutoInfo.id,
                kenteken: $scope.kenteken,
                voertuig: $scope.voertuig,
                eersteKleur: $scope.eersteKleur,
                zitplaatsen: $scope.zitplaatsen,
                fileInfo: bewaarAutoInfo.fileInfo,
                eigenaar: bewaarAutoInfo.eigenaar,
                gestolen: bewaarAutoInfo.gestolen
            };
            var cartracker = {
                id: $scope.Cartrackerid,
                auto: Auto,
                website: $scope.website,
                facturen: $scope.homefacturen,
                fileInfo: $scope.homefileInfo
            };
            var res = $http.post('http://localhost:24707/RekeningAdministratieBackend/api/RekAdmin/modifyCartracker', cartracker)
                    .success(function (data) {
                        $scope.cartrackers = Cartracker.query();
                    })
                    .error(function (data, status) {
                        console.error('Repos error', status, data);
                    })
                    .finally(function () {
                        console.log("finally finished repos");
                    });
            $scope.Cartrackerid = '';
            $scope.kenteken = '';
            $scope.voertuig = '';
            $scope.eersteKleur = '';
            $scope.zitplaatsen = '';
            $scope.website = '';
            $scope.homefacturen = '';
            $scope.homefileInfo = '';
        }
    };
});
