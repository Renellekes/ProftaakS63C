var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Cartraker', ['$resource', function ($resource) {
        return $resource('http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin',
        null, 
        {
            'query': {method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllCars'},
            'getFactuurs':{method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllFactuur'}
        });
    }]);
restservice.factory('restService', ['$resource', function ($resource) {
        return $resource('http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin',
        null, 
        {
            'getEigenaars': {method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllEigenaars'},
            'getFactuur':{method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllFactuur'}
            
        });
    }]);