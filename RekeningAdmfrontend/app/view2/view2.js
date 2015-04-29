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
    
    $scope.init = function ()
    {
        $scope.cars = Cartraker.getFactuurs();
    }
    
    $scope.submit = function () {
        if ($scope.Status) {
            $scope.list.push(this.Status);
            $scope.Status = '';
        }
    };
});