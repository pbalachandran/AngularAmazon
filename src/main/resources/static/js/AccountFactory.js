angular.module('AmazonApp').factory('AccountFactory', function ($http, $q, $location) {

    var acctFactory = {};
    acctFactory.constructAccount = function ($scope) {
        console.log("Create CreditCardType....");
        var cctype = {"creditCardTypeName" : $scope.ccTypeName};
        var ccTypeUrl =
               'http://localhost:8080/profile/creditcardtype/';
        $http.post(ccTypeUrl, cctype).
                success(function(data) {
                    console.log("Retrieve CreditCardType....");
                    var ccTypeUrl =
                        'http://localhost:8080/profile/creditcardtype/' + data.creditCardTypeId;
                        $http.get(ccTypeUrl).
                                 success(function(data) {
                                            console.log("Create CreditCard....");
                                            var cc = {"creditCardNumber": $scope.creditCardNumber,
                                                      "creditCardType" : {"creditCardTypeId" : data.creditCardTypeId,
                                                                          "creditCardTypeName" : data.creditCardTypeName},
                                                      "firstname": $scope.firstname,
                                                      "lastname" : $scope.lastname,
                                                      "expiryDate" : $scope.expiryDate,
                                                      "securityCode" : $scope.securityCode};

                                            console.log("CC: " + JSON.stringify(cc));

                                            var ccUrl = 'http://localhost:8080/profile/creditcard';
                                            $http.post(ccUrl, cc).
                                                         success(function(data) {
                                                                console.log("Retrieve CreditCard....");
                                                                var getCCUrl = 'http://localhost:8080/profile/creditcard/' + data.creditCardId;
                                                                $http.get(getCCUrl).
                                                                             success(function(data) {                                                                       console.log("Create Account....");
                                                                                 var acct = {"username" : $scope.username,
                                                                                             "firstname": $scope.firstname,
                                                                                             "lastname" : $scope.lastname,
                                                                                             "password" : $scope.password,
                                                                                             "creditCard" : {"creditCardId" : data.creditCardId,
                                                                                                             "creditCardNumber" : data.creditCardNumber,
                                                                                                             "creditCardType" :
                                                                                                                {"creditCardTypeId": data.creditCardType.creditCardTypeId,
                                                                                                                 "creditCardTypeName": data.creditCardType.creditCardTypeName},
                                                                                                             "firstname" : data.firstname,
                                                                                                             "lastname" : data.lastname,
                                                                                                             "expiryDate" : data.expiryDate,
                                                                                                             "securityCode" : data.securityCode
                                                                                                            }
                                                                                            };

                                                                                 var accountUrl = 'http://localhost:8080/profile/account';
                                                                                 $http.post(accountUrl, acct).
                                                                                                success(function(data) {
                                                                                                    $location.path('/avatar').
                                                                                                            search({username : data.username});

                                                                                            });
                                                                             });
                                                         });

                                 });
                });
    };

    acctFactory.validateUsername = function (username) {
        console.log("Inside validateUsername.....");
        var getCCUrl = 'http://localhost:8080/profile/account/' + username;
        var defer = $q.defer();
        $http.get(getCCUrl).
            success(function(data) {
                console.log("data.username: " + data.username);
                if (data.username == 'invalid-user') {
                    console.log("Invalid user");
                    defer.reject(false);
                } else {
                    console.log("Valid user");
                    defer.resolve(true);
                }
            });
        return defer.promise;
    };
    return acctFactory;
});
