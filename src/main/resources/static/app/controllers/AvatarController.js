angular.module('AmazonApp').controller('AvatarController',
                                       function ($scope, $http, $routeParams) {
                                            var url = 'http://localhost:8080/profile/account/' + $routeParams.username;
                                            $http.get(url).success(function(data) {
                                                $scope.username = data.username;
                                                $scope.firstname = data.firstname;
                                                $scope.lastname = data.lastname;
                                        });
});
