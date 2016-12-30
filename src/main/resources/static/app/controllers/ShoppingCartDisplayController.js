angular.module('AmazonApp').controller('ShoppingCartDisplayController',
                                       function ($scope, $http, $routeParams, ShoppingCartFactory) {
                                            $scope.ShoppingCartFactory = ShoppingCartFactory;
    
                                            var url = 'http://localhost:8080/shopping/cart/' + $routeParams.shoppingCartId;
                                            $http.get(url).success(function(data) {
                                                        $scope.shoppingCartId = data.shoppingCartId;
                                                        $scope.orderItems = data.orderItems;
                                                        $scope.invoice = data.invoice;
                                                        $scope.total = data.total;
                                                        $scope.status = data.status;
                                                        $scope.account = data.account;
                                            });

                                            $scope.removeFromCart = function(shopppingCartId, orderItemId) {
                                                                        console.log("removeFromCart()");
                                                                        ShoppingCartFactory.pop(shopppingCartId, orderItemId);
                                                                    };
                                        });
