angular.module('AmazonApp').controller('InvoiceController',
                                       function($scope, $routeParams) {
                                            $scope.invoiceId = $routeParams.invoiceId;
});
