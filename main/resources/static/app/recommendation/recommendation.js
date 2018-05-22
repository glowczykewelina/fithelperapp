var home = angular.module('recommendation', []); 	// moduł strony startowej, dzieki ktoremu unika się konfliktów;

home.controller('RecommendationCtrl', ['$scope', '$http', function($scope){

	console.log("Recommendation CONTROLLER")		//elementy pomocnicze sprawdzające czy strona została poprawnie załadowana

}]);