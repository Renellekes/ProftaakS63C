var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Cartracker', ['$resource', function ($resource) {
        return $resource('http://localhost:8080/Rekeningadministratie/api/RekAdmin',
        null, 
        {
            'query': {method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllCartracker'},
            'getFactuurs':{method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/getAllFactuur'}
        });
    }]);

restservice.factory('restService', ['$resource', function ($resource) {
        return $resource('http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin',
        null, 
        {
            'getAlleKilometerTarieven': {method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/KilometerTarieven/All'},
            'getKilometerTarief':{method: 'GET', isArray: true, url: 'http://localhost:8080/RekeningAdministratieBackend/api/RekAdmin/KilometerTarieven/:id'}
        });
    }]);
