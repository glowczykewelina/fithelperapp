var app = angular.module('engineer', [ 'ngRoute', 'ngAnimate', 'ngTouch', 'route-segment', 'view-segment', 'ui.bootstrap', 'home', 'recommendation','meal','nutrition' ]);

app.config(function($routeSegmentProvider, $routeProvider, $locationProvider) {


		    $locationProvider.hashPrefix('');

//routing odpowiadający za przechodzenie między stronami;
			$routeSegmentProvider.options.autoLoadTemplates = true;

			$routeSegmentProvider
					.when('/', 'home')
					.segment('home', {
								templateUrl : 'app/home/home.html',
								controller : 'HomeCtrl'
							})

			$routeSegmentProvider
			.when('/nutrition', 'nutrition')
					.segment('nutrition', {
								templateUrl : 'app/nutrition/nutrition.html',
								controller : 'NutritionCtrl'
							})

			$routeSegmentProvider
					.when('/api/meal', 'api/meal')
					.segment('meal', {
								templateUrl : 'app/meal/meal.html',
								controller : 'MealCtrl'
							})

			$routeSegmentProvider
            					.when('/api/recommendation', 'api/recommendation')
            					.segment('recommendation', {
            								templateUrl : 'app/recommendation/recommendation.html',
            								controller : 'RecommendationCtrl'
            							})



			$routeProvider.otherwise({
				redirectTo : '/'
			});

});


app.controller('MainCtrl', ['$scope', '$routeSegment', function($scope, $routeSegment){

	$scope.$routeSegment = $routeSegment;
	console.log("MAIN CONTROLLER")

}]);