angular.module('AmazonApp').controller('FillShoppingCartController',
                                       function($scope, $http, $routeParams, ShoppingCartFactory, InvoiceFactory) {
                                            $scope.shoppingCartId = $routeParams.shoppingCartId;
                                            $scope.ShoppingCartFactory = ShoppingCartFactory;
                                            $scope.InvoiceFactory = InvoiceFactory;

                                            $http.get('http://localhost:8080/browse/categories').
                                                            success(function(data) {
                                                                $scope.categories = data;
                                                            });

                                            $scope.toggleSelect = function(categoryName) {
                                                        var url = 'http://localhost:8080/browse/category/' + categoryName;
                                                        console.log(url);
                                                        $http.get(url).
                                                            success(function(data) {
                                                                $scope.selectedCategory = data;
                                                            });
                                            };

                                            $scope.addToCart = function(shoppingCartId, inventoryItemId, quantity) {
                                                        console.log("addToCart()");
                                                        var promise = 
                                                            ShoppingCartFactory.push(shoppingCartId, inventoryItemId, quantity);
                                                        promise.then(function(data) {
                                                             $scope.orderItem = data;
                                                        });
                                            };
                                 });
