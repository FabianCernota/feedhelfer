var feedhelfer = angular.module('feedhelfer', ['md.data.table', 'ngMaterial', 'ngRoute']);


feedhelfer.config(['$routeProvider',
function($routeProvider) {
    $routeProvider.
        when('/', {
            templateUrl: 'partials/home.html',
            controller: 'homeCtrl'
        }).
        otherwise({
            redirectTo: '/'
        });
}]);

feedhelfer.controller('homeCtrl', function($http, $scope){
    $http.get('http://localhost:8080/api/feeds/').then(function(feedResponse) {
        $scope.feeds = feedResponse.data;
    });
});


