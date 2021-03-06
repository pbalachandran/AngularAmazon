angular.module('AmazonApp').factory('ShoppingCartFactory',
                                    function($http, $q, $location) {

                                        var scFactory = {};
                                        scFactory.create = function(aUsername) {
                                            console.log("create(), username: " + aUsername);
                                            var cartURL = 'http://localhost:8080/shopping/cart/' + aUsername;
                                            $http.post(cartURL).success(function(data) {
                                                                    $location.path('/displayCart').search({shoppingCartId :  data.shoppingCartId});
                                                                });
                                        };
    
                                        scFactory.delete = function(shoppingCartId) {
                                            console.log("delete(), shoppingCartId: " + shoppingCartId);
                                            var cartURL = 'http://localhost:8080/shopping/cart/' + shoppingCartId;
                                            $http.delete(cartURL).success(function(data) {
                                                console.log("Delete was: " + data);
                                                if (data == true) {
                                                    $location.path('/catalog');
                                                }
                                            });
                                        };
    
                                        scFactory.retrieve = function(aUsername) {
                                            console.log("retrieve(), aUsername: " + aUsername);
                                            var cartsURL = 'http://localhost:8080/shopping/carts/' + aUsername + '/active';
                                            
                                            var defer = $q.defer();
                                            $http.get(cartsURL).then(function(response) {
                                                defer.resolve(response.data);
                                            });
                                            return defer.promise;
                                        };
                                        
                                        scFactory.retrieveCart = function(shoppingCartId) {
                                            console.log("retrieveCart(), shoppingCartId: " + shoppingCartId);
                                            var cartURL = 'http://localhost:8080/shopping/cart/' + shoppingCartId;

                                            var defer = $q.defer();
                                            $http.get(cartURL).then(function(response) {
                                                defer.resolve(response.data);
                                            });
                                            return defer.promise;
                                        };

                                        scFactory.push = function(shoppingCartId, inventoryItemId, quantity) {
                                            console.log("push()");
                                            var url = 'http://localhost:8080/shopping/order';

                                            console.log("shoppingCartId: " + shoppingCartId);
                                            console.log("inventoryItemId: " + inventoryItemId);
                                            console.log("quantity: " + quantity);

                                            var oi = {"quantity" : quantity,
                                                      "inventoryItemId" : inventoryItemId,
                                                      "shoppingCartId" : shoppingCartId};
                                            
                                            var defer = $q.defer();
                                            $http.post(url, oi).then(function(response) {
                                                defer.resolve(response.data);
                                            });
                                            return defer.promise;
                                        };

                                        scFactory.pop = function(shoppingCartId, orderItemId) {
                                            console.log("pop()");
                                            console.log("shoppingCartId: " + shoppingCartId);
                                            console.log("orderItemId: " + orderItemId);

                                            var url = 'http://localhost:8080/shopping/order?shoppingCartId=' + shoppingCartId + "&orderItemId=" + orderItemId;
                                            $http.delete(url).success(function(data) {
                                                    console.log("Delete was: " + data);
                                                    if (data == true) {
                                                        scFactory.shop(shoppingCartId);
                                                    }
                                            });
                                        };

                                        scFactory.shop = function(shoppingCartId) {
                                            console.log("shop()");
                                            $location.path('/shop').search({shoppingCartId:shoppingCartId});
                                        };

                                        scFactory.display = function(shoppingCartId) {
                                            console.log("display(), shoppingCardId: " + shoppingCartId);
                                            $location.path('/displayCart').search({shoppingCartId : shoppingCartId});
                                        };

                                        return scFactory;
                                    });
