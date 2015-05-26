'use strict';

angular.module('myApp.view2', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/view2', {
                    templateUrl: 'view2/view2.html',
                    controller: 'View2Ctrl'
                });
            }])

        .controller('View2Ctrl', function ($scope, Cartraker, $http) {
            $scope.facturen = [];
            $scope.list = [];
            $scope.tekst = "Geen uitgekozen";
            var chooseFactuur = null;
            $scope.init = function ()
            {
                $scope.facturen = Cartraker.getFactuurs();
            }

            $scope.submit = function () {
                if (chooseFactuur) {
                    chooseFactuur.betaalStatus = $scope.Status;
                    var res = $http.post('http://localhost:24707/Rekeningadministratie/api/RekAdmin/WijzigingBetaalStatus', chooseFactuur);
                } else {
                    $scope.tekst = "foute waarde";
                }
            };

            $scope.onClickRepeat = function (factuur) {
                chooseFactuur = factuur;
                $scope.Status = factuur.betaalStatus;
                $scope.nummer = factuur.nummer;
            }
        });