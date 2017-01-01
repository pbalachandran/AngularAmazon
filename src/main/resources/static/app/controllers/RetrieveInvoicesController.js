angular.module('AmazonApp').controller('RetrieveInvoicesController',
                                       function($scope, InvoiceFactory) {
    
                                           $scope.retrieveInvoices = function() {
                                               console.log("retrieveInvoices()");
                                               var inPromise = InvoiceFactory.retrieveInvoices($scope.username);
                                               inPromise.then(function(data) {
                                                   $scope.invoices = data;
                                                   $scope.isNoRetrievedInvoices = !data.length;
                                               });
                                           };
                                       });
