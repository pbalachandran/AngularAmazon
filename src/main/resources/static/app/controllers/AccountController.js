angular.module('AmazonApp').controller('AccountController',
                                       function($scope, $http, $location, AccountFactory) {
                                            $scope.cardTypes = ["VISA",
                                                                "MASTERCARD",
                                                                "DISCOVER"];

                                            $scope.createAccount = function() {
                                                                        console.log("createAccount()");
                                                                        AccountFactory.createAccount($scope);
                                                                   };
                                       });
