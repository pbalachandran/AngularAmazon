angular.module('AmazonApp').controller('ShippingInfoController',
                                       function($scope, InvoiceFactory, $routeParams) {
                                            $scope.InvoiceFactory = InvoiceFactory;
                                            $scope.shoppingCartId = $routeParams.shoppingCartId;
    
                                            console.log("ShoppingCartId: " + $scope.shoppingCartId);

                                            $scope.createShipppingInfo = function() {
                                                InvoiceFactory.createShippingInfo($scope.shoppingCartId);
                                            };

                                            $scope.createInvoice = function() {
                                                InvoiceFactory.createInvoice($scope.shoppingCartId);
                                            };
                                        });
