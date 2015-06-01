'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($scope, Cartracker, $http) {
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

            $scope.submit = function () {
                if (chooseFactuur) {
                    chooseFactuur.betaalStatus = $scope.Status;
                    var res = $http.post('http://localhost:24707/Rekeningadministratie/api/RekAdmin/WijzigingBetaalStatus', chooseFactuur)
                    .success(function (data) {
                        $scope.facturen = Cartracker.getFactuurs();
                    })
                    .error(function (data, status) {
                        console.error('Repos error', status, data);
                    })
                    .finally(function () {
                        console.log("finally finished repos");
                    });
                } else {
                    $scope.tekst = "foute waarde";
                }
            };

            $scope.onClickRepeat = function (factuur) {
                chooseFactuur = factuur;
                $scope.onderdelen = factuur.factuuronderdelen;
                $scope.Status = factuur.betaalStatus;
                $scope.nummer = factuur.nummer;
            }
        });