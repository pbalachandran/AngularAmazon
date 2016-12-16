angular.module('AmazonApp').directive('validateUsername',
                                      function(AccountFactory) {
                                        return {
                                            restrict: 'A',
                                            require: 'ngModel',
                                            link: function($scope, element, attrs, ngModel) {
                                                ngModel.$asyncValidators.validate = function(modelValue, viewValue) {
                                                    return AccountFactory.validateUsername(modelValue);
                                                };
                                            }
                                        };
                            });