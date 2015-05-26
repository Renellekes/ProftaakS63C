var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Cartraker', ['$resource', function ($resource) {
        return $resource('http://localhost:24707/Rekeningadministratie/api/RekAdmin',
        null, 
        {
            'query': {method: 'GET', isArray: true, url: 'getAllCars'},
            'getFactuurs':{method: 'GET', isArray: true, url: 'getAllFactuur'}
        });
    }]);