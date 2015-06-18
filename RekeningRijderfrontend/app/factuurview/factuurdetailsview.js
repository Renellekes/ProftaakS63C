'use strict';

angular.module('myApp.factuurdetailsview', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/factuurdetailsview/:id', {
                    templateUrl: 'factuurview/factuurdetailsview.html',
                    controller: 'factuurdetailsviewCtrl'
                });
            }])

        .controller('factuurdetailsviewCtrl', function ($scope, $routeParams, Post) {
            $scope.factuur = '';    
            $scope.init = function ()
            {
                $scope.factuur = Post.get({id: $routeParams.id}, function (data) {
                    $scope.harry = data;
                });
                console.log($scope.factuur);
            }
        });
