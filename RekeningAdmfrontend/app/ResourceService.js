var restservice = angular.module('restservice', ['ngResource']);

restservice.factory('Cartraker', ['$resource', function ($resource) {
        return $resource('http://localhost:47010/Rekeningadministratie/api/RekAdmin',
        null, 
        {
            'query': {method: 'GET', isArray: true, url: 'http://localhost:24707/RekeningAdministratieBackend/api/RekAdmin/getAllCartracker'},
            'getFactuurs':{method: 'GET', isArray: true, url: 'http://localhost:24707/RekeningAdministratieBackend/api/RekAdmin/getAllFactuur'}
        });
    }]);

restservice.factory('restService', ['$resource', function ($resource) {
        return $resource('http://localhost:47010/Rekeningadministratie/api/RekAdmin',
        null, 
        {
            'getAlleKilometerTarieven': {method: 'GET', isArray: true, url: 'http://localhost:47010/Rekeningadministratie/api/RekAdmin/KilometerTarieven/All'},
            'getKilometerTarief':{method: 'GET', isArray: true, url: 'http://localhost:47010/Rekeningadministratie/api/RekAdmin/KilometerTarieven/:id'}
        });
    }]);
