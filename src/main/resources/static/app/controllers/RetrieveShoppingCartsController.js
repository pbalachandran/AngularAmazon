angular.module('AmazonApp').controller('RetrieveShoppingCartsController',
                                       function ($scope, ShoppingCartFactory) {
                                           $scope.ShoppingCartFactory = ShoppingCartFactory;
    
                                           $scope.retrieveCarts = function() {
                                               var promise = 
                                                  ShoppingCartFactory.retrieve($scope.username);
                                                  promise.then(function(data) {
                                                     $scope.shoppingCarts = data;
                                                     $scope.isNoRetrievedCarts = !data.length;
                                                  });
                                           };
                                       });
