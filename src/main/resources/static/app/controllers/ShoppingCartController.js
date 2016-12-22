angular.module('AmazonApp').controller('ShoppingCartController',
                                       function ($scope, ShoppingCartFactory) {

                                            $scope.createCart = function() {
                                                ShoppingCartFactory.create($scope.username);
                                            };
                                       });
