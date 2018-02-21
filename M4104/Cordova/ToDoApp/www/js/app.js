var app = angular.module('ToDoApp', ['ngRoute', 'ngResource'])
    .config(function ($routeProvider) {
        'use strict';

        var routeConfigList = {
            controller: 'ToDoAppController',
            templateUrl:'html/list.html'
        };

        var routeConfigAdd = {
            controller: 'ToDoAppController',
            templateUrl:'html/add.html'
        };

        $routeProvider
            .when('/list', routeConfigList)
            .when('/add', routeConfigAdd)
            .otherwise({
                redirectTo: '/list'
            });
});