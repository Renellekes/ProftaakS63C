'use strict';

// Declare app level module which depends on views, and components
angular.module('myApp', [
  'ngRoute',
  'myApp.voertuigen',
  'myApp.NAW',
  'myApp.factuurview',
  'myApp.version',
  'restservice'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/voertuigen'});
}]);
