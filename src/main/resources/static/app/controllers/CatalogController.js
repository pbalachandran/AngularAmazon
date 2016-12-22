angular.module('AmazonApp').controller('CatalogController',
                                       function ($scope, $http, $location) {
                                             $http.get('http://localhost:8080/browse/categories').
                                                          success(function(data) {
                                                            $scope.categories = data;
                                                          });

                                             $scope.toggleSelect = function(categoryName) {
                                                var url = 'http://localhost:8080/browse/category/' + categoryName;
                                                console.log(url);
                                                $http.get(url).
                                                      success(function(data) {
                                                        $scope.selectedCategory = data;
                                                      });
                                            };
                                       });
