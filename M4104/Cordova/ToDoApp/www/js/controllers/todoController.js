angular.module("ToDoApp")
    .controller("ToDoAppController", function ToDoAppController($scope, $routeParams, $location) {
        'use strict';

        $scope.saved = localStorage.getItem("tasks");
	    $scope.tasks = ($scope.saved !== null) ? JSON.parse($scope.saved) : [];
        localStorage.setItem("tasks", JSON.stringify($scope.tasks));

        $scope.addTask = function(value) {
        	if(value == "modify") {
        		var id = $routeParams.task;
        		$scope.tasks.splice($scope.tasks[id], 1);
            	localStorage.setItem('tasks',JSON.stringify($scope.tasks));
        	}

        	$scope.tasks.push({
	                title: $scope.taskTitle,
	                description: $scope.taskDescription,
	                date: $scope.taskDate,
	                duration: $scope.taskDuration,
	                context: $scope.taskContext,
	                url: $scope.taskURL,
	            });

        	localStorage.setItem("tasks", JSON.stringify($scope.tasks));
            $location.path("/list");
        };

        $scope.removeTask = function(task) {
            $scope.tasks.splice($scope.tasks.indexOf(task), 1);
            localStorage.setItem("tasks",JSON.stringify($scope.tasks));
        }

        $scope.taskAddView = function(value, task) {
        	if(value == "add") {
        		$location.path("/add").search("new");	
        	}
        	else if(value == "modify") {
        		var index = $scope.tasks.indexOf(task);
            	$location.path("/add").search({task: index});
        	}
        }

        $scope.initAddView = function(){
            var id = $routeParams.task;
            if(typeof id != "undefined") {
            	$scope.taskTitle = $scope.tasks[id].title;
	            $scope.taskDescription = $scope.tasks[id].description; 
	            $scope.taskDate = new Date($scope.tasks[id].date); 
	            $scope.taskDuration = new Date($scope.tasks[id].duration);
	            $scope.taskContext= $scope.tasks[id].context; 
	            $scope.taskURL= $scope.tasks[id].url;
	            $scope.showAddButton = false;
	            $scope.showModifyButton = true;
            }
            else {
            	$scope.showAddButton = true;
	            $scope.showModifyButton = false;	
            }
        }

        $scope.backToList = function() {
            $location.path("/list").search("");   
        }
})