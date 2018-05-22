var home = angular.module('meal', []); 	// moduł strony startowej, dzieki ktoremu unika się konfliktów;

home.controller('MealCtrl', ['$scope', '$http', function($scope){

	console.log("Meal CONTROLLER")		//elementy pomocnicze sprawdzające czy strona została poprawnie załadowana

}]);