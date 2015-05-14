'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', function($scope, Cartraker, $http) {
    $scope.facturen = [];
    $scope.list = [];
    $scope.tekst = "Geen uitgekozen";
    $scope.init = function ()
    {
        $scope.cars = Cartraker.getFactuurs();
    }
    
    $scope.submit = function () {
        if ($scope.Status && $scope.tekst !="Geen uitgekozen" ) {
            $scope.list.push(this.Status);
            $scope.Status = '';
        }else{
            $scope.tekst = "foute waarde";
        }
    };
    
    $scope.onClickRepeat = function(klickWaarde){
        $scope.tekst = klickWaarde;
    }
});