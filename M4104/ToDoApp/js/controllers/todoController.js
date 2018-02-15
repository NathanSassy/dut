/**
 * Le principal module app
 *
 * @type {angular.Module}
 */
angular.module('ToDoApp')
    .controller('ToDoAppController', function ToDoAppController($scope, $routeParams){
        'use strict';

        $scope.tasks = [
        	{
        		title:"Tache 1",
        		duration:20,
        		date:"Lundi",
        		description:"blalbalba",
        		context:"boulot",
        		link:"www.google.fr"
        	},
          ];

    })