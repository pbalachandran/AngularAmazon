angular.module('AmazonApp').config(function($routeProvider) {
                    $routeProvider.when('/catalog', {
                                            controller: 'CatalogController',
                                            templateUrl: './views/catalog.html'
                                        }).when('/profile', {
                                                controller: 'AccountController',
                                                templateUrl: './views/account.html'
                                                }).when('/avatar', {
                                                        controller: 'AvatarController',
                                                        templateUrl: './views/avatar.html'
                                                        }).when('/createCart', {
                                                                controller: 'ShoppingCartController',
                                                                templateUrl: './views/shoppingCart.html'
                                                                }).when('/displayCart', {
                                                                        controller: 'ShoppingCartDisplayController',
                                                                        templateUrl: './views/shoppingCartDisplay.html'
                                                                }).when('/shop', {
                                                                        controller: 'ShoppingCartFillController',
                                                                        templateUrl: './views/shoppingCartFill.html'
                                                                        }).otherwise({redirectTo: '/'});
                                   });