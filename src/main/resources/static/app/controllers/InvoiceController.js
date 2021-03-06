angular.module('AmazonApp').controller('InvoiceController',
                                       function($scope, $routeParams, $location, AccountFactory, ShoppingCartFactory, InvoiceFactory) {
                                            $scope.shoppingCartId = $routeParams.shoppingCartId;

                                            $scope.createInvoice = function() {
                                                console.log("createInvoice()");
                                                var siPromise = InvoiceFactory.createShippingInfo($scope);
                                                siPromise.then(function(data) {
                                                    $scope.shippingInfoId = data.shippingInfoId;
                                                    
                                                    var scPromise = ShoppingCartFactory.retrieveCart($scope.shoppingCartId);
                                                    scPromise.then(function(data) {
                                                        var actPromise = AccountFactory.retrieveAccount(data.username);
                                                        actPromise.then(function(data){   
                                                            $scope.creditCardId = data.creditCard.creditCardId;
                                                            
                                                            var inPromise = 
                                                                InvoiceFactory.createInvoice($scope.shoppingCartId, $scope.shippingInfoId, $scope.creditCardId);
                                                            inPromise.then(function(data) {
                                                                $location.path('/saleSummary').search({invoiceId:data.invoiceId}); 
                                                            });
                                                        });
                                                    });                                                                
                                                });
                                            };
                                       });
