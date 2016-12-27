angular.module('AmazonApp').controller('ShoppingCartsRetrieveController',
                                       function ($scope, ShoppingCartFactory) {
                                           $scope.ShoppingCartFactory = ShoppingCartFactory;
    
                                           $scope.retrieveCarts = function() {
                                               var promise = 
                                                  ShoppingCartFactory.retrieve($scope.username);
                                                  promise.then(function(data) {
                                                     $scope.shoppingCarts = data;
                                                  });
                                           };
                                       });
