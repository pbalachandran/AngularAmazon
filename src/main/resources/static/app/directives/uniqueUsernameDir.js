angular.module('AmazonApp').directive('uniqueUsername',
                                      function(AccountFactory) {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function($scope, element, attrs, ngModel) {
            ngModel.$asyncValidators.unique = function(modelValue, viewValue) {
                return AccountFactory.uniqueUsername(modelValue);
            };
        }
    };
});