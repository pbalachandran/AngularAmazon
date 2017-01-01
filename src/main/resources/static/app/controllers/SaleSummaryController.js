angular.module('AmazonApp').controller('SaleSummaryController',
                                       function($scope, $routeParams) {
                                            $scope.invoiceId = $routeParams.invoiceId;
});
