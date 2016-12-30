angular.module('AmazonApp').controller('InvoiceController',
                                       function($scope, InvoiceFactory, $routeParams) {
                                            $scope.InvoiceFactory = InvoiceFactory;
                                            $scope.shoppingCartId = $routeParams.shoppingCartId;

                                            $scope.createShipppingInfo = function() {
                                                InvoiceFactory.createShippingInfo($scope.shoppingCartId);
                                            };

                                            $scope.createInvoice = function() {
                                                InvoiceFactory.createInvoice($scope.shoppingCartId);
                                            };
                                        });
