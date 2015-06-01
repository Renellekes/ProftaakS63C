'use strict';

angular.module('myApp.factuurdetailsview', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/factuurview', {
                    templateUrl: 'factuurview/factuurdetailsview.html',
                    controller: 'factuurdetailsviewCtrl'
                });
            }])

        .controller('factuurdetailsviewCtrl', function ($scope, Cartracker, $http) {
            $scope.facturen = [];
            $scope.onderdelen =[];
            $scope.list = [];
            $scope.tekst = "Geen uitgekozen";
            var chooseFactuur = null;
            $scope.init = function ()
            {
                $scope.facturen = Cartracker.getFactuurs();
                console.log($scope.facturen);
            }

            $scope.onClickRepeat = function (factuur) {
                chooseFactuur = factuur;
                $scope.onderdelen = factuur.factuuronderdelen;
                $scope.Status = factuur.betaalStatus;
                $scope.nummer = factuur.nummer;
            }
        });
