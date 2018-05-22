var home = angular.module('nutrition', []); 	// moduł strony startowej, dzieki ktoremu unika się konfliktów;

home.controller('NutritionCtrl', ['$scope', '$http', function($scope){

	console.log("Nutrition CONTROLLER")		//elementy pomocnicze sprawdzające czy strona została poprawnie załadowana


    $scope.generateNutrition = function(data){
    	console.log("Generating nutrition")
    	console.log(data)
    		console.log("starrting request")
    		console.log(data);
    		$http.post('http://localhost:8080/api/nutrition', data).then(function(response)) {
    			console.log("success")
    			console.log(response)
    			$scope.nutrition = response.data;
    		},
    		function(response) {
    			console.log("fail")
    		});


    	console.log("after request")
    }
}]);