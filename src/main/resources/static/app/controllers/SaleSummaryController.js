angular.module('AmazonApp').controller('SaleSummaryController',
                                       function($scope, $routeParams, InvoiceFactory) {
                                            $scope.invoiceId = $routeParams.invoiceId;
                                            var promise = InvoiceFactory.retrieveInvoice($scope.invoiceId);
                                            promise.then(function(data) {
                                                $scope.totalBeforeTaxes = data.totalBeforeTaxes;
                                                $scope.taxes = data.taxes;
                                                $scope.invoiceTotal = data.invoiceTotal;
                                                $scope.transactionConfirmation = data.transactionConfirmation;
                                                $scope.date = data.date;
                                            });
    
    
});
        