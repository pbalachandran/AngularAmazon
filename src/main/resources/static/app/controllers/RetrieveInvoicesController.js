angular.module('AmazonApp').controller('InvoicesRetrieveController',
                                       function($scope, InvoiceFactory) {
    
                                           $scope.retrieveInvoices = function() {
                                               console.log("retrieveInvoices()");
                                               var inPromise = InvoiceFactory.retrieveInvoices($scope.invoiceId);
                                               inPromise.then(function(data) {

                                               });
                                           };
                                       }
