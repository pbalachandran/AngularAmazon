angular.module('AmazonApp').factory('InvoiceFactory',
                                    function($http, $q, $location) {

                                    var inFactory = {};
                                    inFactory.checkout = function(shoppingCartId) {
                                        console.log("checkout(), shoppingCart: " + shoppingCartId);
                                        $location.path('/shippingInfo').search({shoppingCartId:shoppingCartId});
                                    };
    
                                    inFactory.createShippingInfo = function($scope) {
                                        console.log("createShippingInfo()"); 
                                        var shippingInfoUrl = 'http://localhost:8080/checkout/shippinginfo';
                                        var shippingInfo = {
                                            "firstname": $scope.firstname,
                                            "lastname" : $scope.lastname,
                                            "address" : $scope.address,
                                            "state" : $scope.state,
                                            "zip" : $scope.zip
                                        };
                                        var defer = $q.defer();
                                        $http.post(shippingInfoUrl, shippingInfo).then(function(response) {
                                            defer.resolve(response.data);
                                        });
                                        return defer.promise;
                                    };
    
                                    inFactory.createInvoice = function(shoppingCartId, shippingInfoId, creditCardId) {
                                        console.log("createInvoice()");
                                        var invoiceUrl = 'http://localhost:8080/checkout/invoice';
                                        var invoice = {
                                            "shoppingCartId" : shoppingCartId,
                                            "creditCardId" : creditCardId,
                                            "shippingInfoId" : shippingInfoId
                                        };
                                        var defer = $q.defer();
                                        $http.post(invoiceUrl, invoice).then(function(response) {
                                            defer.resolve(response.data);
                                        });
                                        return defer.promise;
                                    };
                                    return inFactory;
});
