'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', ['$scope', 'userService', '$location', function($scope, userService, $location) {
        $scope.userService = userService;
        $scope.$location = $location;
     

        if (!$scope.KilometerTarief){
            $scope.KilometerTarief = $scope.userService.getUserById(1);
        }
        $scope.allUsers = $scope.userService.getUsers();
        
        $scope.postTweet = function(){
                $scope.userService.addTweetForUser($scope.tweetContent, $scope.KilometerTarief.id); 
                $scope.tweetContent = "";
        };
        
        $scope.getTimeline = function(){

            var tweets = [];
            for (var i = 0; i < $scope.KilometerTarief.following.length; i++){
                tweets.push.apply(tweets, $scope.userService.getUserById($scope.KilometerTarief.following[i].id).tweets);
            }
            tweets.push.apply(tweets, $scope.KilometerTarief.tweets);
            return tweets;
        };
        
        $scope.setUser = function(){
            $scope.KilometerTarief = $scope.userService.getUserById($scope.selectedUser.id);
        };

}]);