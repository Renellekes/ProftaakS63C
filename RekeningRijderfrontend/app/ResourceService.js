var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Cartracker', ['$resource', function ($resource) {
        return $resource('http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin',
        null, 
        {
            'query': {method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllCars'},
            'getFactuurs':{method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllFactuur'},
            'getCar':{method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/Car/:id'}
        });
    }]);
restservice.factory('restService', ['$resource', function ($resource) {
        return $resource('http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin',
        null, 
        {
            'getEigenaars': {method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllEigenaars'}
            
        });
    }]);