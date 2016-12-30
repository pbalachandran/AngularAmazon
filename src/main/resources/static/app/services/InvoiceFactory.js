angular.module('AmazonApp').factory('InvoiceFactory',
                                    function($http, $q, $location) {

                                    var inFactory = {};
                                    inFactory.checkout = function(shoppingCartId) {
                                        console.log("checkout(), shoppingCart: " + shoppingCartId);
                                        $location.path('/shippingInfo').search({shoppingCartId:shoppingCartId});
                                    };
    
                                    inFactory.createShipppingInfo = function($scope) {
                                        console.log("createShippingInfo()");  
                                    };
                                    return inFactory;
});
