var meal = angular.module('meal', []); 	// moduł strony startowej, dzieki ktoremu unika się konfliktów;

meal.controller('MealCtrl', ['$scope', '$http', function($scope,$http){

	console.log("Meal CONTROLLER")		//elementy pomocnicze sprawdzające czy strona została poprawnie załadowana

}]);