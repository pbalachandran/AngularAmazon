angular.module('AmazonApp').controller('AccountController',
                                       function($scope, $http, $location, AccountFactory) {
                                            $scope.cardTypes = ["VISA",
                                                                "MASTERCARD",
                                                                "DISCOVER"];

                                            $scope.submit = function() {
                                                                console.log("Inside Submit()...");
                                                                AccountFactory.createAccount($scope);
                                                            };
                                       });
